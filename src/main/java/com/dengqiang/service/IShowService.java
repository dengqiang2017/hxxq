package com.dengqiang.service;

import java.util.Map;

import com.dengqiang.bean.NoticeBean;
import com.dengqiang.bean.PageList;
import com.dengqiang.bean.VoteBean;

public interface IShowService {

	NoticeBean getNoticeInfo(Map<String, Object> map);

	PageList<NoticeBean> getNoticePage(Map<String, Object> map);

	VoteBean getVoteInfo(Map<String, Object> map);
	
	PageList<VoteBean> getVotePage(Map<String, Object> map);


}
