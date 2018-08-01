/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudUserApplicationentryDao;
import com.zhiyun.entity.IcloudUserApplicationentry;
import com.zhiyun.service.IcloudUserApplicationentryService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudUserApplicationentryService")
public class IcloudUserApplicationentryServiceImpl extends BaseServiceImpl<IcloudUserApplicationentry, Long> implements IcloudUserApplicationentryService {

	@Resource
	private IcloudUserApplicationentryDao icloudUserApplicationentryDao;

	@Override
	protected BaseDao<IcloudUserApplicationentry, Long> getBaseDao() {
		return this.icloudUserApplicationentryDao;
	}

	@Override
	public boolean findUser(Long userId) {
		int a = icloudUserApplicationentryDao.findUser(userId);
		if (a == 0) {
			return true;
		} else {
		return false;
		}
	}
}
