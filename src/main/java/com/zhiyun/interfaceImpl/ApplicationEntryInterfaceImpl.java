/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.interfaceImpl;

import javax.annotation.Resource;

import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudUserApplicationentryDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.entity.*;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.impl.UserInvoker;
import com.zhiyun.liferay.util.ScreenNameConventer;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudApplicationentryDao;
import com.zhiyun.internal.server.icloud.service.ApplicationEntryInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-16上午8:57:20
 */
@Service("applicationEntryInterface")
public class ApplicationEntryInterfaceImpl extends BaseServiceImpl<IcloudApplicationentry, Long> implements ApplicationEntryInterface {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEntryInterfaceImpl.class);
	
	@Resource
	private IcloudApplicationentryDao icloudApplicationentryDao;

	@Resource
	private IcloudUserApplicationentryDao icloudUserApplicationentryDao;

	@Resource
	private InterfaceForUser interfaceForUser;

	@Resource
	private UserDao userDao;

	@Resource
	private UserInvoker userInvoker;
	
	@Override
	protected BaseDao<IcloudApplicationentry, Long> getBaseDao() {
		return this.icloudApplicationentryDao;
	}

	@Override
	public BaseResult<String> updateStatus(Long id, Integer status,String approvalOpinion) {
		BaseResult<String> result = new BaseResult<>();
		result.setResult(true);
		result.setMessage("调用成功!");
		try {
			IcloudApplicationentry icloudApplicationentry = new IcloudApplicationentry();
			icloudApplicationentry.setId(id);
			icloudApplicationentry.setStatus(status);
			icloudApplicationentry.setApprovalOpinion(approvalOpinion);
			icloudApplicationentry.setUpdated("F");
			icloudApplicationentryDao.update(icloudApplicationentry);

			List<IcloudUserApplicationentry> iuaes = icloudUserApplicationentryDao.listByApplicationentryId(id);
			if(CollectionUtils.isNotEmpty(iuaes)){
				List<Long> userIds = new ArrayList<>();
				for(int i = 0 ; i < iuaes.size();i++){
					userIds.add(iuaes.get(i).getUserId());
				}

				if(status == AuditState.AUDITED){
					userInvoker.addRoleUsers(UserConstant.IC_VIP_USER_ID,userIds.toArray(new Long[0]));
				}

//				if(status == AuditState.UNAUDITED){
//					userInvoker.unsetRoleUsers(UserConstant.IC_VIP_USER_ID,userIds.toArray(new Long[0]));
//				}else if(status == AuditState.AUDITED){
//					userInvoker.addRoleUsers(UserConstant.IC_VIP_USER_ID,userIds.toArray(new Long[0]));
//				}
			}

			//分发个人信息
			if(status ==AuditState.AUDITED){
				IcloudApplicationentry iae = icloudApplicationentryDao.get(id);
				com.zhiyun.internal.server.auth.entity.CasCompany casCompany = new com.zhiyun.internal.server.auth.entity.CasCompany();
				casCompany.setCompanyName(iae.getName());
				casCompany.setCompanyId(iae.getOrganizationId());
				casCompany.setId(iae.getOrganizationId());
				User user = userDao.findByUserId(iae.getCreateUserId());
				com.zhiyun.internal.server.auth.entity.CasUser casUser = new com.zhiyun.internal.server.auth.entity.CasUser();
				casUser.setAccount(user.getScreenname());
				casUser.setEmail(user.getEmailaddress());
				casUser.setName(user.getLastname() + user.getFirstname());
				casUser.setId(user.getId());
				casUser.setPhone(ScreenNameConventer.screenNameMinusP(user.getScreenname()));
				casUser.setId(user.getUserid());
				casUser.setCompanyId(iae.getOrganizationId());
				casUser.setIsAble(true);
				casUser.setIsAdmin(true);


				interfaceForUser.insertOrUpdateUser(casUser,casCompany);
			}

		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			result.setResult(false);
			result.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			result.setResult(false);
			result.setMessage("系统异常");
		}
		return result;
	}
	

}
