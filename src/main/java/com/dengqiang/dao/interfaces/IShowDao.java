package com.dengqiang.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.dengqiang.bean.NoticeBean;
import com.dengqiang.bean.VoteBean;

public interface IShowDao {

	NoticeBean getNoticeInfo(Map<String, Object> map);

	VoteBean getVoteInfo(Map<String, Object> map);

	Integer getNoticeCount(Map<String, Object> map);

	List<NoticeBean> getNoticeList(Map<String, Object> map);

	Integer getVoteCount(Map<String, Object> map);

	List<VoteBean> getVoteList(Map<String, Object> map);

}
