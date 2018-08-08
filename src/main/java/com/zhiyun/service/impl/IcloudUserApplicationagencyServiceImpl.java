/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudUserApplicationagencyDao;
import com.zhiyun.entity.IcloudUserApplicationagency;
import com.zhiyun.service.IcloudUserApplicationagencyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudUserApplicationagencyService")
public class IcloudUserApplicationagencyServiceImpl extends BaseServiceImpl<IcloudUserApplicationagency, Long> implements IcloudUserApplicationagencyService {

	@Resource
	private IcloudUserApplicationagencyDao icloudUserApplicationagencyDao;

	@Override
	protected BaseDao<IcloudUserApplicationagency, Long> getBaseDao() {
		return this.icloudUserApplicationagencyDao;
	}
}
