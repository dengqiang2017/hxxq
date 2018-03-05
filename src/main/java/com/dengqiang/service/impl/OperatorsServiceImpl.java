package com.dengqiang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dengqiang.dao.interfaces.IOperatorsDAO;
import com.dengqiang.service.IOperatorsService;

@Service
public class OperatorsServiceImpl implements
		IOperatorsService {

	@Autowired
	private IOperatorsDAO operatorsDao;
	@Override
	@Transactional
	public void save(Map<String, Object> map) {}

	@Override
	@Transactional
	public void update(Map<String, Object> map) {}

	@Override
	@Transactional
	public void delete(Long id) {}

	@Override
	public Map<String, Object> get(Long id) {return null;}

	@Override
	public List<Map<String, Object>> getAll() {
		return operatorsDao.getAll();
	}
	@Override
	public List<Map<String, Object>> findBySql(Map<String, Object> map) {
		return null;
	}

	@Override
	@Transactional
	public void initTable(String dataname)throws Exception {
			operatorsDao.initTable(dataname);
//			operatorsDao.alterField();
	}
	
	@Override
	@Transactional
	public List<Map<String, Object>> sqlExec(Map<String, Object> map)throws Exception {
		 
		return operatorsDao.sqlExec(map);
	}
	@Override
	@Transactional
	public void initData(String com_id) throws Exception {
		operatorsDao.initData(com_id);
	}
}
