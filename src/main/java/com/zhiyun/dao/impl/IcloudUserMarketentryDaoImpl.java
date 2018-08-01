/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudUserMarketentryDao;
import com.zhiyun.dto.IcloudUserMarketentryDto;
import com.zhiyun.entity.IcloudUserMarketentry;

/**
 * IcloudUserMarketentryDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudUserMarketentryDao")
public class IcloudUserMarketentryDaoImpl extends BaseDaoImpl<IcloudUserMarketentry, Long> implements IcloudUserMarketentryDao {

	@Override
	public void updateMarketentryId(IcloudUserMarketentryDto icloudUserMarketentry) {
		this.update(getMethodName(), icloudUserMarketentry);
	}

	@Override
	public List<Long> findId(Long marketentryId) {
		return this.selectList(getMethodName(), marketentryId);
	}

	@Override
	public int findUser(Long userId) {
		return this.selectOne(getMethodName(), userId);
	}

	@Override
	public IcloudUserMarketentry getByMarketentryId(Long marketentryId) {
		return this.selectOne(getMethodName(), marketentryId);
	}

}
