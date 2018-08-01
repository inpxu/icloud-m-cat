/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import java.util.List;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.IcloudUserMarketentryDto;
import com.zhiyun.entity.IcloudUserMarketentry;

/**
 * IcloudUserMarketentryDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudUserMarketentryDao extends BaseDao<IcloudUserMarketentry, Long> {
	
	// 更新marketentryId
	void updateMarketentryId(IcloudUserMarketentryDto icloudUserMarketentry);
	
	// 根据marketentryId获取id
	List<Long> findId(Long marketentryId);
	
	// 根据userid查询数据是否存在
    int findUser(Long userId);

    IcloudUserMarketentry getByMarketentryId(Long marketentryId);

}
