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
	private String bz;//备注
	private UserInfoBean userInfo;//评论用户
	//通过审核后才能显示
	private int auditStatus=0;//审核状态,0-未审核,1-已审核,2-已失效
	private UserInfoBean auditor;//审核人
	private Date auditDate;//审核时间
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}
