/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import java.util.List;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudApplicationentry;

/**
 * IcloudApplicationentryDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationentryDao extends BaseDao<IcloudApplicationentry, Long> {

    int updateByUserId(IcloudApplicationentry icloudApplicationentry);
    
    // 通过id修改status
    int updateStatus(IcloudApplicationentry icloudApplicationentry);
    
    // 查询未发送的数据
    List<IcloudApplicationentry> findBySended();
    
    // 修改发送状态
    int updateSended(Long id);

    // 通过userId查询
    IcloudApplicationentry findByUserId(Long userId);

    IcloudApplicationentry findByOrganizationId(Long organizationId);

}
