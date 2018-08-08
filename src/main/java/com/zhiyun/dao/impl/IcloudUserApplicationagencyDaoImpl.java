/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudUserApplicationagencyDao;
import com.zhiyun.entity.IcloudUserApplicationagency;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * IcloudUserApplicationagencyDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudUserApplicationagencyDao")
public class IcloudUserApplicationagencyDaoImpl extends BaseDaoImpl<IcloudUserApplicationagency, Long> implements IcloudUserApplicationagencyDao {

    @Override
    public void updateByApplicationAgencyId(Long fromApplicationAgenctId, Long toApplicationAgencyId) {
        Params params = Params.create();
        params.add("fromApplicationAgencyId",fromApplicationAgenctId);
        params.add("toApplicationAgencyId",toApplicationAgencyId);
        this.update(getMethodName(), params);
    }

    @Override
    public List<IcloudUserApplicationagency> listByApplicationagencyId(Long applicationagencyId) {
        return this.selectList(getMethodName(),applicationagencyId);
    }
}
