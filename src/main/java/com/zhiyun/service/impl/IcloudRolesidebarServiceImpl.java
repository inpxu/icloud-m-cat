package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudRolesidebarDao;
import com.zhiyun.entity.IcloudRolesidebar;
import com.zhiyun.service.IcloudRolesidebarService;

@Service("icloudRolesidebarService")
public class IcloudRolesidebarServiceImpl extends BaseServiceImpl<IcloudRolesidebar, Long> implements IcloudRolesidebarService {

	@Resource
	private IcloudRolesidebarDao icloudRolesidebarDao;

	@Override
	protected BaseDao<IcloudRolesidebar, Long> getBaseDao() {
		return this.icloudRolesidebarDao;
	}
}
