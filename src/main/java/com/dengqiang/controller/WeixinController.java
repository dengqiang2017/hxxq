package com.dengqiang.controller;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengqiang.bean.ResultInfo;
import com.dengqiang.util.Sign;
import com.dengqiang.util.WeiXinServiceUtil;
import com.dengqiang.util.WeixinUtil;
import com.qq.weixin.mp.aes.AesException;

@Controller
@RequestMapping("/weixin")
@Component
public class WeixinController extends BaseController{
	/**
	 *  
	 * @param request
	 * @return
	 * @throws AesException 
	 */
	@RequestMapping("weixinfw")
	@ResponseBody
	public String weixinfw(HttpServletRequest request) throws AesException {
		Map<String, Object> map = getKeyAndValue(request);
		log.info(map);
		String sEchoStr = request.getParameter("echostr");// "P9nAzCzyDtyTWESHep1vC5X9xho/qYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp+4RPcs8TgAE7OaBO+FZXvnaqQ==";
		return sEchoStr;
	}

	/**
	 * 取得签名signature
	 * 
	 * @param timestamp
	 * @param nonceStr
	 * @param url
	 * @return
	 */
	@RequestMapping("getSignature")
	@ResponseBody
	public String getSignature(HttpServletRequest request) {
		String timestamp =null;// request.getParameter("timestamp");
		String nonceStr = "dengqiang";
		String url = request.getParameter("url");
		WeixinUtil wei=new WeixinUtil();
		if (StringUtils.isBlank(timestamp)) {
			timestamp = new Date().getTime() + "";
			timestamp=timestamp.substring(0, timestamp.length()-3);
		}
		String jsapi_ticket=wei.jsapi_ticket(getComId());
		String da=request.getParameter("type");
		Map<String,String> map=null;
		if (da!=null) {
			  map=Sign.signDa(jsapi_ticket, nonceStr, timestamp, url);
		}else{
			  map=Sign.sign(jsapi_ticket, nonceStr, timestamp, url);
		}
		return map.get("signature")+","+map.get("timestamp")+","+WeixinUtil.getWeixinParam(getComId(), "corpid")+","+nonceStr;
	}
	/**
	 *  身份验证接口
	 * @param request
	 * @return
	 */
	@RequestMapping("getOatuh")
	@ResponseBody
	public String getOatuh(HttpServletRequest request) {
		String code=request.getParameter("code");
		WeixinUtil wei=new WeixinUtil();
		String result=wei.getOatuh(code,getComId());
		JSONObject json=JSONObject.fromObject(result);
		try {
			String url="https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?access_token="+wei.getAccessToken(getComId());
			JSONObject jsonu=new JSONObject();
			jsonu.put("openid", json.getString("OpenId"));
			result=wei.postData(url, jsonu);
		} catch (Exception e) {
		}
		log.info(result);
		return result;
	}
	
	/**
	 * 根据微信返回资源id获取图片
	 * @param request
	 * @return
	 */
	@RequestMapping("getWeixinFwqImg")
	@ResponseBody
	public ResultInfo getWeixinFwqImg(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			WeixinUtil wei=new WeixinUtil();
			String media_id=request.getParameter("url");
			Map<String,Object> map=getKeyAndValue(request);
			String url="https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="+wei.getAccessToken(getComId())
					+"&media_id="+media_id;
			msg=getComId()+"/certificate/"+map.get("orderNo")+".jpg";
	        File destFile=new File(getRealPath(request)+msg);
	        mkdirsDirectory(destFile);
	        if (destFile.exists()&&destFile.isFile()) {
				destFile.delete();
			}
			wei.getDataImage(url,destFile);
			msg="../"+msg;
			if (isNotMapKeyNull(map, "headship")) {
				///向财务发送调整消息
			}
			success = true;
		} catch (Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	
	/**
	 * 获取图片从微信服务器
	 * @param request
	 * @param url 微信端图片位置
	 * @param imgPath  图片存放位置
	 * @return 图片存放的位置
	 */
	@RequestMapping("getImageToWeixin")
	@ResponseBody
	public ResultInfo getImageToWeixin(HttpServletRequest request) {
		boolean success = false;
		String msg = null;
		try {
			WeixinUtil wei=new WeixinUtil();
			String media_id=request.getParameter("url");
			String imgPath=request.getParameter("imgPath");
			if (StringUtils.isNotBlank(imgPath)) {
				if(imgPath.contains("@com_id")){
					imgPath=imgPath.replace("@com_id", getComId());
				}
				String url="https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="+wei.getAccessToken(getComId())+"&media_id="+media_id;
				File destFile=new File(getRealPath(request)+imgPath);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				wei.getDataImage(url,destFile);
				msg=imgPath;
				success = true;
			}
		} catch (Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return new ResultInfo(success, msg);
	}
	/**
	 *  获取微信服务号中的用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserInfoList")
	@ResponseBody
	public JSONObject getUserInfoList(HttpServletRequest request) {
		return new WeiXinServiceUtil().getUserInfoList(getComId());
	}
}