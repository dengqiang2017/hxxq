package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 投票对象
 * @author 邓强
 *
 */
public class VoteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private HousingEstateBean com_id;//所属小区代码
	private String voteTitle;//投票标题
	private Date beginTime;//投票开始时间
	private Date endTime;//投票截止时间
	private boolean comment=true;//是否允许评论留言
	private boolean showName=true;//实名发起
	private String voteDesc;//投票描述
	private UserInfoBean founder;//发起人
	private Date creationTime;//创建时间
	private List<String> fileList;//附件名称列表
	private List<ReviewContentBean> reviewContents;//评论内容
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public HousingEstateBean getCom_id() {
		return com_id;
	}
	public void setCom_id(HousingEstateBean com_id) {
		this.com_id = com_id;
	}
	public String getVoteTitle() {
		return voteTitle;
	}
	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	public String getVoteDesc() {
		return voteDesc;
	}
	public void setVoteDesc(String voteDesc) {
		this.voteDesc = voteDesc;
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
