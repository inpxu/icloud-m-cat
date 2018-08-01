package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.RoleIdConstant;
import com.zhiyun.dao.IcloudRoleDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.entity.IcloudRole;
import com.zhiyun.entity.User;
import com.zhiyun.service.IcloudRoleService;

@Service("icloudRoleService")
public class IcloudRoleServiceImpl extends BaseServiceImpl<IcloudRole, Long> implements IcloudRoleService {

	@Resource
	private IcloudRoleDao icloudRoleDao;

	@Resource
	private UserDao userDao;

	@Override
	protected BaseDao<IcloudRole, Long> getBaseDao() {
		return this.icloudRoleDao;
	}

}
