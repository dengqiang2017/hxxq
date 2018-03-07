package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户小区相关信息
 * @author 邓强
 */
public class UserInfoHousingBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private HousingEstateBean housingEstate;//小区信息
	private UserInfoBean userInfo;//用户基本信息
	private int userType=0;//类型,0-普通业主,1-业委会成员
	private String userJob;//职位
	private String address;//小区具体位置
	private boolean defaultHousing;//设为默认小区
	//非普通业主,需要审核
	private int auditStatus=0;//审核状态,0-未审核,1-已审核,2-已失效
	private UserInfoBean auditor;//审核人
	private Date auditDate;//审核时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public HousingEstateBean getHousingEstate() {
		return housingEstate;
	}
	public void setHousingEstate(HousingEstateBean housingEstate) {
		this.housingEstate = housingEstate;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public UserInfoBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public UserInfoBean getAuditor() {
		return auditor;
	}
	public void setAuditor(UserInfoBean auditor) {
		this.auditor = auditor;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public boolean isDefaultHousing() {
		return defaultHousing;
	}
	public void setDefaultHousing(boolean defaultHousing) {
		this.defaultHousing = defaultHousing;
	}
	
}
