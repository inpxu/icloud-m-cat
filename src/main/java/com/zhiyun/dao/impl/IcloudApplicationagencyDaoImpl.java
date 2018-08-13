/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.model.Params;
import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudApplicationagencyDao;
import com.zhiyun.entity.IcloudApplicationagency;

import java.util.List;

/**
 * IcloudApplicationagencyDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudApplicationagencyDao")
public class IcloudApplicationagencyDaoImpl extends BaseDaoImpl<IcloudApplicationagency, Long> implements IcloudApplicationagencyDao {

    @Override
    public List<IcloudApplicationagency> listByExceptIdAndAgencyType(Long exceptId, String agencyType) {
        Params params = Params.create();
        params.add("exceptId",exceptId);
        params.add("agencyType",agencyType);
        return this.selectList(getMethodName(),params);
    }
}
