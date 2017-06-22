package cn.jufun.saas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class RolePermissionTestCase {

	@Autowired
	ShiroFilterFactoryBean shiroFilterFactoryBean;

	@Autowired
	private WebApplicationContext applicationContext;

	@Autowired
	DefaultWebSecurityManager securityManager;

	private MockMvc mockMvc;

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
		Object cookieObject = result.getResponse().getHeaderValue("Set-Cookie");
		if (null != cookieObject && cookieObject instanceof String) {
			Matcher m = pattern.matcher((String) cookieObject);
			if (m.find()) {
				globalCookie.setValue(m.group(1));
			}
		}
		//确保cookies存在
		Assert.hasText(globalCookie.getValue());
	}

	//测试角色增删查改
	@Test
	public void testRole()throws Exception {
		//添加一个sysrole对象,如果要作细化，可在controller将save方法拆分成add,update两个url请求
		/*MvcResult mvc = mockMvc.perform(post("/role/save").param("name", "rolename").param("type", "roletype").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//更新
		/*MvcResult mvc = mockMvc.perform(post("/role/save").param("id", "10").param("name", "rolename11").param("type", "roletype").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//查询分页列表
		/*MvcResult mvc = mockMvc.perform(post("/role/list").param("roleName", "role").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//删除
		/*MvcResult mvc = mockMvc.perform(post("/role/del").param("id", "9").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
	}
	@Test
	public void testPermission()throws Exception {
		//添加一个syspermission对象,如果要作细化，可在controller将save方法拆分成add,update两个url请求
		/*MvcResult mvc = mockMvc.perform(post("/permission/save").param("name", "permsname").param("url", "/a/b/c").param("type", "permstype").param("pid", "0").param("sort", "0").param("mask", "0").param("memo", "memo").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//更新
		/*MvcResult mvc = mockMvc.perform(post("/permission/save").param("id", "17").param("name", "permsname").param("url", "/a/b/c").param("type", "perms").param("pid", "0").param("sort", "0").param("mask", "0").param("memo", "memo").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//查询分页列表
		/*MvcResult mvc = mockMvc.perform(post("/permission/list").param("permsName", "角").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//删除
		/*MvcResult mvc = mockMvc.perform(post("/permission/del").param("id", "17").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
	}
	
	
	@Test
	public void testAllocate()throws Exception {
		
		//角色分配
		/*MvcResult mvc = mockMvc.perform(post("/role/alloc").param("uid", "1").param("rid", "14").cookie(globalCookie)).andReturn();
		System.out.println(mvc.getResponse().getContentAsString());*/
		//权限分配
		/*MvcResult mvc = mockMvc.perform(post("/permission/alloc").param("rid", "2").param("pid", "1").cookie(globalCookie)).andReturn();
		System.out.println("返回："+mvc.getResponse().getContentAsString());*/			
		
	}
	
	@After
	public void after() throws Exception {
		// 退出登录，清除session
		mockMvc.perform(get("/logout").cookie(globalCookie));
	}
}
