/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import java.util.List;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudEnterpriseauth;

/**
 * IcloudEnterpriseauthDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudEnterpriseauthDao extends BaseDao<IcloudEnterpriseauth, Long> {
	
	// 查询未发送的数据
    List<IcloudEnterpriseauth> findBySended();
    
    // 修改发送状态
    int updateSended(Long id);

    // 通过userId查询
    List<IcloudEnterpriseauth> findByUserId(Long userId);

    void deleteByUserId(IcloudEnterpriseauth icloudEnterpriseauth);

}
