package com.dengqiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.bean.UserInfoHousingBean;
import com.dengqiang.controller.BaseController;
import com.dengqiang.dao.interfaces.ICustomerDao;
import com.dengqiang.service.ICustomerService;

@Service
public class CustomerServiceImpl extends BaseController implements ICustomerService {
	public static Logger log = Logger.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public Map<String, Object> getCustomerInfoByOpenid(String openid, String type) {
		Map<String, Object> map=new HashMap<>();
		map.put("openid", openid);
		map.put("type", type);
		return customerDao.getCustomerInfoByOpenid(map);
	}

	@Override
	@Transactional
	public String updateLoginTime(Map<String, Object> map) {
		return customerDao.updateLoginTime(map)+"";
	}

	@Override
	@Transactional
	public String save(Map<String, Object> map) {
		return customerDao.save(map);
	}

	@Override
	public UserInfoBean getUserInfoByMac(String mac) throws Exception {
		 UserInfoBean userInfo= customerDao.getUserInfoByMac(mac);
		 if (userInfo!=null) {
			 Map<String, Object> map=new HashMap<>();
			 map.put("id", userInfo.getId());
			 map.put("loginTime", getNow());
			 customerDao.updateLoginTime(map);
			 return userInfo;
		}
		 return null;
	}
	@Override
	public Integer saveAutoLogin(UserInfoBean userInfo) {
		 
		return customerDao.saveAutoLogin(userInfo);
	}
	@Override
	public UserInfoBean getUserInfoById(UserInfoBean userInfo) {
		return customerDao.getUserInfoById(userInfo);
	}
	
	@Override
	public List<HousingEstateBean> getUserHousing(UserInfoBean userInfo) {
		
		return customerDao.getUserHousing(userInfo);
	}
	
	@Override
	public UserInfoHousingBean getUserInfoHousingById(UserInfoBean userInfo) {
		return customerDao.getUserInfoHousingById(userInfo);
	}
}
