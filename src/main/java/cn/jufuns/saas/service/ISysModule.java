package cn.jufuns.saas.service;

import java.util.List;

import cn.jufuns.core.mybatis.paginable.PageInfo;
import cn.jufuns.saas.entity.SysModule;
/**
 * 业务接口定义
 * @author ipple1986
 *
 */
public interface ISysModule {
	List<SysModule> find(String moduleCode);
	
	public PageInfo<SysModule> findSysModulePage(String content, Integer pageNo, Integer pageSize);
}
