package cn.jufuns.saas.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jufuns.core.mybatis.paginable.PageInfo;
import cn.jufuns.core.mybatis.paginable.Pagination;
import cn.jufuns.saas.entity.SysPermission;
import cn.jufuns.saas.service.ISysRolePermission;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {

	private static Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	ISysRolePermission iSysRolePermission;

	// 新加或修改角色
	@RequestMapping("/save")
	@ResponseBody
	public SysPermission add(@ModelAttribute("perms") SysPermission perms, HttpServletRequest request) {
		LOGGER.info("新加或修改权限");
		Date date = new Date();
		perms.setCreateTime(date);
		perms.setUpdateTime(date);
		perms = iSysRolePermission.savePermission(perms);
		return perms;
	}

	// 分页查义角色列表
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<SysPermission> list(String permsName) {
		LOGGER.info("分页查询权限列表");
		PageInfo<SysPermission> pageInfo = new PageInfo<SysPermission>();
		Pagination pagination = new Pagination();
		pagination.setPageNo(1);
		pagination.setPageSize(2);
		pageInfo.setPagination(pagination);
		PageInfo<SysPermission> pageinfo = iSysRolePermission.listPermission(permsName, pageInfo);
		return pageinfo;
	}

	// 根据id删除角色
	@RequestMapping("/del")
	@ResponseBody
	public boolean delete(Long id, HttpServletRequest request) {
		LOGGER.info("根据id删除权限" + id);
		return iSysRolePermission.delPermission(id);
	}
	// 权限分配
	@RequestMapping("/alloc")
	@ResponseBody
	public boolean allocate(Long rid,Long pid, HttpServletRequest request) {
		return iSysRolePermission.allocatePermission(rid, pid);
	}
}
