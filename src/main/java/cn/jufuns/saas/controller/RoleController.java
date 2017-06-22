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
import cn.jufuns.saas.entity.SysRole;
import cn.jufuns.saas.service.ISysRolePermission;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

	private static Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	ISysRolePermission iSysRolePermission;

	// 新加或修改角色
	@RequestMapping("/save")
	@ResponseBody
	public SysRole add(@ModelAttribute("role") SysRole role, HttpServletRequest request) {
		LOGGER.info("新加或修改角色");
		Date date = new Date();
		role.setCreateTime(date);
		role.setUpdateTime(date);
		role = iSysRolePermission.saveRole(role);
		return role;
	}

	// 分页查义角色列表
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<SysRole> list(String roleName) {
		LOGGER.info("分页查询角色列表");
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>();
		Pagination pagination = new Pagination();
		pagination.setPageNo(1);
		pagination.setPageSize(2);
		pageInfo.setPagination(pagination);
		PageInfo<SysRole> pageinfo = iSysRolePermission.listRole(roleName, pageInfo);
		return pageinfo;
	}

	// 根据id删除角色
	@RequestMapping("/del")
	@ResponseBody
	public boolean delete(Long id, HttpServletRequest request) {
		LOGGER.info("根据id删除角色" + id);
		return iSysRolePermission.delRole(id);
	}

	// 角色分配
	@RequestMapping("/alloc")
	@ResponseBody
	public boolean allocate(Long uid, Long rid, HttpServletRequest request) {
		return iSysRolePermission.allocateRole(uid, rid);
	}
}
