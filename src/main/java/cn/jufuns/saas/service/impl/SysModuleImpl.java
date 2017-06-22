package cn.jufuns.saas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jufuns.core.mybatis.paginable.PageInfo;
import cn.jufuns.core.mybatis.paginable.Pagination;
import cn.jufuns.saas.dao.SysModuleMapper;
import cn.jufuns.saas.entity.SysModule;
import cn.jufuns.saas.service.ISysModule;

/**
 * 业务实现层
 * @author ipple1986
 *
 */
@Service
public class SysModuleImpl  implements ISysModule {

	@Autowired
	SysModuleMapper sysModuleMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public List<SysModule> find(String moduleCode) {
	
		List<SysModule> resultMap  = new ArrayList<SysModule>();
		SysModule o = sysModuleMapper.findOne(moduleCode);
		resultMap.add(o);
		return resultMap;
	}
	@Override
	public PageInfo<SysModule> findSysModulePage(String content, Integer pageNo, Integer pageSize) {
		Pagination pagination = new Pagination();
		PageInfo<SysModule> pageInfo = new PageInfo<SysModule>();
		pageInfo.setPagination(pagination);
		
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		pageInfo.setDatalist(sysModuleMapper.findSysModulePagination(pagination));
		
		return pageInfo;
	}
}
