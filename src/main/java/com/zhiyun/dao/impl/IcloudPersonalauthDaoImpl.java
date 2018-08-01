/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudPersonalauthDao;
import com.zhiyun.entity.IcloudPersonalauth;

/**
 * IcloudPersonalauthDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudPersonalauthDao")
public class IcloudPersonalauthDaoImpl extends BaseDaoImpl<IcloudPersonalauth, Long> implements IcloudPersonalauthDao {

	@Override
	public List<IcloudPersonalauth> findBySended() {
		Params params = Params.create();
		return this.selectList(getMethodName(), params);
	}

	@Override
	public int updateSended(Long id) {
		Params params = Params.create().add("id", id);
		return this.update(getMethodName(), params);
	}

	@Override
	public IcloudPersonalauth findByUserId(Long userId) {
		Params params = Params.create().add("userId", userId);
		return this.selectOne(getMethodName(), params);
	}

	@Override
	public void deleteByUserId(IcloudPersonalauth icloudPersonalauth) {
		this.update(getMethodName(),icloudPersonalauth);
	}
}
