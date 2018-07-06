package com.dengqiang.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dengqiang.bean.NoticeBean;
import com.dengqiang.bean.PageList;
import com.dengqiang.bean.VoteBean;
import com.dengqiang.dao.interfaces.IShowDao;
import com.dengqiang.service.IShowService;
@Service
public class ShowServiceImpl extends BaseService implements IShowService {

	@Autowired
	private IShowDao showDao;
	
	@Override
	public NoticeBean getNoticeInfo(Map<String, Object> map) {
		NoticeBean noticeBean = showDao.getNoticeInfo(map);
		String img="notice/"+noticeBean.getFounder().getId()+"/"+noticeBean.getId()+"/";
		noticeBean.setFileList(getImgList(img));
		return noticeBean;
	}
	@Override
	public VoteBean getVoteInfo(Map<String, Object> map) {
		VoteBean voteBean = showDao.getVoteInfo(map);
		String img="vote/"+voteBean.getFounder().getId()+"/"+voteBean.getId()+"/";
		voteBean.setFileList(getImgList(img));
		return voteBean;
	}

	@Override
	public PageList<NoticeBean> getNoticePage(Map<String, Object> map) {
		Integer totalRecord=0;
		Integer pageRecord=MapUtils.getInteger(map, "rows");
		Integer currentPage=MapUtils.getInteger(map, "page");
		map.put("now", getNow());
		map.put("isShow", "true");
		map.put("auditStatus", 1);
		if (isMapKeyNull(map, "count")||"0".equals(map.get("count"))) {
			totalRecord=showDao.getNoticeCount(map);
		}
		PageList<NoticeBean> pages=new PageList<>(currentPage, pageRecord, totalRecord);
		List<NoticeBean> list= showDao.getNoticeList(map);
		pages.setRows(list);
		return pages;
	}
	
	@Override
	public PageList<VoteBean> getVotePage(Map<String, Object> map) {
		Integer totalRecord=0;
		Integer pageRecord=MapUtils.getInteger(map, "rows");
		Integer currentPage=MapUtils.getInteger(map, "page");
		map.put("now", getNow());
		map.put("auditStatus", 1);
		if (isMapKeyNull(map, "count")||"0".equals(map.get("count"))) {
			totalRecord=showDao.getVoteCount(map);
		}
		PageList<VoteBean> pages=new PageList<>(currentPage, pageRecord, totalRecord);
		List<VoteBean> list= showDao.getVoteList(map);
		pages.setRows(list);
		return pages;
	}

}
