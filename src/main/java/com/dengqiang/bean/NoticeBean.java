package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 公告文章
 * @author 邓强
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class NoticeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String noticeTitle;//标题
	private String noticeDesc;//描述
	private boolean comment=true;//是否允许评论留言
	private boolean showName=true;//实名发布
	private boolean top=true;//置顶
	private boolean show=true;//显示
	private UserInfoBean founder;//发布人
	private Date creationTime;//创建时间
	private Date showTime;//显示时间,提前编辑,定时发布
	private String bz;//备注
	private HousingEstateBean housingEstate;//所属小区
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
	public HousingEstateBean getHousingEstate() {
		return housingEstate;
	}
	public void setHousingEstate(HousingEstateBean housingEstate) {
		this.housingEstate = housingEstate;
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
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	public void setTop(int top) {
		if (top==0) {
			this.top = false;
		}else{
			this.top = true;
		}
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}
