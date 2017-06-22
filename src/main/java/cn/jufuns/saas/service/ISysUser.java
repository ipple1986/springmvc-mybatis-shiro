package cn.jufuns.saas.service;

import cn.jufuns.saas.entity.SysUser;

public interface ISysUser {

	public SysUser queryByAccount(String username);

	public void updateLastLoginTimeByUsername(String username);
	
	public SysUser selectByAccountAndPwd(String email,String pwd);
	
	void updatePwdByAccount(String email,String pwd);
}
