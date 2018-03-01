package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 公告文章
 * @author 邓强
 *
 */
public class NoticeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String noticeTitle;//标题
	private String noticeDesc;//描述
	private boolean comment=true;//是否允许评论留言
	private boolean showName=true;//实名发布
	private UserInfoBean founder;//发布人
	private Date creationTime;//创建时间
	private Date showTime;//显示时间,提前编辑,定时发布
	private List<String> fileList;//附件名称列表
	private List<ReviewContentBean> reviewContents;//评论内容
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeDesc() {
		return noticeDesc;
	}
	public void setNoticeDesc(String noticeDesc) {
		this.noticeDesc = noticeDesc;
	}
	public boolean isComment() {
		return comment;
	}
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	public boolean isShowName() {
		return showName;
	}
	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	public UserInfoBean getFounder() {
		return founder;
	}
	public void setFounder(UserInfoBean founder) {
		this.founder = founder;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public List<String> getFileList() {
		return fileList;
	}
	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	public List<ReviewContentBean> getReviewContents() {
		return reviewContents;
	}
	public void setReviewContents(List<ReviewContentBean> reviewContents) {
		this.reviewContents = reviewContents;
	}
	
}
