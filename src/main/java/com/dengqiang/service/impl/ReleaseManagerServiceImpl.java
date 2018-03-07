package com.dengqiang.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.dao.interfaces.IReleaseManagerDao;
import com.dengqiang.service.IReleaseManagerService;
@Service
public class ReleaseManagerServiceImpl implements IReleaseManagerService {

	@Autowired
	private IReleaseManagerDao releaseManagerDao;
	
	@Override
	@Transactional
	public Integer saveNoticeInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return releaseManagerDao.saveNoticeInfo(map);
	}

	@Override
	@Transactional
	public Integer saveVoteInfo(Map<String, Object> map)  throws Exception{
		// TODO Auto-generated method stub
		return releaseManagerDao.saveVoteInfo(map);
	}

	@Override
	@Transactional
	public String saveAuditorInfo(Map<String, Object> map)  throws Exception{
		// TODO Auto-generated method stub
		Integer i=releaseManagerDao.saveAuditorInfo(map);
		if (i!=null&&i>0) {
			return "success";
		}
		return "fail";
	}
}
