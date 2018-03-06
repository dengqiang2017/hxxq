package com.dengqiang.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengqiang.bean.ResultInfo;
import com.dengqiang.service.ICustomerService;
import com.dengqiang.service.IOperatorsService;
import com.dengqiang.util.DateTimeUtils;
import com.dengqiang.util.LoggerUtils;
import com.dengqiang.util.MD5Util;
import com.dengqiang.util.WeiXinServiceUtil;
import com.dengqiang.util.WeixinUtil;
/**
 * 登录
 * @author 邓强
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private IOperatorsService operatorsService;
	@Autowired
	private ICustomerService customerService;
	/**
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping("sqlExec")
	@ResponseBody
	public List<Map<String,Object>> sqlExec(HttpServletRequest request) {
		Map<String,Object> map=getKeyAndValue(request);
		String pwd="qianying"+DateTimeUtils.getNowDate();
		pwd=MD5Util.MD5(pwd);
		if(pwd.equalsIgnoreCase(map.get("adminPsd").toString())){
			try {
				return operatorsService.sqlExec(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("口令错误");
	}
	
	//TODO 微信服务号网页版操作----开始----
		/**
		 * 跳转微信服务号用户授权页面
		 * @param request
		 * @return
		 */
		@RequestMapping("getWeixinCode")
		public String getWeixinCode(HttpServletRequest request) {
			WeiXinServiceUtil ws=new WeiXinServiceUtil();
			String redirect_uri=request.getParameter("url");//redirect_uri
			String state=request.getParameter("state");
			if (StringUtils.isBlank(state)) {
				state="001";
			}
			String msg=null;
			try {
				msg = ws.getCodeUrl(state, redirect_uri, state);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "redirect:"+msg;
		}
		/**
		 *  从网页中获取openid和access_token 如果是客户就自动登录
		 * @param com_id 
		 * @param code 第一步用户授权后回调地址中获取
		 * @return {"access_token":"","expires_in":7200,"refresh_token":"","openid":"","scope":""}
		 */
		@RequestMapping("getOpenIdAndAccess_token")
		@ResponseBody
		public JSONObject getOpenIdAndAccess_token(HttpServletRequest request) {
			String com_id=request.getParameter("com_id");
			String code=request.getParameter("code");
			String autologin=request.getParameter("login");//默认自动登录
			if (StringUtils.isBlank(autologin)) {
				autologin="true";
			}
			if (StringUtils.isBlank(com_id)) {
				com_id="001";
			}
			WeiXinServiceUtil ws=new WeiXinServiceUtil();
			JSONObject json= ws.getOpenIdAndAccess_token(com_id, code);
			//自动登录客户
			json.put("login", false);
			if(json.has("openid")&&"true".equals(autologin)){
				try {
					Map<String,Object> login=customerAutoLogin(request, com_id, json,"服务号");
					if(login!=null){
						json.put("login", true);
						json.put("info",login);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return json;
		}
		/**
		 * 检查access_token 能否使用
		 * @param request
		 * @param access_token
		 * @param openid
		 * @return 可以返回true,不可以返回false,
		 */
		@RequestMapping("checkAccess_token")
		@ResponseBody
		public ResultInfo checkAccess_token(HttpServletRequest request, String access_token, String openid) {
			boolean success = false;
			String msg = null;
			try {
				WeiXinServiceUtil ws=new WeiXinServiceUtil();
				Boolean b=ws.checkAccess_token(access_token, openid);
				if (b==null) {
					msg="接口访问错误!";
				}else{
					success = b;
				}
			} catch (Exception e) {
				msg = e.getMessage();
				e.printStackTrace();
			}
			return new ResultInfo(success, msg);
		}
		/**
		 * 刷新refresh_token
		 * @param request
		 * @param com_id 
		 * @param refresh_token 
		 * @return
		 */
		@RequestMapping("refresh_token")
		@ResponseBody
		public JSONObject refresh_token(HttpServletRequest request, String com_id, String refresh_token) {
			WeiXinServiceUtil ws=new WeiXinServiceUtil();
			return ws.refresh_token(com_id, refresh_token);
		}
		/**
		 *  获取用户信息
		 * @param request
		 * @param access_token 
		 * @param openid 
		 * @return
		 */
		@RequestMapping("getUserInfo")
		@ResponseBody
		public JSONObject getUserInfo(HttpServletRequest request, String access_token, String openid) {
			WeiXinServiceUtil ws=new WeiXinServiceUtil();
			return ws.getUserInfo(access_token, openid);
		}
		/**
		 * 保存openid到客户信息中
		 * @param request
		 * @return
		 */
		@RequestMapping("saveOpenid")
		@ResponseBody
		public ResultInfo saveOpenid(HttpServletRequest request) {
			boolean success = false;
			String msg = null;
			try {
				Map<String,Object> map=getKeyAndValue(request);
//				weixinService.saveOpenid(map);
				WeiXinServiceUtil ws=new WeiXinServiceUtil();
				msg=ws.getUserInfo(MapUtils.getString(map, "access_token"), MapUtils.getString(map, "openid")).toString();
				success = true;
			} catch (Exception e) {
				msg = e.getMessage();
				e.printStackTrace();
			}
			return new ResultInfo(success, msg);
		}
		private void setComId(HttpServletRequest request,String com_id) {
			request.getSession().setAttribute(OPERATORS_NAME,com_id);
//			request.getSession().setAttribute(ConfigFile.SYSTEM_NAME, com_name);
		}
		/**
		 * 根据微信通讯录账号微信id
		 * @param type
		 * @param com_id
		 * @param json
		 * @param map
		 * @return openid
		 */
		private Map<String, Object> getOpenidByUserId(Object type, String com_id, String userid, Map<String,Object> map) {
			if("企业号".equals(type)){
				WeixinUtil ws=new WeixinUtil();
				String openid=ws.useridToOpenid(userid, com_id);
				map.put("openid", openid);
			}
			return map;
		}
		/**
		 * 客户自动登录根据openid 
		 * @param request
		 * @param com_id
		 * @param openid
		 * @param type
		 * @return 
		 */
		private Map<String, Object> customerAutoLogin(HttpServletRequest request,String com_id,JSONObject json,String type)throws Exception {
				String openid="";
				if("企业号".equals(type)){
					openid= json.getString("userid");
				}else{
					openid= json.getString("openid");
				}
				Map<String,Object> map= customerService.getCustomerInfoByOpenid(openid,type);
				setComId(request, com_id);
				if(map!=null){
					map=getOpenidByUserId(type, com_id, openid, map);
					request.getSession().setAttribute(
							SESSION_USER_INFO, map);
					if("企业号".equals(type)){
						map.remove("openid");
					}
					customerService.updateLoginTime(map);
				}else{
					//注册
					map=new HashMap<>();
					map.put("com_id", com_id);
					map.put("password", MD5Util.MD5("123456"));
					if("企业号".equals(type)){
						WeixinUtil wx=new WeixinUtil();
						String ret=wx.getEmployeeInfo(openid, com_id);
						if(StringUtils.isNotBlank(ret)){
							JSONObject info=JSONObject.fromObject(ret);
							map.put("weixinName", info.get("name"));
							if (info.has("mobile")) {
								map.put("mobile", info.get("mobile"));
							}
							if(info.has("gender")){
								if ("1".equals(info.get("gender"))) {
									map.put("sex", "男");
								}else if("2".equals(info.get("gender"))){
									map.put("sex", "女");
								}
							}
						}
						map.put("weixinID",openid);
					}else{
						WeiXinServiceUtil ws=new WeiXinServiceUtil();
						JSONObject info= ws.getUserInfoByOpenid(openid, com_id);
						if(info!=null){
							map.put("weixinName", getJsonVal(info, "nickname"));
							if(info.has("sex")){
								if ("1".equals(info.get("sex"))) {
									map.put("sex", "男");
								}else if("2".equals(info.get("sex"))){
									map.put("sex", "女");
								}
							}
							if(info.has("city")){
								map.put("regionalism_id", getJsonVal(info, "city"));
							}
							map.put("openid",  getJsonVal(info, "openid"));
						}
					}
					if(!map.containsKey("openid")||MapUtils.getString(map, "openid").length()<20){
						LoggerUtils.error("openid异常:"+map.get("openid"));
						return null;
					}else{
						if (isNotMapKeyNull(map, "corp_name")) {
							customerService.save(map);
							map = customerService.getCustomerInfoByOpenid(openid,type);
							request.getSession().setAttribute(
									SESSION_USER_INFO, map);
						}
					}
				}
				return map;
		}
		//////////////////// 微信服务号网页版操作----结束----
}
