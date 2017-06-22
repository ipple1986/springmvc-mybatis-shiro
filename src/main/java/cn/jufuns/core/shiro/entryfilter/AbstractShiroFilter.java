package cn.jufuns.core.shiro.entryfilter;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.subject.WebSubject;

public abstract class AbstractShiroFilter extends org.apache.shiro.web.servlet.AbstractShiroFilter {

	private Serializable sessionId;
	@Override
	protected WebSubject createSubject(ServletRequest request, ServletResponse response) {
		WebSubject.Builder builder = new WebSubject.Builder(getSecurityManager(), request, response);
		if(this.sessionId!=null){
			builder.sessionId(this.sessionId);	
		}
		return builder.buildWebSubject();
	}
	public void setSessionId(Serializable sessionId) {
		this.sessionId = sessionId;
	}
	
}
