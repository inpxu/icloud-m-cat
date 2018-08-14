/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Page;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.entity.IcloudOnicloud;

import java.util.List;

/**
 * IcloudOnicloudDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudOnicloudDao extends BaseDao<IcloudOnicloud, Long> {

    DataGrid<IcloudOnicloudDto> myPage(Params params, Page page);

    List<IcloudOnicloudDto> findByUserIdAndStatus(Params params);

}
