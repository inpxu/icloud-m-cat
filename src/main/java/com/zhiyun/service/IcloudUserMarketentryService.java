/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.IcloudUserMarketentry;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudUserMarketentryService extends BaseService<IcloudUserMarketentry, Long> {
	
	// 更新marketentryId
	void updateMarketentryId(Long marketentryId, Long newId);
	
	// 根据userid查询数据是否存在
    boolean findUser(Long userId);

}
