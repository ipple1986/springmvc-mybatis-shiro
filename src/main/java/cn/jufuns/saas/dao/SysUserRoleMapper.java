package cn.jufuns.saas.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jufuns.saas.entity.SysUserRole;

@Mapper//标记此类由MapperScannerConfigurer扫描成Mapper类
@CacheNamespace//命名空间缓存
public interface SysUserRoleMapper {

	@Insert("insert into sys_user_role (id ,uid, rid) values (null,#{uid,jdbcType=BIGINT}, #{rid,jdbcType=BIGINT})")
    void insert(SysUserRole sysUserRole);
	
	@Delete("delete from sys_user_role where uid =  #{id,jdbcType=BIGINT}")
	void deleteByUserId(long id);
	
	@Delete("delete from sys_user_role where uid in(#{userIds,jdbcType=VARCHAR})")
	void deleteRoleByUserIds(List<Long> userIds);
	 
	@Select("select uid from sys_user_role where rid =  #{id,jdbcType=BIGINT}")
	long findUserIdByRoleId(long id);
	  
	@Select("select * from sys_user_role where uid=#{uid,jdbcType=BIGINT} and rid=#{rid,jdbcType=BIGINT}")
	SysUserRole selectByUidAndRid(@Param("uid")Long uid,@Param("rid")Long rid);
	  
   
}