package cn.jufuns.core.shiro.session.listener;


import java.io.Serializable;

import org.apache.shiro.session.Session;

import cn.jufuns.core.util.Repository;

public class SessionListener implements org.apache.shiro.session.SessionListener {

    private Repository<Serializable,Session>  sessionRepository;

    @Override
    public void onStart(Session session) {
        //TODO
        System.out.println("监控到新启动了一个会话，会话Id:"+session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("监控到暂停了会话:"+session.getId());
    }

    @Override
    public void onExpiration(Session session) {
    	 System.out.println("监控到会话过期:"+session.getId());
        //sessionManager已经设置了deleteInvalidSessions=true，不用手动删除
    	 //sessionRepository.del(session.getId());
    }

	public Repository<Serializable, Session> getSessionRepository() {
		return sessionRepository;
	}

	public void setSessionRepository(Repository<Serializable, Session> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}
	

   

}

