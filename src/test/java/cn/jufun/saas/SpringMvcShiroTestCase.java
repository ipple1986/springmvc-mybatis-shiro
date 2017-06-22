package cn.jufun.saas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.Assert;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 一个单元测试的样例
 * 
 * @author ipple1986
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy(
		{
			@ContextConfiguration(name = "parent",locations = { "classpath:spring/spring-config-context.xml"}),
			@ContextConfiguration(name = "child",locations = {  "classpath:spring/spring-config-mvc.xml" })
		}
	)
public class SpringMvcShiroTestCase {

	@Autowired
	ShiroFilterFactoryBean shiroFilterFactoryBean;

	@Autowired
	private WebApplicationContext applicationContext;

	@Autowired
	DefaultWebSecurityManager securityManager;

	private MockMvc mockMvc;
	private FilterConfig filterConfig;

	RequestBuilder requestBuilder = null;
	Cookie globalCookie = new Cookie("session_id_cookie", "");

	@Before // 每启动一个@Test测试方法，都会执行一次before
	public void before() throws Exception {

		// 初始化web环境，模拟web.xml加载filterproxy
		DefaultMockMvcBuilder builders = MockMvcBuilders.webAppContextSetup(applicationContext);
		AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
		builders.addFilter(shiroFilter, "/*");
		mockMvc = builders.build();

		// 先登录模拟登录，并获取sessionId
		requestBuilder = post("/doLogin").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "ipple1986").param("password", "ipple1986");// 修改密码，使用例不通过
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Pattern pattern = Pattern.compile("session_id_cookie=([a-z0-9-]+);");
		System.out.println(result.getResponse().getCookies().length);
		Object cookieObject = result.getResponse().getHeaderValue("Set-Cookie");
		System.out.println(cookieObject);
		if (null != cookieObject && cookieObject instanceof String) {
			Matcher m = pattern.matcher((String) cookieObject);
			if (m.find()) {
				globalCookie.setValue(m.group(1));
			}
		}
		//确保cookies存在
		Assert.hasText(globalCookie.getValue());
	}

	@Test
	public void emptyTestCase() {

	}

	// 以下测试用户跳转页面，试着在方法加注解，让测试用例不通过
	@Test
	public void testGotoUserIndex() throws Exception {
		
		requestBuilder = get("/user/index").cookie(globalCookie);
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());// .andExpect(forwardedUrl("/WEB-INF/jsp/user/index.jsp"));
	}
	@Test
	public void testOnlineUser()throws Exception {
		
		requestBuilder = get("/online/list").cookie(globalCookie);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@After
	public void after() throws Exception {
		// 退出登录，清除session
		mockMvc.perform(get("/logout").cookie(globalCookie));
	}
}
