/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudCmsconfigDao;
import com.zhiyun.entity.IcloudCmsconfig;
import com.zhiyun.service.IcloudCmsconfigService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudCmsconfigService")
public class IcloudCmsconfigServiceImpl extends BaseServiceImpl<IcloudCmsconfig, Long> implements IcloudCmsconfigService {

	@Resource
	private IcloudCmsconfigDao icloudCmsconfigDao;

	@Override
	protected BaseDao<IcloudCmsconfig, Long> getBaseDao() {
		return this.icloudCmsconfigDao;
	}
}
