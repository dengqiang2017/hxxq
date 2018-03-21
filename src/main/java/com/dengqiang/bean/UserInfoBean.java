package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 用户基本信息
 * @author 邓强
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserInfoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;//用户名称/登录名称,身份证上名字,普通业主为空
	private String fullName;//身份证上名字,普通业主为空
	private String password;
	private String weixinName;//微信昵称
	private String weixin;//微信号
	private String openid;//微信openid
	private String identityCard;//身份证号用于发消息和发起投票时实名认证
	private String sex;//性别
	private String mobile;//电话号码/登录名称 
	private int userType=0;//0-普通用户业主,1-业委会成员,2-运营商合作者,3-管理员
	private String mac;//用户最近使用的mac/ip地址,用于自动登录
	private Date loginTime;//用户最近登录时间
	private List<UserInfoHousingBean> userInfoHousings;//用户小区相关信息
	private List<FileBean> fileList;//身份证图片等文件地址
	public UserInfoBean() {}
	public UserInfoBean(long id) {
		this.id=id;
	}
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWeixinName() {
		return weixinName;
	}
	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public List<UserInfoHousingBean> getUserInfoHousings() {
		return userInfoHousings;
	}
	public void setUserInfoHousings(
			List<UserInfoHousingBean> userInfoHousings) {
		this.userInfoHousings = userInfoHousings;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public List<FileBean> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileBean> fileList) {
		this.fileList = fileList;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
