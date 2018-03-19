package com.dengqiang.dao.interfaces;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dengqiang.bean.UserInfoBean;

public interface ICustomerDao {

	Map<String, Object> getCustomerInfoByOpenid(Map<String, Object> map);

	String updateLoginTime(Map<String, Object> map);

	String save(Map<String, Object> map);
	/**
	 * 根据mac地址获取用户信息,用于短时自动登录
	 * @param mac
	 * @return 用户信息
	 */
	UserInfoBean getUserInfoByMac(@Param("mac")String mac);
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

}
