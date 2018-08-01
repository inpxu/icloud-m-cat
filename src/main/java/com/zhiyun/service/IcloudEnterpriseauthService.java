/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import java.util.List;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.IcloudEnterpriseauth;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudEnterpriseauthService extends BaseService<IcloudEnterpriseauth, Long> {
	
	// 查询未发送的数据
	List<IcloudEnterpriseauth> findBySended();
   
	// 修改发送状态
	int updateSended(Long id);

    // 通过userId查询
    IcloudEnterpriseauth findByUserId(Long userId);

    void updateEnterpriseauth(IcloudEnterpriseauth icloudEnterpriseauth);

    void deleteByUserId(Long userId);

    void saveIcloudEnterpriseauth(IcloudEnterpriseauth icloudEnterpriseauth);


}
