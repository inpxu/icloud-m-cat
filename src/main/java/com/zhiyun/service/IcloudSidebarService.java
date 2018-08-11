package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.context.OnlineUser;
import com.zhiyun.dto.IcloudSidebarDto;
import com.zhiyun.entity.IcloudSidebar;

/**
 * 服务接口。
 */
public interface IcloudSidebarService extends BaseService<IcloudSidebar, Long> {

    IcloudSidebarDto listSidebarByScreenName(String screenName);

	IcloudSidebarDto listByUser(OnlineUser user);

}
