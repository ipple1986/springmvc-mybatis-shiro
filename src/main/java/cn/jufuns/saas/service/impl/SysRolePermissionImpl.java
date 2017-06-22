package cn.jufuns.saas.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jufuns.core.mybatis.paginable.PageInfo;
import cn.jufuns.saas.dao.SysPermissionMapper;
import cn.jufuns.saas.dao.SysRoleMapper;
import cn.jufuns.saas.dao.SysRolePermissionMapper;
import cn.jufuns.saas.dao.SysUserRoleMapper;
import cn.jufuns.saas.entity.SysPermission;
import cn.jufuns.saas.entity.SysRole;
import cn.jufuns.saas.entity.SysRolePermission;
import cn.jufuns.saas.entity.SysUserRole;
import cn.jufuns.saas.service.ISysRolePermission;

/**
 * 业务实现层
 * 
 * @author ipple1986
 *
 */
@Service
public class SysRolePermissionImpl implements ISysRolePermission {


	//角色CRUD接口
	@Autowired
	SysRoleMapper sysRoleMapper;
	@Override
	public SysRole saveRole(SysRole role) {
		Long id = null;
		if(role.getId()==null){
			sysRoleMapper.insert(role);		
			id = role.getId();
		}else{
			sysRoleMapper.update(role);
		}
		if(null == id){
			return null;
		}
		return sysRoleMapper.selectById(id);
	}

	@Override
	public PageInfo<SysRole> listRole(String roleName, PageInfo<SysRole> pageInfo) {
		List<SysRole> datalist = null;
		if(StringUtils.hasText(roleName)){
			datalist = sysRoleMapper.selectByNamePagination(pageInfo.getPagination(), roleName);
		}else{
			datalist = sysRoleMapper.selectPagination(pageInfo.getPagination());
		}
		pageInfo.setDatalist(datalist);
		return pageInfo;
	}

	@Override
	public boolean delRole(Long rid) {
		if(null == rid ){
			return Boolean.FALSE;
		}
		sysRoleMapper.deleteById(rid);
		return Boolean.TRUE;
	}

	
	//权限CRUD接口
	@Autowired
	SysPermissionMapper sysPermissionMapper;
	@Override
	public SysPermission savePermission(SysPermission perms) {
		Long id = null;
		if(perms.getId()==null){
			sysPermissionMapper.insert(perms);		
			id = perms.getId();
		}else{
			sysPermissionMapper.update(perms);
		}
		if(null == id){
			return null;
		}
		return sysPermissionMapper.selectById(id);
	}

	@Override
	public PageInfo<SysPermission> listPermission(String roleName, PageInfo<SysPermission> pageInfo) {
		List<SysPermission> datalist = null;
		if(StringUtils.hasText(roleName)){
			datalist = sysPermissionMapper.selectByNamePagination(pageInfo.getPagination(), roleName);
		}else{
			datalist = sysPermissionMapper.selectPagination(pageInfo.getPagination());
		}
		pageInfo.setDatalist(datalist);
		return pageInfo;
	}

	@Override
	public boolean delPermission(Long pid) {
		if(null == pid ){
			return Boolean.FALSE;
		}
		sysPermissionMapper.deleteById(pid);
		return Boolean.TRUE;
	}

	
	//授权接口
	@Autowired
	SysRolePermissionMapper sysRolePermissionMapper;
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;
	@Override
	public boolean allocateRole(Long uid, Long rid) {
		SysUserRole usrRole = sysUserRoleMapper.selectByUidAndRid(uid, rid);
		if(null != usrRole){
			return Boolean.FALSE;
		}
		usrRole = new SysUserRole();
		usrRole.setUid(uid);
		usrRole.setRid(rid);
		sysUserRoleMapper.insert(usrRole);
		return Boolean.TRUE;
	}

	@Override
	public boolean allocatePermission(Long rid, Long pid) {
		SysRolePermission rolePermission = sysRolePermissionMapper.selectByRidAndPid(rid,pid);
		if(null != rolePermission){
			return Boolean.FALSE;
		}
		rolePermission = new SysRolePermission();
		rolePermission.setRid(rid);
		rolePermission.setPid(pid);
		sysRolePermissionMapper.insert(rolePermission);
		return Boolean.TRUE;
	}

	//授权信息查询接口
	
	@Override
	public Set<String> queryRoleByUserId(Long userId) {
		return sysRolePermissionMapper.queryRoleByUserId(userId);
	}

	@Override
	public Set<String> queryPermissionByUserId(Long userId) {
		return sysRolePermissionMapper.queryPermissionByUserId(userId);
	}

	
}
