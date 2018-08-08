
package com.zhiyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.RoleIdConstant;
import com.zhiyun.dao.IcloudSidebarDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.dto.IcloudSidebarDto;
import com.zhiyun.dto.IcloudSidebarDto.SideBar;
import com.zhiyun.entity.IcloudSidebar;
import com.zhiyun.entity.User;

import com.zhiyun.service.IcloudRoleService;
import com.zhiyun.service.IcloudSidebarService;

@Service("icloudSidebarService")
public class IcloudSidebarServiceImpl extends BaseServiceImpl<IcloudSidebar, Long> implements IcloudSidebarService {

	@Resource
	private IcloudSidebarDao icloudSidebarDao;

	@Resource
	private IcloudRoleService icloudRoleService;

	@Resource
	private UserDao userDao;

	@Override
	protected BaseDao<IcloudSidebar, Long> getBaseDao() {
		return this.icloudSidebarDao;
	}

	@Override
	public IcloudSidebarDto listSidebarByScreenName(String screenName) {

		User user = userDao.findUserStatus(screenName);

		List<IcloudSidebar> icloudSidebars = null;

		List<IcloudSidebar> agencySidebars = null;

		if (user.getIcloudEnterpriseauth() != null
		
				&& user.getIcloudEnterpriseauth().getStatus() == AuditState.AUDITED) {
			icloudSidebars = icloudSidebarDao.listSidebarByRoleId(RoleIdConstant.ENTERPRISE_AUTHORIZED_ROLE_ID);
			if (user.getIcloudApplicationagency() != null && user.getIcloudApplicationagency().getStatus() == AuditState.AUDITED){
				agencySidebars = icloudSidebarDao.listSidebarByRoleId(RoleIdConstant.AGENCY__AUTHORIZED_ROLE_ID);
				icloudSidebars.addAll(agencySidebars);
			}
		} else if (user.getIcloudPersonalauth() != null
				&& user.getIcloudPersonalauth().getStatus() == AuditState.AUDITED) {
			icloudSidebars = icloudSidebarDao.listSidebarByRoleId(RoleIdConstant.PERSONAL_AUTHORIZED_ROLE_ID);
			if (user.getIcloudApplicationagency() != null && user.getIcloudApplicationagency().getStatus() == AuditState.AUDITED){
				agencySidebars = icloudSidebarDao.listSidebarByRoleId(RoleIdConstant.AGENCY__AUTHORIZED_ROLE_ID);
				icloudSidebars.addAll(agencySidebars);
			}
		} else {
			icloudSidebars = icloudSidebarDao.listSidebarByRoleId(RoleIdConstant.UN_AUTHORIZED_ROLE_ID);
		}

		IcloudSidebarDto icloudSidebarDto = new IcloudSidebarDto();

		if (CollectionUtils.isNotEmpty(icloudSidebars)) {
			for (IcloudSidebar icloudSidebar : icloudSidebars) {
				if (icloudSidebar.getParentid() == null || icloudSidebar.getParentid() == 0) {
					IcloudSidebarDto.SideBar sideBar = new IcloudSidebarDto.SideBar();
					sideBar.setTitle(icloudSidebar.getName());
					sideBar.setId(icloudSidebar.getId());
					icloudSidebarDto.getNavList().add(sideBar);
				}

			}

			for (IcloudSidebar icloudSidebar : icloudSidebars) {
				if (icloudSidebar.getParentid() != null && icloudSidebar.getParentid() != 0) {
					for (SideBar sideBar : icloudSidebarDto.getNavList()) {
						if (sideBar.getId() == icloudSidebar.getParentid()) {
							IcloudSidebarDto.Item item = new IcloudSidebarDto.Item();
							item.setId(icloudSidebar.getId());
							item.setLink(icloudSidebar.getUrl());
							item.setName(icloudSidebar.getName());
							sideBar.getChildrens().add(item);
						}
					}
				}
			}

		}

		return icloudSidebarDto;
	}

}
