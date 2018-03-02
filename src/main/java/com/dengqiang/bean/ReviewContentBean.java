package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论内容
 * @author 邓强
 *
 */
public class ReviewContentBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private int reviewType=0;//0-公告信息评论,1-投票内容评论
	private VoteBean vote;//投票信息
	private NoticeBean notice;//公告信息
	private String reviewContent;//评论留言内容
	private Date reviewTime;//评论时间
	private UserInfoBean userInfo;//评论用户
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public VoteBean getVote() {
		return vote;
	}
	public void setVote(VoteBean vote) {
		this.vote = vote;
	}
	public NoticeBean getNotice() {
		return notice;
	}
	public void setNotice(NoticeBean notice) {
		this.notice = notice;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	public UserInfoBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
	public int getReviewType() {
		return reviewType;
	}
	public void setReviewType(int reviewType) {
		this.reviewType = reviewType;
	}
}
