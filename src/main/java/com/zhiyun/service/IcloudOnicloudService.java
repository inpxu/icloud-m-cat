/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import java.util.List;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.entity.IcloudOnicloud;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudOnicloudService extends BaseService<IcloudOnicloud, Long> {

    void addOnIncloud(IcloudOnicloud icloudOnicloud);

    IcloudOnicloud getOnIcloudByUserId(Long userId);

    void updateOnIncloud(IcloudOnicloud icloudOnicloud);

    DataGrid<IcloudOnicloudDto> myPage(Params params , Pager pager);
    
    // 查询未发送的数据
 	List<IcloudOnicloud> findBySended();
    
 	// 修改发送状态
 	int updateSended(Long id);


}
