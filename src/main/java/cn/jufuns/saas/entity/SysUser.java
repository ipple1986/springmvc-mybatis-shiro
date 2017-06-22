package cn.jufuns.saas.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SysUser implements Serializable{
	private Long id;
	private String account;
	private String nickname;
	private String email;
	private String pwd;
	private Date createTime;
	private Date lastLoginTime;
	private Integer status;

	public SysUser(){
		
	}
	
	public SysUser(SysUser user){
		id = user.getId();
		account = user.getAccount();
		nickname = user.getNickname();
		email = user.getEmail();
		pwd = user.getPwd();
		createTime = user.getCreateTime();
		lastLoginTime = user.getLastLoginTime();
		status = user.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		
		return this.id + "\t" + this.account + "\t" + this.nickname + "\t" + this.email + "\t" + this.status + "\t" + this.pwd + "\t" + this.createTime + "\t" + this.lastLoginTime;
	}
	

}
