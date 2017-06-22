package cn.jufuns.core.shiro.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import cn.jufuns.core.util.Repository;

/**
 * 负责管理操作session,引入redis
 * 
 * @author ipple1986
 *
 */
public class SessionDAO extends AbstractSessionDAO {

	private static Logger LOGGER = Logger.getLogger(SessionDAO.class);

	private Repository<Serializable, Session> sessionRepository;

	public Repository<Serializable, Session> getSessionRepository() {
		return sessionRepository;
	}

	public void setSessionRepository(Repository<Serializable, Session> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return getSessionRepository().get(sessionId);
	}
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		this.assignSessionId(session, sessionId);
		//System.out.println("=====doCreate======");
		getSessionRepository().set(session);
		return sessionId;
	}
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		//System.out.println("=====update======");
		getSessionRepository().set(session);
	}

	@Override
	public void delete(Session session) {
		if (session == null) {
			LOGGER.error("Session 不能为null\t" + getClass());
			return;
		}
		Serializable id = session.getId();
		if (id != null)
			getSessionRepository().del(id);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return getSessionRepository().getall();
	}



}
