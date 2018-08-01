/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import java.util.List;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.IcloudApplicationentry;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationentryService extends BaseService<IcloudApplicationentry, Long> {

	IcloudApplicationentry findIcloudApplicationentryByUserId(Long userId);

	void save(IcloudApplicationentry icloudApplicationentry);

    IcloudApplicationentry updateApplicationEntry(IcloudApplicationentry icloudApplicationentry);
	
	public void updateUserInDataCenter(String companyName, long companyId, long userId, String screenName, String name,
			String email) ;

    
    // 查询未发送的数据
    List<IcloudApplicationentry> findBySended();
    
    // 修改发送状态
    int updateSended(Long id);

    // 通过userId查询
    IcloudApplicationentry findByUserId(Long userId);


}
