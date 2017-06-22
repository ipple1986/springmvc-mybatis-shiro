package cn.jufuns.saas.dao;

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
import cn.jufuns.saas.entity.SysRole;

@Mapper // 标记此类由MapperScannerConfigurer扫描成Mapper类
@CacheNamespace // 命名空间缓存
public interface SysRoleMapper {

	@Insert("insert into sys_role(id,name,type,create_time,update_time)values(null,#{role.name,jdbcType=VARCHAR},#{role.type,jdbcType=VARCHAR},#{role.createTime,jdbcType=TIMESTAMP},#{role.updateTime,jdbcType=TIMESTAMP})")
	@Options(useGeneratedKeys = true, keyProperty = "role.id")
	void insert(@Param("role") SysRole role);

	@Update("update sys_role set name=#{role.name,jdbcType=VARCHAR},type=#{role.type,jdbcType=VARCHAR} ,update_time=now() where id=#{role.id,jdbcType=BIGINT}")
	void update(@Param("role") SysRole role);

	@Select("select *  from sys_role  where id=#{id,jdbcType=BIGINT}")
	SysRole selectById(@Param("id") Long id);
	
	/*@Select("select * from sys_role")
	List<SysRole> select();

	@Select("select * from sys_role where name like CONCAT('%', #{name,jdbcType=VARCHAR},'%')")
	List<SysRole> selectByName(@Param("name") String roleName);

	@Select("select count(0) from sys_role")
	Long count();

	@Select("select count(0) from sys_role where name like CONCAT('%', #{name,jdbcType=VARCHAR},'%') ")
	Long countByName(@Param("name") String roleName);*/

	@Select("select * from sys_role ")
	List<SysRole> selectPagination(@Param("page")Pagination page);
	
	@Select("select * from sys_role where name like CONCAT('%', #{name,jdbcType=VARCHAR},'%') ")
	List<SysRole> selectByNamePagination(@Param("page")Pagination page,@Param("name")String rolename);
	
	
	@Delete("delete from sys_role  where id=#{id,jdbcType=INTEGER}")
	void deleteById(@Param("id") Long id);
	
	
	
	
		

}