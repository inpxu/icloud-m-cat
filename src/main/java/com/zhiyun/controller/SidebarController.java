/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.context.OnlineUser;
import com.zhiyun.dto.IcloudSidebarDto;
import com.zhiyun.service.IcloudSidebarService;

/**
 * 
 * SidebarController
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 下午1:08:09
 */
@Controller
@RequestMapping(value = "/sidebar", produces = "application/json;charset=UTF-8")
public class SidebarController extends BaseController {

	@Autowired
	private IcloudSidebarService icloudSidebarService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SidebarController.class);

	/**
	 * 
	 * @Title: getSidebar   
	 * @Description: 获取侧边导航栏 
	 * @author: 孙云涛  
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getOld", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getSidebar() {

		BaseResult<IcloudSidebarDto> baseResult = new BaseResult<>();
		try {
			String screenName = UserHolder.getUser().getAccountName();
			
			
			IcloudSidebarDto icloudSidebarDto = icloudSidebarService.listSidebarByScreenName(screenName);

			baseResult.setResult(true);
			baseResult.setModel(icloudSidebarDto);
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;

	}
	
	
	/**
	 * 
	 * @Title: getSidebar   
	 * @Description: 获取侧边导航栏 
	 * @author: 孙云涛  
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getSidebarList() {

		BaseResult<IcloudSidebarDto> baseResult = new BaseResult<>();
		try {
			OnlineUser user = UserHolder.getUser();
			
			IcloudSidebarDto icloudSidebarDto = icloudSidebarService.listByUser(user);

			baseResult.setResult(true);
			baseResult.setModel(icloudSidebarDto);
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;

	}

}
