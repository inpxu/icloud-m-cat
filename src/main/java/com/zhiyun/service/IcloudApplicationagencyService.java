/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.entity.IcloudApplicationentry;

import java.util.List;
import java.util.Map;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationagencyService extends BaseService<IcloudApplicationagency, Long> {

    void save(IcloudApplicationagency icloudApplicationagency);

    IcloudApplicationagency updateApplicationAgency(IcloudApplicationagency icloudApplicationagency);

    List<Object> getAgencyAreaByParams(Map params);

}
