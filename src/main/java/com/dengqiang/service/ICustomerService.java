package com.dengqiang.service;

import java.util.Map;

import com.dengqiang.bean.UserInfoBean;

public interface ICustomerService {

	Map<String, Object> getCustomerInfoByOpenid(String com_id, String id)throws Exception;

	String updateLoginTime(Map<String, Object> map)throws Exception;

	String save(Map<String, Object> map)throws Exception;
	/**
	 * 根据mac地址获取用户信息,用于短时自动登录
	 * @param mac
	 * @return 用户信息
	 */
	UserInfoBean getUserInfoByMac(String mac)throws Exception;

}
