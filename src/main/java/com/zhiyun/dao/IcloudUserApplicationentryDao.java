/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudUserApplicationentry;

import java.util.List;

/**
 * IcloudUserApplicationentryDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudUserApplicationentryDao extends BaseDao<IcloudUserApplicationentry, Long> {

    void updateByApplicationEntryId(Long fromApplicationEntryId,Long toApplicationEntryId);
    
    // 根据userid查询数据是否存在
    int findUser(Long userId);

    List<IcloudUserApplicationentry> listByApplicationentryId(Long organizationId);

    void deleteByUserId(Long userId);

}
