/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudUserOnicloudDao;
import com.zhiyun.entity.IcloudUserOnicloud;
import com.zhiyun.service.IcloudUserOnicloudService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudUserOnicloudService")
public class IcloudUserOnicloudServiceImpl extends BaseServiceImpl<IcloudUserOnicloud, Long> implements IcloudUserOnicloudService {

	@Resource
	private IcloudUserOnicloudDao icloudUserOnicloudDao;

	@Override
	protected BaseDao<IcloudUserOnicloud, Long> getBaseDao() {
		return this.icloudUserOnicloudDao;
	}
}
