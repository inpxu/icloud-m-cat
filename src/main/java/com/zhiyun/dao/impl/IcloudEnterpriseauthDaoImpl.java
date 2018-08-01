/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudEnterpriseauthDao;
import com.zhiyun.entity.IcloudEnterpriseauth;

/**
 * IcloudEnterpriseauthDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudEnterpriseauthDao")
public class IcloudEnterpriseauthDaoImpl extends BaseDaoImpl<IcloudEnterpriseauth, Long> implements IcloudEnterpriseauthDao {

	@Override
	public List<IcloudEnterpriseauth> findBySended() {
		Params params = Params.create();
		return this.selectList(getMethodName(), params);
	}

	@Override
	public int updateSended(Long id) {
		Params params = Params.create();
		params.add("id", id);
		return this.update(getMethodName(), id);
	}

	@Override
	public IcloudEnterpriseauth findByUserId(Long userId) {
		Params params = Params.create();
		params.add("userId", userId);
		return this.selectOne(getMethodName(), userId);
	}

	@Override
	public void deleteByUserId(IcloudEnterpriseauth icloudEnterpriseauth) {
		this.update(getMethodName(),icloudEnterpriseauth);
	}
}
