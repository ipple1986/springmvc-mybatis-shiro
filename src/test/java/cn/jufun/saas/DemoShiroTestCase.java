package cn.jufun.saas;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.junit.Test;

import cn.jufuns.core.shiro.test.withoutlogin.WithoutLoginCredentialsMatcher;
import cn.jufuns.core.shiro.test.withoutlogin.WithoutLoginRealm;

/**
 * 一个单元测试的样例
 * 
 * @author ipple1986
 * @version 0.0.1
 */

public class DemoShiroTestCase {
	@Test
	public void generateRememberMeCipherKey() throws Exception{
		String str = "saas.jufuns.cn";
		System.out.println(Base64.encodeToString(str.getBytes()));
		System.out.println(Base64.decodeToString("c2Fhcy5qdWZ1bnMuY24="));
	}

	/**
	 * 测试不登录认证，授权判断
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWithoutLogin() throws Exception {

		MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();

		WithoutLoginCredentialsMatcher credentialsMatcher = new WithoutLoginCredentialsMatcher();

		WithoutLoginRealm realm = new WithoutLoginRealm();
		realm.setCredentialsMatcher(credentialsMatcher);

		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(realm);
		securityManager.setCacheManager(cacheManager);
		SecurityUtils.setSecurityManager(securityManager);

		// 伪登录
		SecurityUtils.getSubject().login(new UsernamePasswordToken("ipple1986", ""));
		// 判断登录
		System.out.println(SecurityUtils.getSubject().hasRole("admin"));
		//permission *
		System.out.println(SecurityUtils.getSubject().isPermitted("bee:add:aa,bb"));
		System.out.println(SecurityUtils.getSubject().isPermitted("bee:update"));

		
	}

}
