/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.interfaceImpl;

import javax.annotation.Resource;

import com.zhiyun.constants.AuditState;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.impl.RoleInvoker;
import com.zhiyun.liferay.impl.UserInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudPersonalauthDao;
import com.zhiyun.entity.IcloudPersonalauth;
import com.zhiyun.internal.server.icloud.service.PersonalAuthInterface;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-16上午9:56:38
 */
@Service("personalAuthInterface")
public class PersonalAuthInterfaceImpl extends BaseServiceImpl<IcloudPersonalauth, Long> implements PersonalAuthInterface {

private static final Logger LOGGER = LoggerFactory.getLogger(PersonalAuthInterfaceImpl.class);
	
	@Resource
	private IcloudPersonalauthDao icloudPersonalauthDao;



	@Resource
	private RoleInvoker roleInvoker;
	
	@Override
	protected BaseDao<IcloudPersonalauth, Long> getBaseDao() {
		return this.icloudPersonalauthDao;
	}
	@Override
	public BaseResult<String> updateStatus(Long id, Integer status,String approvalOpinion) {
		BaseResult<String> result = new BaseResult<>();
 		result.setResult(true);
		result.setMessage("调用成功!");
		try {
			IcloudPersonalauth icloudPersonalauth = new IcloudPersonalauth();
			icloudPersonalauth.setId(id);
			icloudPersonalauth.setApprovalOpinion(approvalOpinion);
			icloudPersonalauth.setStatus(status);
			icloudPersonalauth.setUpdated("F");
			icloudPersonalauthDao.update(icloudPersonalauth);

			//角色定义
			IcloudPersonalauth ipa = icloudPersonalauthDao.get(id);

			if(status == AuditState.AUDITED){
				roleInvoker.addUserRoles(ipa.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
			}

//			if(status == AuditState.UNAUDITED){
//				roleInvoker.unsetUserRoles(ipa.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
//			}else if(status == AuditState.AUDITED){
//				roleInvoker.addUserRoles(ipa.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
//			}

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
