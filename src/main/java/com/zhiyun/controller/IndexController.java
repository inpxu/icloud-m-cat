/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhiyun.client.UserHolder;
import com.zhiyun.liferay.impl.UserInvoker;
import com.zhiyun.liferay.model.LiferayInvokerResult;

/**
 * 同步请求主页
 *
 * @author 庄超
 * @version v1.0
 * @date 2018 -05-05 13:01
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserInvoker userInvoker;
	

	/**
	 * 查询样式接口
	 *
	 * @param model
	 *            传输对象
	 * @return java.lang.String 响应页面地址
	 * @author 庄超
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {

		LiferayInvokerResult result = userInvoker.getUserByScreenName("ptest");

		logger.debug("request in");
        model.addAttribute("userId", UserHolder.getId());
		model.addAttribute("userName", UserHolder.getUserName());
		model.addAttribute("companyName", UserHolder.getCompanyName());
		return "/demo/index";
	}

}
