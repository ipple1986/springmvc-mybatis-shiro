package cn.jufuns.saas.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.jufuns.core.mybatis.paginable.Pagination;
import cn.jufuns.saas.entity.SysPermission;

@Mapper // 标记此类由MapperScannerConfigurer扫描成Mapper类
@CacheNamespace // 命名空间缓存
public interface SysPermissionMapper {
	
	@Insert("insert into sys_permission(id,name,url,type,pid,sort,mask,memo,create_time,update_time)"
			+ "values"
			+ "(null,#{perms.name,jdbcType=VARCHAR},#{perms.url,jdbcType=VARCHAR},"
			+ "#{perms.type,jdbcType=VARCHAR},#{perms.pid,jdbcType=BIGINT},"
			+ "#{perms.sort,jdbcType=INTEGER},#{perms.mask,jdbcType=INTEGER},#{perms.memo,jdbcType=VARCHAR},"
			+ "#{perms.createTime,jdbcType=TIMESTAMP},#{perms.updateTime,jdbcType=TIMESTAMP})")
	@Options(useGeneratedKeys = true, keyProperty = "perms.id")
	void insert(@Param("perms") SysPermission perms);

	@Update("update sys_permission set name=#{perms.name,jdbcType=VARCHAR},url=#{perms.url,jdbcType=VARCHAR},type=#{perms.type,jdbcType=VARCHAR}"
			+ ",pid=#{perms.pid,jdbcType=BIGINT},sort=#{perms.sort,jdbcType=INTEGER},mask=#{perms.mask,jdbcType=INTEGER}"
			+ ",memo=#{perms.memo,jdbcType=VARCHAR},update_time=now() where id=#{perms.id,jdbcType=BIGINT}")
	void update(@Param("perms") SysPermission perms);
	
	
	//要动态更新也可以写成xml
	@Update("update sys_permission set name=#{perms.name,jdbcType=VARCHAR} ,update_time=now() where id=#{perms.id,jdbcType=BIGINT}")
	void updateName(@Param("perms") SysPermission perms);

	@Select("select *  from sys_permission  where id=#{id,jdbcType=BIGINT}")
	SysPermission selectById(@Param("id") Long id);
	


	@Select("select * from sys_permission ")
	List<SysPermission> selectPagination(@Param("page")Pagination page);
	
	@Select("select * from sys_permission where name like CONCAT('%', #{name,jdbcType=VARCHAR},'%') ")
	List<SysPermission> selectByNamePagination(@Param("page")Pagination page,@Param("name")String rolename);
	
	
	@Delete("delete from sys_permission  where id=#{id,jdbcType=INTEGER}")
	void deleteById(@Param("id") Long id);
    

}