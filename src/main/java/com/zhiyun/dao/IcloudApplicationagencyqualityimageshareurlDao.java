/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudApplicationagencyqualityimageshareurl;

import java.util.List;

/**
 * IcloudApplicationagencyqualityimageshareurlDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationagencyqualityimageshareurlDao extends BaseDao<IcloudApplicationagencyqualityimageshareurl, Long> {

    List<IcloudApplicationagencyqualityimageshareurl> findByApplicationAgencyIds(List<Long> applicationAgencyIds );

}
