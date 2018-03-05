package com.dengqiang.service;

import java.util.Map;

public interface ICustomerService {

	Map<String, Object> getCustomerInfoByOpenid(String com_id, String id,
			String type);

	String updateLoginTime(Map<String, Object> map);

	String save(Map<String, Object> map);

}
