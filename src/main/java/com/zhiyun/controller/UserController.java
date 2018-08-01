/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.UserStateDto;
import com.zhiyun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UserController
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 下午2:02:55
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @Title: getUser   
	 * @Description:
	 * @param: @return      获取个人基础信息
	 * 	 * @author: 孙云涛
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getUser() {

		BaseResult<UserStateDto> baseResult = new BaseResult<>();
		try {
			String username = UserHolder.getUser().getAccountName();
			UserStateDto userDto = userService.findUserStateDtoByScreenName(username);
			baseResult.setResult(true);
			baseResult.setModel(userDto);
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return JSON.toJSONString(baseResult);
	}

}
