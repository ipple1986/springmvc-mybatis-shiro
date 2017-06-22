package cn.jufuns.saas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jufuns.saas.service.impl.SysUserImpl;

@Controller
public class LoginController {
	private static Logger LOGGER = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping(value={"","/","/login"})
	public String index(){
		
		return "login";
	}
	
	@Autowired
	SysUserImpl sysUserService; 
	
	@RequestMapping("/doLogin")//不走authc过滤器,仿造FormAuthenticationFilter过滤器，做登录
	public String login(String username,String password,HttpServletRequest request,Model model){
		
		if(SecurityUtils.getSubject().getPrincipal()!=null){//已经登录，直接跳转主页
			LOGGER.info("已经登录，直接跳转主页");
			return "redirect:/index";
		}
		
		String errorMsg  = "";
		if(!StringUtils.hasText(username)){
			errorMsg  = "帐号不能为空！";
		}else if(!StringUtils.hasText(password)){
			errorMsg  = "密码不能为空！";
		}else{
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password,false,request.getRemoteHost());
			try {
				subject.login(token);
				sysUserService.updateLastLoginTimeByUsername(token.getUsername());
			} catch (IncorrectCredentialsException e) {
				errorMsg = ("登录密码错误！");
			} catch (DisabledAccountException e) {
				errorMsg=("帐号已被禁用！");
			} catch (UnknownAccountException e) {
				errorMsg = ("帐号不存在！");
			} catch (UnauthorizedException e) {
				errorMsg = ("您没有得到相应的授权！" + e.getMessage());
			}
		}
		if(StringUtils.hasText(errorMsg)){
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}else{
			//在此处加入session
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", SecurityUtils.getSubject().getPrincipal());
			return "redirect:/index";
		}
	}
	@RequestMapping("/logout")//不走过滤器
	public String logout(String username,String password,HttpServletRequest request,Model model){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/";
	}
	

}