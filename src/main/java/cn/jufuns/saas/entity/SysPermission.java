package cn.jufuns.saas.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SysPermission implements Serializable{
	private Long id;
	private String name;// url地址
	private String url;// url描述
	private String type;
	private Long pid;
	private int sort;
	private int mask;
	private String memo;
	private Date createTime;
	private Date updateTime;
	
	private String icon;
	  
	  
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getMask() {
		return mask;
	}

	public void setMask(int mask) {
		this.mask = mask;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return this.id + "\t" + this.name + "\t" + this.url + "\t" + this.pid + "\t" + this.sort + "\t" + this.mask + "\t" + this.memo + "\t" + this.createTime + "\t" +this.updateTime+ "\t" +this.icon;
	}

	
}
