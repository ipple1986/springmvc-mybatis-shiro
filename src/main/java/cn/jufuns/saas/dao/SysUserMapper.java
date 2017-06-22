package cn.jufuns.saas.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import cn.jufuns.saas.entity.SysUser;

@Mapper//标记此类由MapperScannerConfigurer扫描成Mapper类
//@CacheNamespace//命名空间缓存
public interface SysUserMapper {

	
    @Select("select * from sys_user where account = #{username} ")
    SysUser queryByAccount(@Param("username")String username);
    
    @Update("update sys_user set last_login_time = now() where email = #{username} ")
    void updateLastLoginTimeByUsername(@Param("username")String username);
  
    @Select("select * from sys_user where account = #{account,jdbcType=VARCHAR} and pwd = #{pwd,jdbcType=VARCHAR}")
	SysUser selectByAccountAndPwd(@Param("account")String account,@Param("pwd")String pwd);
 
	@Update("update sys_user set pwd=#{pwd,jdbcType=VARCHAR} where account = #{account,jdbcType=VARCHAR}")
	void updatePwdByAccount(@Param("account")String account,@Param("pwd")String pwd);
	
    
    String whereSql = "where  LOWER(nickname) like  LOWER(CONCAT('%',#{findContent,jdbcType=VARCHAR},'%')) or LOWER(email) like  LOWER(CONCAT('%',#{findContent,jdbcType=VARCHAR},'%') ";
    
    
    @Select("select * from sys_user ")
    List<SysUser> findAll();
  
    
    @Select("select * from sys_user " + (whereSql))
    List<SysUser> findAll2(@Param("findContent")String findContent);
    
    
    @Select("select count(1) from sys_user ")
    long findCount();
    
    @Select("select count(1) from sys_user " + whereSql)
    long findCount2(@Param("findContent")String findContent);
    
   
	
	
}