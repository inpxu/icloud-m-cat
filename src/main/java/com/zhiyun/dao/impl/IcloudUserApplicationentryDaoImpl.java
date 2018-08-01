/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.model.Params;
import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudUserApplicationentryDao;
import com.zhiyun.entity.IcloudUserApplicationentry;

import java.util.List;

/**
 * IcloudUserApplicationentryDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudUserApplicationentryDao")
public class IcloudUserApplicationentryDaoImpl extends BaseDaoImpl<IcloudUserApplicationentry, Long> implements IcloudUserApplicationentryDao {

    public void updateByApplicationEntryId(Long fromApplicationEntryId,Long toApplicationEntryId){
        Params params = Params.create();
        params.add("fromApplicationEntryId",fromApplicationEntryId);
        params.add("toApplicationEntryId",toApplicationEntryId);
        this.update(getMethodName(), params);
    }

    @Override
    public List<IcloudUserApplicationentry> listByApplicationentryId(Long organizationId) {
        return this.selectList(getMethodName(),organizationId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        this.delete(getMethodName(),userId);
    }

	@Override
	public int findUser(Long userId) {
		return selectOne(getMethodName(), userId);
	}

}
