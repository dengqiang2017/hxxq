package com.dengqiang.dao.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dengqiang.dao.base.BaseDao;
@Repository
public interface IOperatorsDao extends BaseDao {
	
	/**
	 * 初始化数据表
	 * @param databaseName 
	 */
	void initTable(@Param("dataname")String dataname);
	/**
	 * 初始化数据
	 * @param com_id
	 */
	void initData(@Param("com_id")String com_id);
	
	void alterField();
	
	List<Map<String, Object>> sqlExec(Map<String, Object> map);
	

}
