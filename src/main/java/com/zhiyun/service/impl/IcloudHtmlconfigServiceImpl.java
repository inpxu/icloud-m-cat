/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudHtmlconfigDao;
import com.zhiyun.entity.IcloudHtmlconfig;
import com.zhiyun.service.IcloudHtmlconfigService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudHtmlconfigService")
public class IcloudHtmlconfigServiceImpl extends BaseServiceImpl<IcloudHtmlconfig, Long> implements IcloudHtmlconfigService {

	@Resource
	private IcloudHtmlconfigDao icloudHtmlconfigDao;

	@Override
	protected BaseDao<IcloudHtmlconfig, Long> getBaseDao() {
		return this.icloudHtmlconfigDao;
	}
}
