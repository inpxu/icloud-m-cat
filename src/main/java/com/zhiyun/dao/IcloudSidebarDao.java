package com.zhiyun.dao;

import java.util.List;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudSidebar;

public interface IcloudSidebarDao extends BaseDao<IcloudSidebar, Long> {

	List<IcloudSidebar> listSidebarByRoleId(long roleId);

}
