package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 投票对象
 * @author 邓强
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class VoteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private HousingEstateBean housingEstate;//所属小区
	private String voteTitle;//投票标题
	private Date beginTime;//投票开始时间
	private Date endTime;//投票截止时间
	private boolean comment=true;//是否允许评论留言
	private boolean showName=true;//实名发起
	private String voteDesc;//投票描述
	private UserInfoBean founder;//发起人
	private Date creationTime;//创建时间
	private String bz;//备注
	private VoteHistoryBean voteHistory;//投票历史
	private List<FileBean> fileList;//附件名称列表
	private List<ReviewContentBean> reviewContents;//评论内容
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
	public HousingEstateBean getHousingEstate() {
		return housingEstate;
	}
	public void setHousingEstate(HousingEstateBean housingEstate) {
		this.housingEstate = housingEstate;
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
	public void setComment(int comment) {
		if (comment==1) {
			this.comment = true;
		}else{
			this.comment = false;
		}
	}
	public boolean isShowName() {
		return showName;
	}
	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	public void setShowName(int showName) {
		if (showName==0) {
			this.showName = false;
		}else{
			this.showName = true;
		}
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
	public List<FileBean> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileBean> fileList) {
		this.fileList = fileList;
	}
	public List<ReviewContentBean> getReviewContents() {
		return reviewContents;
	}
	public void setReviewContents(List<ReviewContentBean> reviewContents) {
		this.reviewContents = reviewContents;
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
	public VoteHistoryBean getVoteHistory() {
		return voteHistory;
	}
	public void setVoteHistory(VoteHistoryBean voteHistory) {
		this.voteHistory = voteHistory;
	}
}

