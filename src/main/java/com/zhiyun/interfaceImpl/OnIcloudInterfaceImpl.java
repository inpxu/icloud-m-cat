/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.interfaceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudOnicloudDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.entity.IcloudOnicloud;
import com.zhiyun.entity.User;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import com.zhiyun.internal.server.icloud.service.OnIcloudInterface;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.impl.RoleInvoker;
import com.zhiyun.liferay.util.ScreenNameConventer;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018年8月14日上午11:16:14
 */
@Service("onIcloudInterface")
public class OnIcloudInterfaceImpl extends BaseServiceImpl<IcloudOnicloud, Long> implements OnIcloudInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(OnIcloudInterfaceImpl.class);
	
	@Resource
	private IcloudOnicloudDao icloudOnicloudDao;

	@Resource
	private InterfaceForUser interfaceForUser;

	@Resource
	private UserDao userDao;

	@Resource
	private RoleInvoker roleInvoker;

	@Override
	protected BaseDao<IcloudOnicloud, Long> getBaseDao() {
		return this.icloudOnicloudDao;
	}
	
	@Override
	public BaseResult<String> updateStatus(Long id, Integer status, String approvalOpinion) {
		BaseResult<String> result = new BaseResult<>();
		result.setResult(true);
		result.setMessage("调用成功!");
		try {
			IcloudOnicloud icloud = new IcloudOnicloud();
			icloud.setId(id);
			icloud.setStatus(status);
			icloud.setApprovalOpinion(approvalOpinion);
			icloud.setUpdated("F");
			icloudOnicloudDao.update(icloud);
			
			//分发个人信息
			if(status ==AuditState.AUDITED){
				IcloudOnicloud iae = icloudOnicloudDao.get(id);
				//角色定义
				roleInvoker.addUserRoles(iae.getCreateUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
				//分发个人信息
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
