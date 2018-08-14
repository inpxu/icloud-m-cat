/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.model.Params;
import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudApplicationagencyqualityimageshareurlDao;
import com.zhiyun.entity.IcloudApplicationagencyqualityimageshareurl;

import java.util.List;

/**
 * IcloudApplicationagencyqualityimageshareurlDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudApplicationagencyqualityimageshareurlDao")
public class IcloudApplicationagencyqualityimageshareurlDaoImpl extends BaseDaoImpl<IcloudApplicationagencyqualityimageshareurl, Long> implements IcloudApplicationagencyqualityimageshareurlDao {

    @Override
    public List<IcloudApplicationagencyqualityimageshareurl> findByApplicationAgencyIds(List<Long> applicationAgencyIds) {
        return this.selectList(getMethodName(),Params.create().add("applicationAgencyIds",applicationAgencyIds));
    }

	@Override
	public List<String> findUrl(Long id) {
		return this.selectList(getMethodName(), id);
	}
}
