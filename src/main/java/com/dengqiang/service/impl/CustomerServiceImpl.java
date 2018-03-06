package com.dengqiang.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.dao.interfaces.ICustomerDao;
import com.dengqiang.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public Map<String, Object> getCustomerInfoByOpenid(String openid, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<>();
		map.put("openid", openid);
		map.put("type", type);
		return customerDao.getCustomerInfoByOpenid(map);
	}

	@Override
	@Transactional
	public String updateLoginTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return customerDao.updateLoginTime(map);
	}

	@Override
	@Transactional
	public String save(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return customerDao.save(map);
	}

}
