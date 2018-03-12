package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 消息对象
 * @author 邓强
 * 
 */
public class MsgBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String msgTitle;//消息标题
	private String MsgContent;//消息内容
	private boolean show=true;//是否显示
	private boolean read=false;//是否已读,单人时有效
	private Date creationTime;//创建时间
	private List<UserInfoBean>  userInfo;//为空表示所有人
	public MsgBean() {}
	public MsgBean(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return MsgContent;
	}
	public void setMsgContent(String msgContent) {
		MsgContent = msgContent;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public void setShow(int show) {
		if (show==0) {
			this.show = false;
		}else{
			this.show = true;
		}
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public void setRead(int read) {
		if (read==0) {
			this.read = false;
		}else{
			this.read = true;
		}
	}
	public List<UserInfoBean> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(List<UserInfoBean> userInfo) {
		this.userInfo = userInfo;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
