/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import java.util.List;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudPersonalauth;

/**
 * IcloudPersonalauthDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudPersonalauthDao extends BaseDao<IcloudPersonalauth, Long> {
	
	// 查询未发送的数据
    List<IcloudPersonalauth> findBySended();
    
    // 修改发送状态
    int updateSended(Long id);

    // 通过userId查询
    List<IcloudPersonalauth> findByUserId(Long userId);

    void deleteByUserId(IcloudPersonalauth icloudPersonalauth);

}
