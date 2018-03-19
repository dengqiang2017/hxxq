package com.dengqiang.service;

import java.util.Map;

public interface IReleaseManagerService {
	/**
	 * 保存公告信息
	 * @param map
	 * @return 公告id
	 * @throws Exception
	 */
	Integer saveNoticeInfo(Map<String, Object> map) throws Exception;
	/**
	 * 保存投票信息
	 * @param map
	 * @return 投票id
	 * @throws Exception
	 */
	Integer saveVoteInfo(Map<String, Object> map) throws Exception;
	/**
	 * 审核发布的内容
	 * @param map id 数据ID auditStatus 审核状态 type 审核数据类型 vote,notice,review
	 * @param id 数据ID
	 * @param auditStatus 审核状态
	 * @param type 审核数据类型 vote,notice,review
	 * @return
	 */
	String saveAuditorInfo(Map<String, Object> map) throws Exception;
}
