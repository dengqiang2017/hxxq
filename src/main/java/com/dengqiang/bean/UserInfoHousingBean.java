package com.dengqiang.bean;

import java.io.Serializable;
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
	
}
