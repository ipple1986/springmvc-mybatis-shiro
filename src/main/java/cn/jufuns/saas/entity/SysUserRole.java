package cn.jufuns.saas.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysUserRole implements Serializable{

	private Long id;
	private Long rid;
	private Long uid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
