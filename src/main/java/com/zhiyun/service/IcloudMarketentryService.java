/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import java.util.List;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.IcloudMarketentry;
import com.zhiyun.entity.IcloudMarketentrydatashareurl;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudMarketentryService extends BaseService<IcloudMarketentry, Long> {

	void save(IcloudMarketentry icloudMarketentry);

	void update(IcloudMarketentry icloudMarketentry);
	
	// 查询未发送的数据
    List<IcloudMarketentry> findBySended();
    
    // 修改发送状态
    int updateSended(Long id);
    
    // 通过id查询关联的证件图片
    List<IcloudMarketentrydatashareurl> findUrl(Long id);

    // 通过userId查询
    IcloudMarketentry findByUserId(Long userId);

    void updateIcloudMarketentry(IcloudMarketentry icloudMarketentry);

}
