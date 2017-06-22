package cn.jufuns.saas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jufuns.saas.dao.SysUserMapper;
import cn.jufuns.saas.entity.SysUser;
import cn.jufuns.saas.service.ISysUser;

/**
 * 业务实现层
 * 
 * @author ipple1986
 *
 */
@Service
public class SysUserImpl  implements ISysUser {

	@Autowired
	SysUserMapper sysUserMapper;

	@Override
	public SysUser queryByAccount(String username) {
		return sysUserMapper.queryByAccount(username);
	}

	@Override
	public void updateLastLoginTimeByUsername(String username) {
		sysUserMapper.updateLastLoginTimeByUsername(username);
	}

	@Override
	public SysUser selectByAccountAndPwd(String email, String pwd) {
		return sysUserMapper.selectByAccountAndPwd(email, pwd);
	}

	@Override
	public void updatePwdByAccount(String email, String pwd) {
		sysUserMapper.updatePwdByAccount(email, pwd);
	}

}
