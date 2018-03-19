package com.dengqiang.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.ResultInfo;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.service.ICustomerService;
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
	@Autowired
	private ICustomerService customerService;
	
	@Override
	public UserInfoBean getUserInfo(HttpServletRequest request) {
		UserInfoBean userInfo=super.getUserInfo(request);
		userInfo = customerService.getUserInfoById(userInfo);
		return userInfo;
	}
	
	/**
	 * 保存发布公告信息
	 * @param request
	 * @return 执行结果信息
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
			//TODO 根据用户id从数据库获取用户最新信息
//			boolean b=checkUserInfo(userInfo);
//			if (!b) {//TODO 测试期间允许非实名发布
//				msg="未实名认证,只能浏览不能发布信息!";
//			}else
			if (isMapKeyNull(map, "noticeTitle")) {
				msg="标题不能为空!";
			}else if(StringUtils.isBlank(founder)){
				msg="信息不完善不能发布信息!";
			}else if(userType==0){
				msg="权限不足,请联系管理员!";
			}else if(housingEstate==null){
				msg="没有获取到所属小区信息!";
			}else if(isMapKeyNull(map, "agree")||"false".equals(MapUtils.getString(map, "agree").toLowerCase())){
				msg="请阅读并确认《发布信息条款》";
			}else{
				if (isMapKeyNull(map, "showTime")) {
					map.put("showTime", getNow());
				}
				if (isMapKeyNull(map, "isComment")) {//是否允许评论留言默认是
					map.put("isComment", true);
				}
				if (isMapKeyNull(map, "isTop")) {//是否置顶
					map.put("isTop", false);
				}
				if (isMapKeyNull(map, "isShow")) {//是否显示
					map.put("isShow", true);
				}
				if (isMapKeyNull(map, "showName")) {//实名发布
					map.put("showName", false);
				}
				map.put("creationTime", getNow());
				map.put("founder", userInfo.getId());
				map.put("housingEstate", housingEstate.getId());
				Integer id=releaseManagerService.saveNoticeInfo(map);
				//开始保存附件
				if (id>0) {
					if (isNotMapKeyNull(map, "id")) {
						id=MapUtils.getInteger(map, "id");
					}
					File srcFile=new File(getRealPath(request)+"temp/"+userInfo.getId()+"/notice/");
					if (srcFile.exists()&&srcFile.isDirectory()) {
						File[] fs=srcFile.listFiles();
//						List<FileBean> beans=new ArrayList<>();
						for (File src : fs) {
							String img=userInfo.getId()+"/notice/"+id+"/"+src.getName();
							File destFile=new File(getRealPath(request)+img);
							mkdirsDirectory(destFile);
							FileUtils.moveFile(src, destFile);
//							FileBean fileBean=new FileBean();
//							fileBean.setFileName(src.getName());
//							if (img.endsWith("_sl")) {
//								fileBean.setFileType("notice缩略图");
//							}else{
//								fileBean.setFileType("notice");
//							}
//							fileBean.setFilePath(img);
//							beans.add(fileBean);
						}
//						releaseManagerService.saveFile(beans);
					}
					success = true;
				}else{
					msg="失败";
				}
				//end
			}
		} catch (Exception e) {
			msg = "系统执行错误!";
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	/**
	 * 检查用户是否实名认证 未认证的只能浏览,不能发布
	 * @param userInfo
	 * @return true-已认证,false-未认证
	 */
	private boolean checkUserInfo(UserInfoBean userInfo) {
		if (StringUtils.isBlank(userInfo.getIdentityCard())) {
			return false;
		}else if (userInfo.getIdentityCard().length()!=18) {
			return false;
		}else if(StringUtils.isBlank(userInfo.getFullName())){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 检查用户是否实名认证 未认证的只能浏览,不能发布
	 * @param request
	 * @return true-已认证,false-未认证
	 */
	@RequestMapping(value = "checkUserInfo", method = RequestMethod.GET)
	public @ResponseBody ResultInfo checkUserInfo(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			success = checkUserInfo(getUserInfo(request));
		} catch (Exception e) {
			msg = "系统执行错误!";
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	/**
	 * 保存投票信息
	 * @param request
	 * @return 执行结果信息
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
			//TODO 根据用户id从数据库获取用户最新信息
//			boolean b=checkUserInfo(userInfo);
//			if (!b) {//TODO 测试期间允许非实名发布
//				msg="未实名认证,只能浏览不能发布信息!";
//			}else 
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
			}else if(isMapKeyNull(map, "agree")||"false".equals(MapUtils.getString(map, "agree").toLowerCase())){
				msg="请阅读并确认《发布信息条款》";
			}else{
				if (isMapKeyNull(map, "beginTime")) {
					map.put("beginTime", getNow());
				}
				if (isMapKeyNull(map, "isComment")) {//是否允许评论留言默认是
					map.put("isComment", true);
				}
				if (isMapKeyNull(map, "showName")) {//实名发布,默认是
					map.put("showName", true);
				}
				map.put("creationTime", getNow());
				map.put("founder", userInfo.getId());
				map.put("housingEstate", housingEstate.getId());
				Integer id=releaseManagerService.saveVoteInfo(map);
				//开始保存附件
				if (id>0) {
					if (isNotMapKeyNull(map, "id")) {
						id=MapUtils.getInteger(map, "id");
					}
					File srcFile=new File(getRealPath(request)+"temp/"+userInfo.getId()+"/vote/");
					if (srcFile.exists()&&srcFile.isDirectory()) {
						File[] fs=srcFile.listFiles();
						for (File src : fs) {
							String img=userInfo.getId()+"/vote/"+id+"/"+src.getName();
							File destFile=new File(getRealPath(request)+img);
							mkdirsDirectory(destFile);
							FileUtils.moveFile(src, destFile);
						}
					}
					success = true;
				}else{
					msg="失败";
				}
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
	 * 审核发布的内容
	 * @param request
	 * @param id 数据ID
	 * @param auditStatus 审核状态
	 * @param type 审核数据类型 vote-投票,notice-公告,review-评论
	 * @return 执行结果信息
	 */
	@RequestMapping(value = "saveAuditorInfo", method = RequestMethod.POST)
	public @ResponseBody ResultInfo saveAuditorInfo(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			Map<String, Object> map = getKeyAndValue(request);
			UserInfoBean userinfo= getUserInfo(request);
			if (isMapKeyNull(map, "auditStatus")) {
				msg="审核状态不能为空!";
			}else if(userinfo!=null&&userinfo.getUserType()<2){
				msg="权限不足!";
			}else if (isMapKeyNull(map, "type")) {
				msg="审核类型不能为空!";
			}else if (isMapKeyNull(map, "id")) {
				msg="id不能为空!";
			}else {
				map.put("auditor", userinfo.getId());
				msg=releaseManagerService.saveAuditorInfo(map);
				success = true;
			}
		} catch (Exception e) {
			msg = "系统执行错误!";
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	/**
	 * 进入公告,投票发起页面时,现将该用户临时文件夹清空
	 * @param request
	 * @param type notice,vote
	 * @return
	 */
	@RequestMapping(value = "removeTempFile", method = RequestMethod.POST)
	public @ResponseBody ResultInfo removeTempFile(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			Map<String, Object> map = getKeyAndValue(request);
			UserInfoBean userinfo= getUserInfo(request);
			if (isMapKeyNull(map, "type")) {
				msg="类型不能为空";
			}else{
				File file=new File(getRealPath(request)+"temp/"+userinfo.getId()+"/"+map.get("type"));
				if (file.exists()&&file.isDirectory()) {
					log.info(file.getPath());
					try {
						FileUtils.deleteDirectory(file);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
				success = true;
			}
		} catch (Exception e) {
			msg = "系统执行错误!";
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	
	
	/**
	 * 移除临时文件
	 */
	@Scheduled(fixedRate = 1000*80)
	public void removeTempFile() {
//		File file=new File(getRealPath(request)+"temp/notice/");
		//通过递归获取文件夹中的文件创建时间
		//与当前时间进行比较,大于30分钟的移除掉
	}
}
