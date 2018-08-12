/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
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

}
