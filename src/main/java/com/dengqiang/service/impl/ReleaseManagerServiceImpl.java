package com.dengqiang.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.dao.interfaces.IReleaseManagerDao;
import com.dengqiang.service.IReleaseManagerService;

public class ReleaseManagerServiceImpl implements IReleaseManagerService {

	@Autowired
	private IReleaseManagerDao releaseManagerDao;
	
	@Override
	@Transactional
	public String saveNoticeInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return releaseManagerDao.saveNoticeInfo(map);
	}

	@Override
	@Transactional
	public String saveVoteInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return releaseManagerDao.saveVoteInfo(map);
	}

}
