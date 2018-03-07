package com.dengqiang.service;

import java.util.Map;

public interface ICustomerService {

	Map<String, Object> getCustomerInfoByOpenid(String com_id, String id)throws Exception;

	String updateLoginTime(Map<String, Object> map)throws Exception;

	String save(Map<String, Object> map)throws Exception;

}
