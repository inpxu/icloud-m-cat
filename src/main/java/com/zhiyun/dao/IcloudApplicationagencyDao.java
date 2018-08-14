/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudApplicationagency;

import java.util.List;

/**
 * IcloudApplicationagencyDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationagencyDao extends BaseDao<IcloudApplicationagency, Long> {

    List<IcloudApplicationagency> listByExceptIdAndAgencyType(Long exceptId, String agencyType);
    // 查询未发送的数据
    List<IcloudApplicationagency> findBySended();
    
    // 修改发送状态
    int updateSended(Long id);

}
