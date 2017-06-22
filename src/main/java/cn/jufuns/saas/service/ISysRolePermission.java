package cn.jufuns.saas.service;

import java.util.Set;

import cn.jufuns.core.mybatis.paginable.PageInfo;
import cn.jufuns.saas.entity.SysPermission;
import cn.jufuns.saas.entity.SysRole;

public interface ISysRolePermission {
	//角色CRUD接口
	SysRole saveRole(SysRole role);
	PageInfo<SysRole> listRole(String roleName,PageInfo<SysRole> pageInfo);
	boolean delRole(Long rid); 
	
	//授权信息查询接口
	Set<String> queryRoleByUserId(Long userId);
	Set<String> queryPermissionByUserId(Long userId);
	
	
	//权限CRUD接口
	SysPermission savePermission(SysPermission role);
	PageInfo<SysPermission> listPermission(String roleName,PageInfo<SysPermission> pageInfo);
	boolean delPermission(Long rid); 
	
	//授权接口
	boolean allocateRole(Long uid,Long rid);
	boolean allocatePermission(Long rid,Long pid);
}
