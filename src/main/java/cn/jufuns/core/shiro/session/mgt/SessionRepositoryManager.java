package cn.jufuns.core.shiro.session.mgt;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import cn.jufuns.core.shiro.session.SessionDAO;
import cn.jufuns.core.shiro.session.SessionStatus;
import cn.jufuns.saas.entity.OnlineUser;
import cn.jufuns.saas.entity.SysUser;


public class SessionRepositoryManager {

	private static Logger LOGGER = Logger.getLogger(SessionRepositoryManager.class);
	
	public static final String SESSION_STATUS ="jufuns-online-status";
	

	private SessionDAO sessionDAO;
	
	/**
	 * 获取所有的有效Session用户
	 * @return
	 */
	public  List<OnlineUser> getAllUser() {
		//获取所有session
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		List<OnlineUser> list = new ArrayList<OnlineUser>();
		
		for (Session session : sessions) {
			OnlineUser bo = getSessionBo(session);
			if(null != bo){
				list.add(bo);
			}
		}
		return list;
	}
	/**
	 * 根据ID查询 SimplePrincipalCollection
	 * @param userIds	用户ID
	 * @return
	 */
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds){
		//把userIds 转成Set，好判断
		
		Set<Long> idset = new LinkedHashSet<Long>(Arrays.asList(userIds));
		//获取所有session
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		//定义返回
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		for (Session session : sessions) {
			//获取SimplePrincipalCollection
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if(null != obj && obj instanceof SimplePrincipalCollection){
				//强转
				SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
				//判断用户，匹配用户ID。
				obj = spc.getPrimaryPrincipal();
				if(null != obj && obj instanceof SysUser){
					SysUser user = (SysUser)obj;
					//比较用户ID，符合即加入集合
					if(null != user && idset.contains(user.getId())){
						list.add(spc);
					}
				}
			}
		}
		return list;
	}
	
	
	
	/**
	 * 获取单个Session
	 * @param sessionId
	 * @return
	 */
	public OnlineUser getSession(String sessionId) {
		Session session = getSessionDAO().getSessionRepository().get(sessionId);
		OnlineUser bo = getSessionBo(session);
		return bo;
	}
	private OnlineUser getSessionBo(Session session){
		//获取session登录信息。
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if(null == obj){
			return null;
		}
		//确保是 SimplePrincipalCollection对象。
		if(obj instanceof SimplePrincipalCollection){
			SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
			/**
			 * 获取用户登录的，@link SampleRealm.doGetAuthenticationInfo(...)方法中
			 * return new SimpleAuthenticationInfo(user,user.getPswd(), getName());的user 对象。
			 */
			obj = spc.getPrimaryPrincipal();
			if(null != obj && obj instanceof SysUser){
				//存储session + user 综合信息
				OnlineUser userBo = new OnlineUser((SysUser)obj);
				//最后一次和系统交互的时间
				userBo.setLastAccess(session.getLastAccessTime());
				//主机的ip地址
				userBo.setHost(session.getHost());
				//session ID
				userBo.setSessionId(session.getId().toString());
				//session最后一次与系统交互的时间
				userBo.setLastLoginTime(session.getLastAccessTime());
				//回话到期 ttl(ms)
				userBo.setTimeout(session.getTimeout());
				//session创建时间
				userBo.setStartTime(session.getStartTimestamp());
				//是否踢出
				SessionStatus sessionStatus = (SessionStatus)session.getAttribute(SESSION_STATUS);
				boolean status = Boolean.TRUE;
				if(null != sessionStatus){
					status = sessionStatus.getOnlineStatus();
				}
				userBo.setSessionStatus(status);
				return userBo;
			}
		}
		return null;
	}
	/**
	 * 改变Session状态
	 * @param status {true:踢出,false:激活}
	 * @param sessionId
	 * @return
	 */
	public Map<String, Object> changeSessionStatus(Boolean status,
			String sessionIds) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String[] sessionIdArray = null;
			if(sessionIds.indexOf(",") ==-1){
				sessionIdArray = new String[]{sessionIds};
			}else{
				sessionIdArray = sessionIds.split(",");
			}
			for (String id : sessionIdArray) {
				Session session = getSessionDAO().getSessionRepository().get(id);
				SessionStatus sessionStatus = new SessionStatus();
				sessionStatus.setOnlineStatus(status);
				session.setAttribute(SESSION_STATUS, sessionStatus);
				sessionDAO.update(session);
			}
			map.put("status", 200);
			map.put("sessionStatus", status?1:0);
			map.put("sessionStatusText", status?"踢出":"激活");
			map.put("sessionStatusTextTd", status?"有效":"已踢出");
		} catch (Exception e) {
			LOGGER.error(String.format( "改变Session状态错误，sessionId[%s]", sessionIds),e);
			map.put("status", 500);
			map.put("message", "改变失败，有可能Session不存在，请刷新再试！");
		}
		return map;
	}
	/**
	 * 查询要禁用的用户是否在线。
	 * @param id		用户ID
	 * @param status	用户状态
	 */
	public void forbidUserById(Long id, Long status) {
		//获取所有在线用户
		for(OnlineUser bo : getAllUser()){
			Long userId = bo.getId();
			//匹配用户ID
			if(userId.equals(id)){
				//获取用户Session
				Session session = getSessionDAO().getSessionRepository().get(bo.getSessionId());
				//标记用户Session
				SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
				//是否踢出 true:有效，false：踢出。
				sessionStatus.setOnlineStatus(status.intValue() == 1);
				//更新Session
				sessionDAO.update(session);
			}
		}
	}
	public SessionDAO getSessionDAO() {
		return sessionDAO;
	}
	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}
	
	

}
