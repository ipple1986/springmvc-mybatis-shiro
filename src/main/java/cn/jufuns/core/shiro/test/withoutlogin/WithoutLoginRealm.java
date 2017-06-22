package cn.jufuns.core.shiro.test.withoutlogin;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;


public class WithoutLoginRealm extends AuthorizingRealm {


	
	public WithoutLoginRealm() {
		super();
	}
	//身份认证
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		//不作登录认证
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		return new SimpleAuthenticationInfo(token.getUsername(),null, getName());
    }

	// 授权 
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
    	
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		System.out.println(principals.getPrimaryPrincipal().toString());//传过来的用户名，用于查询权限角色时使用
		//根据用户ID查询角色（role），放入到Authorization里。
		Set<String> roles = new LinkedHashSet<String>();
		roles.add("admin");
		roles.add("bee");
		roles.add("*");
		info.setRoles(roles);
		
		Set<String> permissions = new LinkedHashSet<String>();
		permissions.add("user:list");
		permissions.add("role:list");
		permissions.add("role:allocate");
		permissions.add("permission:list");
		permissions.add("permission:allocate");
		
		permissions.add("*");
		//permissions.add("bee:add");
		//permissions.add("bee:update");
		//permissions.add("bee:query");
		//permissions.add("bee:delete");
		info.setStringPermissions(permissions);
        return info;  
    }  
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
