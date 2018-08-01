package com.zhiyun.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.IcloudRoleDao;
import com.zhiyun.entity.IcloudRole;

@Repository("icloudRoleDao")
public class IcloudRoleDaoImpl extends BaseDaoImpl<IcloudRole, Long> implements IcloudRoleDao {

}
