/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudUserMarketentryDao;
import com.zhiyun.dto.IcloudUserMarketentryDto;
import com.zhiyun.entity.IcloudUserMarketentry;
import com.zhiyun.service.IcloudUserMarketentryService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudUserMarketentryService")
public class IcloudUserMarketentryServiceImpl extends BaseServiceImpl<IcloudUserMarketentry, Long> implements IcloudUserMarketentryService {

	@Resource
	private IcloudUserMarketentryDao icloudUserMarketentryDao;

	@Override
	protected BaseDao<IcloudUserMarketentry, Long> getBaseDao() {
		return this.icloudUserMarketentryDao;
	}

	@Override
	public void updateMarketentryId(Long marketentryId, Long newId) {
		List<Long> ids = icloudUserMarketentryDao.findId(marketentryId);
		for (Long id : ids) {
			IcloudUserMarketentryDto dto = new IcloudUserMarketentryDto();
			dto.setId(id);
			dto.setNewId(newId);
			icloudUserMarketentryDao.updateMarketentryId(dto);
		}
	}

	@Override
	public boolean findUser(Long userId) {
		int a = icloudUserMarketentryDao.findUser(userId);
		if (a == 0) {
			return true;
		} else {
			return false;
		}
	}
}
