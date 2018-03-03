package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 浏览记录对象
 * @author 邓强
 */
public class BrowseRecordBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private NoticeBean notice;//公告信息
	private UserInfoBean userInfo;//浏览用户
	private Date browsingTime;//浏览时间
	private Long timeLen;//浏览时长
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public NoticeBean getNotice() {
		return notice;
	}
	public void setNotice(NoticeBean notice) {
		this.notice = notice;
	}
	public UserInfoBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
	public Date getBrowsingTime() {
		return browsingTime;
	}
	public void setBrowsingTime(Date browsingTime) {
		this.browsingTime = browsingTime;
	}
	public Long getTimeLen() {
		return timeLen;
	}
	public void setTimeLen(Long timeLen) {
		this.timeLen = timeLen;
	}
	
}
