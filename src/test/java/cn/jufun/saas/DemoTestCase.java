package cn.jufun.saas;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.jufuns.core.mybatis.paginable.PageInfo;
import cn.jufuns.saas.controller.LoginController;
import cn.jufuns.saas.dao.SysModuleMapper;
import cn.jufuns.saas.entity.SysModule;
import cn.jufuns.saas.service.ISysModule;


/**
 * 一个单元测试的样例
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
public class DemoTestCase {
	
	
	@Autowired
	WebApplicationContext  applicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	
	//#####Dao层###########测试Mybatis的XXXMapper--开始######################
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	//通过sqlSessionFactory获取mapper,查询对象*/
	@Test
	public void testMapper1(){
		SqlSession session = sqlSessionFactory.openSession();
		SysModule sysModule = null;
		//方式1：
		sysModule = session.selectOne("cn.jufuns.saas.dao.SysModuleMapper.findOne","JKB_ADS");
		System.out.println(sysModule==null);
		//方式2：
		SysModuleMapper sysModuleMapper2 = session.getMapper(SysModuleMapper.class);
		sysModule = sysModuleMapper2.findOne("JKB_ADS");
		System.out.println(sysModule==null);
		
		session.close();
	}
	//通过注入方式，获取XXXMapper，查询对象
	//注：引用XXXMapper类，作为简单地测试，不带事务，事务注解在服务层
	@Autowired
	SysModuleMapper sysModuleMapper;
	@Rollback
	@Test
	public void testMapper2(){
		Assert.assertNotNull("sysModuleMapper不能为null",sysModuleMapper);
		//测试查询所有数据，并打印
		List<SysModule> resultMap = sysModuleMapper.findAll();
		System.out.println(resultMap.size());
		Assert.assertTrue(resultMap!=null && resultMap.size()>0);
		for(SysModule o:resultMap){
				System.out.println(o.getModuleCode()+"\t"+o.getModuleUri());
		}
		//测试查询单条数据，并打印
		SysModule  sysModule = sysModuleMapper.findOne("JKB_ADS");
		Assert.assertNotNull("sysModule不能null",sysModule);
		
		
	}
	//#####Dao层###########测试Mybatis的XXXMapper--结束######################
	
	
	//---------------------------------无情的分割线-----------------------------------
	
	
	//#####Service层###########测试Service层的SysModule服务--开始######################
	@Autowired
	ISysModule iSysModule;
	@Test
	public void testService(){
		iSysModule.find("JKB_ADS");
	}
	@Test//测试分页
	public void testPagination(){
		PageInfo<SysModule> pageInfo = iSysModule.findSysModulePage("%a%",1,20);
		
		System.out.println(String.format("共%s条记录",pageInfo.getPagination().getTotalCount() ));
		System.out.println(String.format("共%s页",pageInfo.getPagination().getTotalPage() ));
		System.out.println(String.format("当前第%s页",pageInfo.getPagination().getPageNo() ));
		System.out.println(String.format("每页%s条记录",pageInfo.getPagination().getPageSize() ));
		System.out.println(String.format("上一页%s",pageInfo.getPagination().getPrePage() ));
		System.out.println(String.format("下一页%s",pageInfo.getPagination().getNextPage() ));
		for(SysModule m:pageInfo.getDatalist()){
			System.out.println("pageInfo.datalist::\t"+m.getModuleCode());
		}
	}
	//#####Service层###########测试Service层的SysModule服务--结束######################
	
	//---------------------------------此处无银100两-----------------------------------

	//#####Controller层###########测试Controller层--开始######################
	//不建议的方式，直接注入XXXController，进行方法
	@Autowired
	LoginController indexController;
	@Test
	public void testIndexController(){
		//注意，在IndexController的index方法上加上@RequireRoles("")，调用些方法会抛未授权异常
		//indexController.index(null);
	}
	//建议使用Mockito MVC+Junit4进行测试，例子如下（不使用注入的userController）
	//@Test
	public void testRestfullUserController()throws Exception {
		// 测试UserController
				RequestBuilder request = null;

				// 1、get查一下user列表，应该为空
				request = get("/users/");
				mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

				// 2、post提交一个user
				request = post("/users/").param("id", "1").param("name", "测试大师").param("age", "20");
				mockMvc.perform(request).andExpect(content().string(equalTo("success")));

				// 3、get获取user列表，应该有刚才插入的数据
				request = get("/users/");
				mockMvc.perform(request).andExpect(status().isOk())
						.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

				// 4、put修改id为1的user
				request = put("/users/1").param("name", "测试终极大师").param("age", "30");
				mockMvc.perform(request).andExpect(content().string(equalTo("success")));

				// 5、get一个id为1的user
				request = get("/users/1");
				mockMvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

				// 6、del删除id为1的user
				request = delete("/users/1");
				mockMvc.perform(request).andExpect(content().string(equalTo("success")));

				// 7、get查一下user列表，应该为空
				request = get("/users/");
				mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
	}
	//#####Controller层###########测试Controller层--结束######################
	
	@Test
	public void testShiroLogin(){
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").param("username", "ipple1986@gmail.com").param("password", "ipple1986")).andReturn();
			String resultStr = result.getResponse().getContentAsString();
			
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testLogin() throws Exception{
	
		//void throws AuthorizationException
		//SecurityUtils.getSubject().checkPermission("");
		//SecurityUtils.getSubject().checkRole("");
		//boolean
	//	SecurityUtils.getSubject().hasRole("");
		//SecurityUtils.getSubject().isPermitted("");
		
		/*DefaultSecurityManager securityManager = new DefaultSecurityManager();
		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");  
        dataSource.setUrl("jdbc:mysql://60.205.204.19:3306/sys");  
        dataSource.setUsername("crm");  
        dataSource.setPassword("vvopdb");  
        
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setAuthenticationQuery("SELECT password FROM sec_user WHERE user_name = ?");
        
        System.out.println(jdbcRealm.getCacheManager()==null);
		
        securityManager.setRealm(jdbcRealm);
        
        SecurityUtils.setSecurityManager(securityManager);
		// 3.得到Subject
		Subject subject = SecurityUtils.getSubject();
		// 4.创建用户登录凭证
		UsernamePasswordToken token = new UsernamePasswordToken("ipple1986@gmail.com", "ipple1986");
		token.setRememberMe(true);
		// 5.登录，如果登录失败会抛出不同的异常，根据异常输出失败原因
		try {
			subject.login(token);
			// 6.判断是否成功登录
			assertEquals(true, subject.isAuthenticated());
			System.out.println("登录成功！！");
			// 判断用户是否拥有某个角色
			//assertEquals(true, subject.hasRole("admin"));
			// 使用Shiro自带的断言判断用户是否有被授权
			//subject.checkRole("manager");
			//subject.checkPermission("create_user1");
			// 7.注销用户
			subject.logout();
		} catch (IncorrectCredentialsException e) {
			System.out.println("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.");
		} catch (ExcessiveAttemptsException e) {
			System.out.println("登录失败次数过多");
		} catch (LockedAccountException e) {
			System.out.println("帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.");
		} catch (DisabledAccountException e) {
			System.out.println("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");
		} catch (ExpiredCredentialsException e) {
			System.out.println("帐号已过期. the account for username " + token.getPrincipal() + "  was expired.");
		} catch (UnknownAccountException e) {
			System.out.println("帐号不存在. There is no user with username of " + token.getPrincipal());
		} catch (UnauthorizedException e) {
			System.out.println("您没有得到相应的授权！" + e.getMessage());
		}*/
		//MvcResult  result=  mockMvc.perform(MockMvcRequestBuilders.post("/doLogin").param("username", "ipple1986@gmail.com").param("password", "ipple1986")).andDo(MockMvcResultHandlers.print()).andReturn();
		
	}
}
