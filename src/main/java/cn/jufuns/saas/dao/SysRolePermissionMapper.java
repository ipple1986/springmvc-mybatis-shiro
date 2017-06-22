package cn.jufuns.saas.dao;

import java.util.Set;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jufuns.saas.entity.SysRolePermission;

@Mapper // 标记此类由MapperScannerConfigurer扫描成Mapper类
@CacheNamespace // 命名空间缓存
public interface SysRolePermissionMapper {


	@Select("select r.name from sys_role r,sys_user_role ur where  ur.rid=r.id and ur.uid = #{uid,jdbcType=BIGINT} ")
	Set<String> queryRoleByUserId(@Param("uid") Long userId);

	@Select("select p.url from sys_permission p , sys_role_permission rp, sys_user_role ur where ur.uid =#{uid,jdbcType=BIGINT} and p.id = rp.pid and rp.rid = ur.rid")
	Set<String> queryPermissionByUserId(@Param("uid") Long userId);
	
	
	@Select("select * from  sys_role_permission where pid =  #{id,jdbcType=BIGINT}")
	SysRolePermission findRolePermissionByPid(Long id);

	@Select("select * from  sys_role_permission where rid =  #{id,jdbcType=BIGINT}")
	SysRolePermission findRolePermissionByRid(Long id);
  
	@Select("select * from  sys_role_permission where rid =  #{rid,jdbcType=BIGINT} and  pid =  #{pid,jdbcType=BIGINT}")
	SysRolePermission find(Long rid,Long pid);
	
	
	@Delete("delete from sys_role_permission where pid =  #{id,jdbcType=BIGINT}")
	void deleteByPid(Long id);
	@Delete("delete from sys_role_permission where rid =  #{id,jdbcType=BIGINT}")
	void deleteByRid(Long id);
	
	@Delete("delete from sys_role_permission where rid =  #{rid,jdbcType=BIGINT} and  pid =  #{pid,jdbcType=BIGINT}")
	void delete(Long rid,Long pid);

	@Delete("delete from sys_role_permission where rid in(#{roleIds,jdbcType=VARCHAR})")
	void deleteByRids(String roleIds);

	@Insert("insert into sys_role_permission (rid, pid) values (#{rid,jdbcType=BIGINT}, #{pid,jdbcType=BIGINT})")
    void insert(SysRolePermission sysRolePermission);
    
	@Select("select * from sys_role_permission where rid=#{rid,jdbcType=BIGINT} and pid= #{pid,jdbcType=BIGINT} limit 1")
	SysRolePermission selectByRidAndPid(@Param("rid")Long rid,@Param("pid")Long pid);

}