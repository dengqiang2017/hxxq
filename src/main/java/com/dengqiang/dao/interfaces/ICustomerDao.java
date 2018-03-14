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

}
