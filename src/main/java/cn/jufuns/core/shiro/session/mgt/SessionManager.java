package cn.jufuns.saas.shiro.session.mgt;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class SessionManager extends DefaultWebSessionManager {
	@Override
	public boolean isServletContainerSessions() {
		return Boolean.TRUE;
	}
}
