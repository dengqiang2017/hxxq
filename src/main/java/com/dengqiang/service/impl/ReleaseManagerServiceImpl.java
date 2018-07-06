package com.dengqiang.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.bean.FileBean;
import com.dengqiang.bean.NoticeBean;
import com.dengqiang.bean.PageList;
import com.dengqiang.bean.VoteBean;
import com.dengqiang.dao.interfaces.IReleaseManagerDao;
import com.dengqiang.service.IReleaseManagerService;
@Service
public class ReleaseManagerServiceImpl extends BaseService implements IReleaseManagerService {

	@Autowired
	private IReleaseManagerDao releaseManagerDao;
	
	@Override
	@Transactional
	public Integer saveNoticeInfo(Map<String, Object> map) throws Exception {
		// TODO 保存公告发布
		if (isMapKeyNull(map, "id")) {//id为空增加
			return releaseManagerDao.saveNoticeInfo(map);
		}else{
			releaseManagerDao.updateNoticeInfo(map);
			return Integer.parseInt(map.get("id").toString());
		}
	}

	@Override
	@Transactional
	public Integer saveVoteInfo(Map<String, Object> map)  throws Exception{
		// TODO 保存投票发起
		if (isMapKeyNull(map, "id")) {
			return releaseManagerDao.saveVoteInfo(map);
		}else{
			releaseManagerDao.updateVoteInfo(map);
			return Integer.parseInt(map.get("id").toString());
		}
	}

	@Override
	@Transactional
	public String saveAuditorInfo(Map<String, Object> map)  throws Exception{
		// TODO 审核发布的内容
		Integer i=releaseManagerDao.saveAuditorInfo(map);
		if (i!=null&&i>0) {
			return "success";
		}
		return "fail";
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
			totalRecord=releaseManagerDao.getNoticeCount(map);
		}
		PageList<NoticeBean> pages=new PageList<>(currentPage, pageRecord, totalRecord);
		List<NoticeBean> list= releaseManagerDao.getNoticeList(map);
		for (Iterator<NoticeBean> iterator = list.iterator(); iterator.hasNext();) {
			NoticeBean noticeBean = iterator.next();
			String img="notice/"+noticeBean.getFounder().getId()+"/"+noticeBean.getId()+"/";
			File src=new File(getRealPath(getRequest())+img);
			if (src.exists()&&src.isDirectory()) {
				File[] srcList=src.listFiles();
				List<FileBean> fileList=new ArrayList<>();
				for (File srcFile : srcList) {
					FileBean bean=new FileBean();
					bean.setFilePath(img+srcFile.getName());
					bean.setFileName(srcFile.getName());
					fileList.add(bean);
				}
				noticeBean.setFileList(fileList);
			}
		}
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
			totalRecord=releaseManagerDao.getVoteCount(map);
		}
		PageList<VoteBean> pages=new PageList<>(currentPage, pageRecord, totalRecord);
		List<VoteBean> list= releaseManagerDao.getVoteList(map);
		pages.setRows(list);
		return pages;
	}
}
