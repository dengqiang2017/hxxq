package com.dengqiang.integerceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.controller.BaseController;
/**
 * 请求拦截器
 * @author dengqiang
 *
 */
public class Integerceptor extends BaseController implements HandlerInterceptor {
	private Logger log = Logger.getLogger(Integerceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exp)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		log.info("preHandle===>>>" + request.getRequestURI());//request.getHeaderNames()
//		getVer(request);
		UserInfoBean userInfo=(UserInfoBean) request.getSession().getAttribute(SESSION_USER_INFO);
		boolean login=true;
		if (userInfo==null) {
			login=false;
			if (request.getRequestURI().contains("/login/")) {
				login=true;
			}
		}
//		if (isLocal()) {
			userInfo=new UserInfoBean(0);
			userInfo.setUserName("邓强");
			userInfo.setUserType(1);
			request.getSession().setAttribute(SESSION_USER_INFO,userInfo);
			request.getSession().setAttribute(HOUSING_ESTATE_INFO,new HousingEstateBean(0));
			login=true;
//		}
    	return login;
	}
}
