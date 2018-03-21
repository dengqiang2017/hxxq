package com.dengqiang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dengqiang.bean.HousingEstateBean;
import com.dengqiang.bean.UserInfoBean;
import com.dengqiang.service.ICustomerService;

@Controller
@RequestMapping("user")
public class UserInfoController extends BaseController{

	@Autowired
	ICustomerService customerService;
	
	/**
	 * 用户小区相关信息
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserHousing")
	@ResponseBody
	public List<HousingEstateBean> getUserHousing(HttpServletRequest request) {
		UserInfoBean userInfo=getUserInfo(request);
		
		return customerService.getUserHousing(userInfo);
	}
	
	
}
