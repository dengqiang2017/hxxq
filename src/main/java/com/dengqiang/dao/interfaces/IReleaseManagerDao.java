package com.dengqiang.dao.interfaces;

import java.util.Map;

public interface IReleaseManagerDao {

	String saveNoticeInfo(Map<String, Object> map);

	String saveVoteInfo(Map<String, Object> map);

}
