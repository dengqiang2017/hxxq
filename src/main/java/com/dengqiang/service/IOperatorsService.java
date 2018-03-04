package com.dengqiang.service;

import java.util.List;
import java.util.Map;


public interface IOperatorsService extends IBaseService  {
	/**
	 * 初始化数据表
	 * @param databaseName 
	 */
	void initTable(String dataname)throws Exception;

	List<Map<String, Object>> sqlExec(Map<String, Object> map)throws Exception;
	/**
	 * 初始化数据
	 * @param com_id
	 */
	void initData(String com_id)throws Exception;
}
