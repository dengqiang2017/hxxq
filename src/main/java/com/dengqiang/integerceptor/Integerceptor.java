package com.dengqiang.integerceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		log.info("preHandle===>>>" + request.getRequestURI());
		getVer(request);
		Map<String, Object> userMap=(Map<String, Object>) request.getSession().getAttribute(SESSION_USER_INFO);
		boolean login=true;
		if (userMap==null) {
			login=false;
		}
    	return login;
	}
}