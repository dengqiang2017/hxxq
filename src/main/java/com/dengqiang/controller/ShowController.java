package com.dengqiang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengqiang.bean.NoticeBean;
import com.dengqiang.bean.PageList;
import com.dengqiang.bean.VoteBean;
import com.dengqiang.service.IShowService;

@Controller
@RequestMapping("showc")
public class ShowController extends BaseController {
	
	@Autowired
	private IShowService showService; 
	/**
	 * 获取公告分页
	 * @param request
	 * @return
	 */
	@RequestMapping("getNoticePage")
	@ResponseBody
	public PageList<NoticeBean> getNoticePage(HttpServletRequest request) {
		Map<String, Object> map=getKeyAndValue(request);
		if (isMapKeyNull(map, "rows")) {
			map.put("rows", 10);
		}
		if (isMapKeyNull(map, "page")) {
			map.put("page", 0);
		}
		if (isMapKeyNull(map, "housingEstate")) {
			throw new RuntimeException("没有获取到小区信息!");
		}
		return showService.getNoticePage(map);
	}

	@RequestMapping("getVotePage")
	@ResponseBody
	public PageList<VoteBean> getVotePage(HttpServletRequest request) {
		Map<String, Object> map=getKeyAndValue(request);
		if (isMapKeyNull(map, "rows")) {
			map.put("rows", 10);
		}
		if (isMapKeyNull(map, "page")) {
			map.put("page", 0);
		}
		if (isMapKeyNull(map, "housingEstate")) {
			throw new RuntimeException("没有获取到小区信息!");
		}
		map.put("userInfoId", getUserInfoId(request));
		return showService.getVotePage(map);
	}
	/**
	 * 
	 * @param request
	 */
	@RequestMapping("getNoticeInfo")
	@ResponseBody
	public NoticeBean getNoticeInfo(HttpServletRequest request) {
		Map<String, Object> map=getKeyAndValue(request);
		if (isMapKeyNull(map, "id")) {
			throw new RuntimeException("信息id不能为空!");
		}
		return showService.getNoticeInfo(map);
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getVoteInfo")
	@ResponseBody
	public VoteBean getVoteInfo(HttpServletRequest request) {
		Map<String, Object> map=getKeyAndValue(request);
		if (isMapKeyNull(map, "id")) {
			throw new RuntimeException("信息id不能为空!");
		}
		return showService.getVoteInfo(map);
	}
	
}
