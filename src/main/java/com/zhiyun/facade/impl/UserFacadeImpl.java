/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.UserFacade;
import com.zhiyun.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("userFacade")
public class UserFacadeImpl implements UserFacade {

	@Resource
	private UserService userService;
	
	
}
