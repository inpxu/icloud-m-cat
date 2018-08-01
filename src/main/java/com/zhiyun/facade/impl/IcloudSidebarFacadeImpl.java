/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.facade.IcloudSidebarFacade;
import com.zhiyun.service.IcloudSidebarService;

/**
 * 
 * IcloudSidebarFacadeImpl
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 下午2:47:44
 */
@Service("icloudSidebarFacade")
public class IcloudSidebarFacadeImpl implements IcloudSidebarFacade {

	@Resource
	private IcloudSidebarService icloudSidebarService;
	
	
}
