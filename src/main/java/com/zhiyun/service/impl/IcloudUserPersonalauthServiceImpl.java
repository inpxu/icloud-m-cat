/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudUserPersonalauthDao;
import com.zhiyun.entity.IcloudUserPersonalauth;
import com.zhiyun.service.IcloudUserPersonalauthService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudUserPersonalauthService")
public class IcloudUserPersonalauthServiceImpl extends BaseServiceImpl<IcloudUserPersonalauth, Long> implements IcloudUserPersonalauthService {

	@Resource
	private IcloudUserPersonalauthDao icloudUserPersonalauthDao;

	@Override
	protected BaseDao<IcloudUserPersonalauth, Long> getBaseDao() {
		return this.icloudUserPersonalauthDao;
	}
}
