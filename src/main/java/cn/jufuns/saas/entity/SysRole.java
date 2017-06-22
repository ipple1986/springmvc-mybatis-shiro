package cn.jufuns.saas.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SysRole implements Serializable {

	private Long id;
	private String name;// 角色名称
	private String type;// 角色类型
	private Date createTime;
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return this.id + "\t" + this.name + "\t" + this.type + "\t" + this.createTime + "\t" +  this.updateTime;
	}
	
	

}
