package com.dengqiang.dao.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.bean.UserInfoHousingBean;
@Repository
public interface ICustomerDao {

	Map<String, Object> getCustomerInfoByOpenid(Map<String, Object> map);

	Integer updateLoginTime(Map<String, Object> map);

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
	/**
	 * 用户小区相关信息
	 * @param userInfo
	 * @return
	 */
	List<HousingEstateBean> getUserHousing(UserInfoBean userInfo);
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	UserInfoHousingBean getUserInfoHousingById(UserInfoBean userInfo);

}
