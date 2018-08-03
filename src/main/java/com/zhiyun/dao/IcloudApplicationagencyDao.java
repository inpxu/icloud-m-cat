/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.AgencyUserCheckDto;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.entity.IcloudApplicationentry;

import java.util.List;
import java.util.Map;

/**
 * IcloudApplicationagencyDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationagencyDao extends BaseDao<IcloudApplicationagency, Long> {

    List<Object> getAgencyAreaByParams(Map params);

    List<AgencyUserCheckDto> getUserCheckByparams(Map params);

    int getUserCheckByparamsCount(Map params);

    int agencyApprove(IcloudApplicationentry icloudApplicationentry);

    List<IcloudApplicationagency> findBySended();

    void updateSended(Long id);
}
