package cn.jufuns.saas.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import cn.jufuns.core.mybatis.paginable.Pagination;
import cn.jufuns.saas.entity.SysModule;

@Mapper // 标记此类由MapperScannerConfigurer扫描成Mapper类
//@CacheNamespace // 命名空间缓存
public interface SysModuleMapper {

	//@Insert("")
	//@Options(useGeneratedKeys=true,keyProperty="module.id") //抽数据时，返回id
	
	//@Delete("delete 1","delete 2")//多个删除语句，写在一块
	
	@Select("select * from sys_module")
    public List<SysModule> findAll();
    
	@Select("select * from sys_module where module_code = #{name}")
    SysModule findOne(@Param("name")String name);
	
	
	//@Select("select * from sys_module")
	List<SysModule> findSysModulePage();
	
	//测试分页
	@Select("select * from sys_module ")
	List<SysModule> findSysModulePagination(@Param("page")Pagination page);
}