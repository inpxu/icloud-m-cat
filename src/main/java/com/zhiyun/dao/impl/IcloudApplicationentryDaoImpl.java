/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudApplicationentryDao;
import com.zhiyun.entity.IcloudApplicationentry;

/**
 * IcloudApplicationentryDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudApplicationentryDao")
public class IcloudApplicationentryDaoImpl extends BaseDaoImpl<IcloudApplicationentry, Long>
		implements IcloudApplicationentryDao {

	@Override
	public int updateByUserId(IcloudApplicationentry icloudApplicationentry) {
		return this.update(getMethodName(), icloudApplicationentry);
	}

	@Override
	public int updateStatus(IcloudApplicationentry icloudApplicationentry) {
		return this.update(getMethodName(), icloudApplicationentry);
	}

	@Override
	public List<IcloudApplicationentry> findBySended() {
		Params params = Params.create();
		return this.selectList(getMethodName(), params);
	}

	@Override
	public int updateSended(Long id) {
		Params params = Params.create();
		params.add("id", id);
		return this.update(getMethodName(), params);
	}

	@Override
	public IcloudApplicationentry findByUserId(Long userId) {
		Params params = Params.create();
		params.add("userId", userId);
		return this.selectOne(getMethodName(), userId);
	}

	@Override
	public IcloudApplicationentry findByOrganizationId(Long organizationId) {
		return this.selectOne(getMethodName(),organizationId);
	}
}
