/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudApplicationagencyDao;
import com.zhiyun.entity.IcloudApplicationagency;

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
}
