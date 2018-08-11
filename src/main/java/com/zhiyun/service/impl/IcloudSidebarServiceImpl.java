
package com.zhiyun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.RoleIdConstant;
import com.zhiyun.context.OnlineUser;
import com.zhiyun.dao.IcloudSidebarDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.dto.IcloudSidebarDto;
import com.zhiyun.dto.IcloudSidebarDto.SideBar;
import com.zhiyun.entity.IcloudSidebar;
import com.zhiyun.entity.User;
import com.zhiyun.internal.server.icloud.constant.ErrorCode;
import com.zhiyun.liferay.impl.RoleInvoker;
import com.zhiyun.liferay.model.LiferayInvokerResult;
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
	
	@Resource
	private RoleInvoker roleInvoker;

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
	
	
	@Override
	public IcloudSidebarDto listByUser(OnlineUser user) {

		LiferayInvokerResult result =roleInvoker.getUserRoles(user.getId());
		List<Long> roleIdList = new ArrayList<Long>();
		if(result.getResult() != null){
			JSONArray roleArr = (JSONArray) result.getResult();
			 for (Iterator iter = roleArr.iterator(); iter.hasNext();) { 
		          JSONObject roleJson = (JSONObject) iter.next(); 
		          roleIdList.add(roleJson.getLongValue("roleId"));
			 } 
        }
		List<IcloudSidebar> icloudSidebarList =  icloudSidebarDao.listByRoleIds(roleIdList);

		Map<Long,List<IcloudSidebar>> parentIdRefMap = new HashMap<Long,List<IcloudSidebar>>();
		for(IcloudSidebar sideBar: icloudSidebarList) {
			if(parentIdRefMap.containsKey(sideBar.getParentid())){
				parentIdRefMap.get(sideBar.getParentid()).add(sideBar);
			}else {
				List<IcloudSidebar> tempList = new ArrayList<IcloudSidebar>();
				tempList.add(sideBar);
				parentIdRefMap.put(sideBar.getParentid(), tempList);
			}
		}
		
		//获取 parentId =0 
		IcloudSidebarDto icloudSidebarDto = new IcloudSidebarDto();
		
		List<IcloudSidebar> list = parentIdRefMap.get(0L);
		for(IcloudSidebar bar : list) {
			List<IcloudSidebar> children = parentIdRefMap.get(bar.getId());
			IcloudSidebarDto.SideBar sideBar = new IcloudSidebarDto.SideBar();
			
			for(IcloudSidebar child: children) {
				IcloudSidebarDto.Item item = new IcloudSidebarDto.Item();
				item.setId(child.getId());
				item.setLink(child.getUrl());
				item.setName(child.getName());
				sideBar.getChildrens().add(item);
			}
			
			sideBar.setTitle(bar.getName());
			sideBar.setId(bar.getId());
			icloudSidebarDto.getNavList().add(sideBar);
		}

	

		return icloudSidebarDto;
	}

}
