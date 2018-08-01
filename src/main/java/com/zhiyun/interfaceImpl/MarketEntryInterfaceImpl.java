/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.interfaceImpl;

import javax.annotation.Resource;

import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudUserMarketentryDao;
import com.zhiyun.entity.IcloudUserApplicationentry;
import com.zhiyun.entity.IcloudUserMarketentry;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.impl.RoleInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudMarketentryDao;
import com.zhiyun.entity.IcloudMarketentry;
import com.zhiyun.internal.server.icloud.service.MarketEntryInterface;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-16上午9:50:06
 */
@Service("marketEntryInterface")
public class MarketEntryInterfaceImpl extends BaseServiceImpl<IcloudMarketentry, Long> implements MarketEntryInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarketEntryInterfaceImpl.class);
	
	@Resource
	private IcloudMarketentryDao icloudMarketentryDao;

	@Resource
	private IcloudUserMarketentryDao icloudUserMarketentryDao;

	@Resource
	private RoleInvoker roleInvoker;
	
	@Override
	protected BaseDao<IcloudMarketentry, Long> getBaseDao() {
		return this.icloudMarketentryDao;
	}
	
	@Override
	public BaseResult<String> updateStatus(Long id, Integer status,String approvalOpinion) {
		BaseResult<String> result = new BaseResult<>();
		result.setResult(true);
		result.setMessage("调用成功!");
		try {
			IcloudMarketentry icloudMarketentry = new IcloudMarketentry();
			icloudMarketentry.setId(id);
			icloudMarketentry.setStatus(status);
			icloudMarketentry.setApprovalOpinion(approvalOpinion);
			icloudMarketentry.setUpdated("F");
			icloudMarketentryDao.update(icloudMarketentry);

			IcloudUserMarketentry ium = icloudUserMarketentryDao.getByMarketentryId(id);

			if(status == AuditState.AUDITED){
				roleInvoker.addUserRoles(ium.getUserId(),new Long[]{UserConstant.IC_FACILITATOR_USER_ROLE_ID});
			}

//			if(status == AuditState.UNAUDITED){
//				roleInvoker.unsetUserRoles(ium.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
//			}else if(status == AuditState.AUDITED){
//				roleInvoker.addUserRoles(ium.getUserId(),new Long[]{UserConstant.IC_ADVANCED_USER_ROLE_ID});
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

