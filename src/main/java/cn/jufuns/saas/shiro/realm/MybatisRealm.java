package cn.jufuns.saas.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jufuns.saas.entity.SysUser;
import cn.jufuns.saas.service.impl.SysRolePermissionImpl;
import cn.jufuns.saas.service.impl.SysUserImpl;


public class MybatisRealm extends AuthorizingRealm {

	@Autowired
	SysUserImpl sysUserService;
	
	@Autowired
	SysRolePermissionImpl sysRolePermissionService;
	
	public MybatisRealm() {
		super();
	}
	//身份认证
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser sysUser = sysUserService.queryByAccount(token.getUsername());
		if(null == sysUser){
			throw new UnknownAccountException("帐号或密码不正确！");
	
		}else if("0".equalsIgnoreCase(sysUser.getStatus().toString())){
		
			throw new DisabledAccountException("帐号已经禁止登录！");
		}
		return new SimpleAuthenticationInfo(sysUser,sysUser.getPwd().toCharArray(), getName());
    }

	// 授权 
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
    	
    	SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		Set<String> roles = sysRolePermissionService.queryRoleByUserId(sysUser.getId());
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = sysRolePermissionService.queryPermissionByUserId(sysUser.getId());
		info.setStringPermissions(permissions);
        return info;  
    }  
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
	/**
	 * 指定principalCollection 清除
	 */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
