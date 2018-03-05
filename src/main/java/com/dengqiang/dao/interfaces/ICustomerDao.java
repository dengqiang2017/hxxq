package com.dengqiang.dao.interfaces;

import java.util.Map;

public interface ICustomerDao {

	Map<String, Object> getCustomerInfoByOpenid(Map<String, Object> map);

	String updateLoginTime(Map<String, Object> map);

	String save(Map<String, Object> map);

}
