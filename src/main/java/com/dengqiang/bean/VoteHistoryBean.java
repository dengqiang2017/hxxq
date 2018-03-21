package com.dengqiang.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 投票历史
 * @author 邓强
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class VoteHistoryBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private VoteBean vote;//投票id
	private boolean voteResult;//投票状态
	private String comment;//留言内容
	private int commentState=0;//留言状态0-审核中,1-审核通过,2-不通过
	private UserInfoBean userInfo;//投票人
	private Date voteTime;//投票时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VoteBean getVote() {
		return vote;
	}
	public void setVoteId(VoteBean vote) {
		this.vote = vote;
	}
	public boolean isVoteResult() {
		return voteResult;
	}
	public void setVoteResult(boolean voteResult) {
		this.voteResult = voteResult;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCommentState() {
		return commentState;
	}
	public void setCommentState(int commentState) {
		this.commentState = commentState;
	}
	public UserInfoBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
	public Date getVoteTime() {
		return voteTime;
	}
	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}
	public void setVote(VoteBean vote) {
		this.vote = vote;
	}
	
}
