/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.interfaceImpl;

import javax.annotation.Resource;

import com.zhiyun.constants.AuditState;
import com.zhiyun.entity.IcloudPersonalauth;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.impl.RoleInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudEnterpriseauthDao;
import com.zhiyun.entity.IcloudEnterpriseauth;
import com.zhiyun.internal.server.icloud.service.EntrypriseAuthInterface;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-16上午9:44:04
 */
@Service("entrypriseAuthInterface")
public class EntrypriseAuthInterfaceImpl extends BaseServiceImpl<IcloudEnterpriseauth, Long> implements EntrypriseAuthInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntrypriseAuthInterfaceImpl.class);
	
	@Resource
	private IcloudEnterpriseauthDao icloudEnterpriseauthDao;

	@Resource
	private InterfaceForUser interfaceForUser;

	@Resource
	private RoleInvoker roleInvoker;
	
	@Override
	protected BaseDao<IcloudEnterpriseauth, Long> getBaseDao() {
		return this.icloudEnterpriseauthDao;
	}
	
	@Override
	public BaseResult<String> updateStatus(Long id, Integer status,String approvalOpinion) {
		
		BaseResult<String> result = new BaseResult<>();
		result.setResult(true);
		result.setMessage("调用成功!");
		try {
			IcloudEnterpriseauth icloudEnterpriseauth = new IcloudEnterpriseauth();
			icloudEnterpriseauth.setId(id);
			icloudEnterpriseauth.setStatus(status);
			icloudEnterpriseauth.setApprovalOpinion(approvalOpinion);
			icloudEnterpriseauth.setUpdated("F");
			icloudEnterpriseauthDao.update(icloudEnterpriseauth);

			IcloudEnterpriseauth iea = icloudEnterpriseauthDao.get(id);
			//角色定义
			if(status == AuditState.AUDITED){
				roleInvoker.addUserRoles(iea.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
			}


//
//			if(status == AuditState.UNAUDITED){
//				roleInvoker.unsetUserRoles(iea.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
//			}else if(status == AuditState.AUDITED){
//				roleInvoker.addUserRoles(iea.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
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
