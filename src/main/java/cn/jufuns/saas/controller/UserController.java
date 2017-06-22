package cn.jufuns.saas.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jufuns.core.shiro.session.mgt.SessionRepositoryManager;
import cn.jufuns.saas.entity.OnlineUser;
import cn.jufuns.saas.entity.SysUser;
import cn.jufuns.saas.service.ISysModule;
import cn.jufuns.saas.service.ISysUser;

@Controller
public class UserController {

	private static Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	ISysModule iSysModule;

	@Autowired
	ISysUser iSysUser;

	// 用户个人信息页面
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		//model.addAttribute("token", SecurityUtils.getSubject().getPrincipal());
		return "index";
	}

	// 修改密码
	@RequestMapping("/user/password")
	//@RequiresRoles("xxba")
	public String gopwd() throws Exception {
		return "user/password";
	}
	@RequestMapping("/user/doChangePassword")
	@ResponseBody
	public Map<String, String> updatepwd(String oldpwd,  String newpwd, String accept,Model model) {
		SysUser loginUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String status="0";
		String msg=""; 
		if(!StringUtils.hasText(oldpwd)){
			status = "1";
			msg="旧密码不能为空";
		}else if(!StringUtils.hasText(newpwd)){
			status = "2";
			msg = "新密码不能为空";
		}else if(!StringUtils.hasText(accept)){
			status = "3";
			msg = "确认密码不能为空";
		}else if (!newpwd.equals(accept)) {
			status = "4";
			msg = "新密码和确认密码不一致，请重新输入";
		} else if(newpwd.length()<6) {
			status = "2";
			msg="新密码长度应该不低于6位";
		}
					
		if ("0".equals(status)){
			// 根据帐号密码查找用户
			String md5str = DigestUtils.md5DigestAsHex(oldpwd.getBytes(Charset.forName("UTF-8")));
			SysUser sysUser = iSysUser.selectByAccountAndPwd(loginUser.getAccount(), md5str);
			if (null == sysUser) {// 找不到用户
				status = "2";
				msg="用户不存在";
			} else {
				String newmd5str = DigestUtils.md5DigestAsHex(newpwd.getBytes(Charset.forName("UTF-8")));
				sysUser.setPwd(newmd5str);
				iSysUser.updatePwdByAccount(loginUser.getAccount(), newmd5str);
				loginUser.setPwd(newmd5str);
				// SecurityUtils.getSubject().login(new
				// UsernamePasswordToken(email,newpwd));
			}
		}
		Map<String, String> data = new HashMap<String, String>();
		data.put("msg", msg);
		data.put("status", status);
		return data;
	}

	// 修改其他信息
	@RequestMapping("/user/goinf")
	public String goinf() {
		return "/user/inf";
	}

	@RequestMapping("/user/updateinf")
	public String updateinf(@ModelAttribute("sysUser") SysUser sysUser, HttpServletRequest request, Model model) {
		SysUser loginUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		if (!loginUser.getEmail().equals(sysUser.getEmail())) {
			// 非本人，不给修改
		} else {// 此处修改逻辑

		}

		model.addAttribute("token", SecurityUtils.getSubject().getPrincipal());
		return "user/index";
	}

	@Autowired
	SessionRepositoryManager sessionManager;

	/**
	 * 在线用户管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/online/list")
	@ResponseBody
	public List<OnlineUser> online() {
		List<OnlineUser> list = sessionManager.getAllUser();
		//return new ModelAndView("member/online", "list", list);
		return list;
	}

	/**
	 * 在线用户详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/online/detail/{sessionId}", method = RequestMethod.GET)
	@ResponseBody
	public OnlineUser onlineDetails(@PathVariable("sessionId") String sessionId) {
		OnlineUser bo = sessionManager.getSession(sessionId);
		//return new ModelAndView("member/onlineDetails", "bo", bo);
		return bo;
	}

	/**
	 * 改变Session状态
	 * 
	 * @param status
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value = "/online/changeSessionStatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeSessionStatus(Boolean status, String sessionIds) {
		return sessionManager.changeSessionStatus(status, sessionIds);
	}
}
