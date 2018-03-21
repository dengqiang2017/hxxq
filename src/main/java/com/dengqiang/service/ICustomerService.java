package com.dengqiang.service;

import java.util.List;
import java.util.Map;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.bean.UserInfoHousingBean;

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
	/**
	 * 保存自动登录时不存在的用户
	 * @param userInfo
	 * @return 返回用户id
	 */
	Integer saveAutoLogin(UserInfoBean userInfo);
	/**
	 * 根据id获取用户最新信息
	 * @param userInfo
	 * @return
	 */
	UserInfoBean getUserInfoById(UserInfoBean userInfo);
	/**
	 * 用户小区相关信息
	 * @param userInfo
	 * @return
	 */
	List<HousingEstateBean> getUserHousing(UserInfoBean userInfo);
	/**
	 * 获取小区信息
	 * @param userInfo
	 * @return
	 */
	UserInfoHousingBean getUserInfoHousingById(UserInfoBean userInfo);
}
