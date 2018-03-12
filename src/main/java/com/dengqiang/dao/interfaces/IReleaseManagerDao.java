package com.dengqiang.dao.interfaces;

import java.util.Map;
/**
 * 信息发布数据持久dao
 * @author 邓强
 *
 */
public interface IReleaseManagerDao {
	/**
	 * 增加公告信息
	 * @param map
	 * @return 公告id
	 * @throws Exception
	 */
	Integer saveNoticeInfo(Map<String, Object> map) throws Exception;
	/**
	 * 修改公告信息
	 * @param map
	 * @return
	 */
	Integer updateNoticeInfo(Map<String, Object> map);
	/**
	 * 增加投票信息
	 * @param map
	 * @return 投票id
	 * @throws Exception
	 */
	Integer saveVoteInfo(Map<String, Object> map) throws Exception;
	/**
	 * 修改投票信息
	 * @param map
	 * @return
	 */
	Integer updateVoteInfo(Map<String, Object> map);
	/**
	 * 审核发布的内容
	 * @param map
	 * @param id 数据ID
	 * @param auditStatus 审核状态
	 * @param type 审核数据类型 vote,notice,review
	 * @return 执行结果数
	 */
	Integer saveAuditorInfo(Map<String, Object> map) throws Exception;
	

}
