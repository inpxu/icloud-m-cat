/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Page;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.IcloudOnicloudDto;
import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudOnicloudDao;
import com.zhiyun.entity.IcloudOnicloud;

/**
 * IcloudOnicloudDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudOnicloudDao")
public class IcloudOnicloudDaoImpl extends BaseDaoImpl<IcloudOnicloud, Long> implements IcloudOnicloudDao {

    @Override
    public DataGrid<IcloudOnicloudDto> myPage(Params params, Page page) {
        return this.selectPage(getMethodName(), params, page);
    }

}
