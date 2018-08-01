/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.facade.IcloudCmsconfigFacade;
import com.zhiyun.service.IcloudCmsconfigService;

/**
 * IcloudCmsconfigFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudCmsconfigFacade")
public class IcloudCmsconfigFacadeImpl implements IcloudCmsconfigFacade {

	@Resource
	private IcloudCmsconfigService icloudCmsconfigService;
	
	
}
