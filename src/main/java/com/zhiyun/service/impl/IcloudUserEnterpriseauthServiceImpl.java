/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudUserEnterpriseauthDao;
import com.zhiyun.entity.IcloudUserEnterpriseauth;
import com.zhiyun.service.IcloudUserEnterpriseauthService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudUserEnterpriseauthService")
public class IcloudUserEnterpriseauthServiceImpl extends BaseServiceImpl<IcloudUserEnterpriseauth, Long> implements IcloudUserEnterpriseauthService {

	@Resource
	private IcloudUserEnterpriseauthDao icloudUserEnterpriseauthDao;

	@Override
	protected BaseDao<IcloudUserEnterpriseauth, Long> getBaseDao() {
		return this.icloudUserEnterpriseauthDao;
	}
}
