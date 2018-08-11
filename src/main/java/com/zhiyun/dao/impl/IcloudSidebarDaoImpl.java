/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudSidebarDao;
import com.zhiyun.entity.IcloudSidebar;

/**
 * IcloudSidebarDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("icloudSidebarDao")
public class IcloudSidebarDaoImpl extends BaseDaoImpl<IcloudSidebar, Long> implements IcloudSidebarDao {

	@Override
	public List<IcloudSidebar> listSidebarByRoleId(long roleId) {
		Params params = Params.create().add("roleId", roleId);
		return this.selectList(getMethodName(), params);

	}

	@Override
	public List<IcloudSidebar> listByRoleIds(List<Long> roleIdList) {
		Params params = Params.create().add("roleIdList", roleIdList);
		return this.selectList(getMethodName(), params);
	}

}
