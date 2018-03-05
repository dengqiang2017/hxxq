package com.dengqiang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.ResultInfo;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.service.IReleaseManagerService;
/**
 * 信息发布
 * @author 邓强
 *
 */
@Controller
@RequestMapping("/releaseManager")
public class ReleaseManagerController extends BaseController {

	@Autowired
	private IReleaseManagerService releaseManagerService;
	
	/**
	 * 保存发布公告信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveNoticeInfo", method = RequestMethod.POST)
	public @ResponseBody ResultInfo saveNoticeInfo(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			Map<String, Object> map = getKeyAndValue(request);
			UserInfoBean userInfo=getUserInfo(request);
			String founder=userInfo.getUserName();
			int userType=userInfo.getUserType();
			HousingEstateBean housingEstate=getHousingEstate(request);
			if (isMapKeyNull(map, "noticeTitle")) {
				msg="标题不能为空!";
			}else if(StringUtils.isBlank(founder)){
				msg="信息不完善不能发布信息!";
			}else if(userType==0){
				msg="权限不足,请联系管理员!";
			}else if(housingEstate==null){
				msg="没有获取到所属小区信息!";
			}else{
				if (isMapKeyNull(map, "showTime")) {
					map.put("showTime", getNow());
				}
				if (isMapKeyNull(map, "comment")) {//是否允许评论留言默认是
					map.put("comment", true);
				}
				if (isMapKeyNull(map, "showName")) {//实名发布,默认是
					map.put("showName", true);
				}
				map.put("creationTime", getNow());
				map.put("founder", userInfo.getId());
				map.put("housingEstate", housingEstate.getId());
				msg=releaseManagerService.saveNoticeInfo(map);
				//开始保存附件
				
				//end
				success = true;
			}
		} catch (Exception e) {
			msg = "系统执行错误!";
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	/**
	 * 保存投票信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveVoteInfo", method = RequestMethod.POST)
	public @ResponseBody ResultInfo saveVoteInfo(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			Map<String, Object> map = getKeyAndValue(request);
			UserInfoBean userInfo=getUserInfo(request);
			String founder=userInfo.getUserName();
			int userType=userInfo.getUserType();
			HousingEstateBean housingEstate=getHousingEstate(request);
			if (isMapKeyNull(map, "voteTitle")) {
				msg="标题不能为空!";
			}else if(StringUtils.isBlank(founder)){
				msg="信息不完善不能发布信息!";
			}else if(userType==0){
				msg="权限不足,请联系管理员!";
			}else if(isMapKeyNull(map, "endTime")){
				msg="截止时间不能为空!";
			}else if(isMapKeyNull(map, "voteDesc")){
				msg="请为当前投票添加描述!";
			}else if(housingEstate==null){
				msg="没有获取到所属小区信息!";
			}else{
				if (isMapKeyNull(map, "beginTime")) {
					map.put("beginTime", getNow());
				}
				if (isMapKeyNull(map, "comment")) {//是否允许评论留言默认是
					map.put("comment", true);
				}
				if (isMapKeyNull(map, "showName")) {//实名发布,默认是
					map.put("showName", true);
				}
				map.put("creationTime", getNow());
				map.put("founder", userInfo.getId());
				map.put("housingEstate", housingEstate.getId());
				msg=releaseManagerService.saveVoteInfo(map);
				//开始保存附件
				
				//end
				success = true;
			}
		} catch (Exception e) {
			msg = "系统执行错误!";
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	
	
}
