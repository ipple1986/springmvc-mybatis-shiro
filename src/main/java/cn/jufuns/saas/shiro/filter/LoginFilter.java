package cn.jufuns.saas.shiro.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;

import cn.jufuns.core.util.Constants;

public class LoginFilter<T> extends AccessControlFilter {

	private static Logger LOGGER = Logger.getLogger(LoginFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {

		if (null != SecurityUtils.getSubject().getPrincipal() || isLoginRequest(request, response)) {// &&
																										// isEnabled()
			LOGGER.info("====进入==LoginFilter.isAccessAllowed==已经登录==");
			return Boolean.TRUE;
		}
		LOGGER.info("====进入==LoginFilter.isAccessAllowed==未登录==");
		if (Constants.isAjax(request)) {// ajax请求,非登录状态
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("status", "300");
			resultMap.put("message", "你没有登录");// 当前用户没有登录！
			Constants.out(response, resultMap);
		}
		return Boolean.FALSE;

	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 保存Request和Response 到登录后的链接
		saveRequestAndRedirectToLogin(request, response);
		return Boolean.FALSE;
	}

	public static void main(String[] args) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("s", "s");
		maps.put("i", 1);
		maps.put("l", 1l);
		maps.put("d", 0.212);
		JSONWrappedObject json = new JSONWrappedObject("", "", maps, null);
		LOGGER.info(json.getValue());

	}

}
