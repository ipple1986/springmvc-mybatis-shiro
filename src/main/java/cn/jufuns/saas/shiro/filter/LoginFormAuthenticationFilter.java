package cn.jufuns.saas.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

//为了将异常取到，并抛出，重写登录过滤器
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {
	public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "LOGIN_EXCEPTION";

	@Override
	public void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		String className = ae.getClass().getName();
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(LoginFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, ae);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response){
		setFailureAttribute(request, e);
		return true;
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
			return super.onLoginSuccess(token, subject, request, response);
	}
}
