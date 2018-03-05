package com.dengqiang.service;

import java.util.Map;

public interface IReleaseManagerService {
	/**
	 * 保存公告信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String saveNoticeInfo(Map<String, Object> map) throws Exception;
	/**
	 * 保存投票信息
	 * @param map
	 * @return
	 */
	String saveVoteInfo(Map<String, Object> map);

}
