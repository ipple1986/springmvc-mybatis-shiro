package cn.jufuns.saas.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysModule implements Serializable{



	private String moduleCode;
	private String moduleName;
	private String moduleOrder;
	private String moduleType;
	private String parentCode;
	private String moduleDesc;
	private String moduleUri;
	private Integer moduleView;
	private String moduleOdr;
	private String icoPath;
	private String imgname;
	private String ispad;
	private String secondShow;
	private String usualShow;
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleOrder() {
		return moduleOrder;
	}
	public void setModuleOrder(String moduleOrder) {
		this.moduleOrder = moduleOrder;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getModuleUri() {
		return moduleUri;
	}
	public void setModuleUri(String moduleUri) {
		this.moduleUri = moduleUri;
	}
	public Integer getModuleView() {
		return moduleView;
	}
	public void setModuleView(Integer moduleView) {
		this.moduleView = moduleView;
	}
	public String getModuleOdr() {
		return moduleOdr;
	}
	public void setModuleOdr(String moduleOdr) {
		this.moduleOdr = moduleOdr;
	}
	public String getIcoPath() {
		return icoPath;
	}
	public void setIcoPath(String icoPath) {
		this.icoPath = icoPath;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getIspad() {
		return ispad;
	}
	public void setIspad(String ispad) {
		this.ispad = ispad;
	}
	public String getSecondShow() {
		return secondShow;
	}
	public void setSecondShow(String secondShow) {
		this.secondShow = secondShow;
	}
	public String getUsualShow() {
		return usualShow;
	}
	public void setUsualShow(String usualShow) {
		this.usualShow = usualShow;
	}
	@Override
	public String toString() {
		return this.moduleCode+"\t"+
				this.moduleName+"\t"+
		this.moduleOrder+"\t"+
		this.moduleType+"\t"+
		this.parentCode+"\t"+
		this.moduleDesc+"\t"+
		this.moduleUri+"\t"+
		this.moduleView+"\t"+
		this.moduleOdr+"\t"+
		this.icoPath+"\t"+
		this.imgname+"\t"+
		this.ispad+"\t"+
		this.secondShow+"\t"+
		this.usualShow;
	}
	
	
}
