/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudApplicationagencyDao;
import com.zhiyun.dto.AgencyUserCheckDto;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.entity.IcloudApplicationentry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public List<Object> getAgencyAreaByParams(Map params) {
        return this.selectList(getMethodName(), params);
    }

    @Override
    public List<AgencyUserCheckDto> getUserCheckByparams(Map params) {
        return this.selectList(getMethodName(), params);
    }

    @Override
    public int getUserCheckByparamsCount(Map params) {
        return this.selectList(getMethodName(), params).size();
    }

    @Override
    public int agencyApprove(IcloudApplicationentry icloudApplicationentry) {
        return this.update(getMethodName(), icloudApplicationentry);
    }

    @Override
    public List<IcloudApplicationagency> findBySended() {
        Params params = Params.create();
        return this.selectList(getMethodName(), params);
    }

    @Override
    public void updateSended(Long id) {
        Params params = Params.create();
        params.add("id", id);
        this.update(getMethodName(), params);
    }
}
