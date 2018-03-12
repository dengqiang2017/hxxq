package com.dengqiang.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.controller.BaseController;
import com.dengqiang.dao.interfaces.IReleaseManagerDao;
import com.dengqiang.service.IReleaseManagerService;
@Service
public class ReleaseManagerServiceImpl extends BaseController implements IReleaseManagerService {

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
}
