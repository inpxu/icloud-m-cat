/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudScheduleDao;
import com.zhiyun.entity.IcloudSchedule;
import com.zhiyun.service.IcloudScheduleService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudScheduleService")
public class IcloudScheduleServiceImpl extends BaseServiceImpl<IcloudSchedule, Long> implements IcloudScheduleService {

	@Resource
	private IcloudScheduleDao icloudScheduleDao;

	@Override
	protected BaseDao<IcloudSchedule, Long> getBaseDao() {
		return this.icloudScheduleDao;
	}
}
