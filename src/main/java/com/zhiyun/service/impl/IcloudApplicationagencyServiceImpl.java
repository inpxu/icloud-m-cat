/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudUserApplicationagencyDao;
import com.zhiyun.dao.IcloudUserApplicationentryDao;
import com.zhiyun.entity.*;
import com.zhiyun.liferay.constants.LiferayErrorCodeConstant;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudApplicationagencyDao;
import com.zhiyun.service.IcloudApplicationagencyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudApplicationagencyService")
public class IcloudApplicationagencyServiceImpl extends BaseServiceImpl<IcloudApplicationagency, Long> implements IcloudApplicationagencyService {

	@Resource
	private IcloudApplicationagencyDao icloudApplicationagencyDao;

	@Resource
	private IcloudUserApplicationagencyDao icloudUserApplicationagencyDao;

	@Override
	protected BaseDao<IcloudApplicationagency, Long> getBaseDao() {
		return this.icloudApplicationagencyDao;
	}

	@Override
	@Transactional
	public void save(IcloudApplicationagency icloudApplicationagency) {
		icloudApplicationagency.setUserId(UserHolder.getId());
		icloudApplicationagency.setStatus(AuditState.AUDITING);
		icloudApplicationagency.setSended("F");
		icloudApplicationagency.setUpdated("F");
		icloudApplicationagency.setOrganizationId(UserHolder.getOrgId());
		icloudApplicationagency.setCreateBy(UserHolder.getUserName());
		icloudApplicationagency.setModifyBy(UserHolder.getUserName());

		icloudApplicationagencyDao.insert(icloudApplicationagency);

		IcloudUserApplicationagency icloudUserApplicationagency = new IcloudUserApplicationagency();
		icloudUserApplicationagency.setUserId(UserHolder.getId());
		icloudUserApplicationagency.setApplicationagencyId(icloudApplicationagency.getId());

		icloudUserApplicationagencyDao.insert(icloudUserApplicationagency);
	}

	@Override
	@Transactional
	public IcloudApplicationagency updateApplicationAgency(IcloudApplicationagency icloudApplicationagency) {
		IcloudApplicationagency agency = icloudApplicationagencyDao.get(icloudApplicationagency.getId());

		if(!agency.getUserId().equals(UserHolder.getId())){
			throw new BusinessException("非所属代理无法修改");
		}

		if(agency.getStatus().equals(AuditState.AUDITING)){
			throw new BusinessException("申请正在审核中，不能进行修改");
		}

		if(agency.getStatus().equals(AuditState.AUDITED)){

			Long fromApplicationAgencyId = icloudApplicationagency.getId();
			icloudApplicationagency.setUserId(UserHolder.getId());
			icloudApplicationagency.setStatus(AuditState.AUDITING);
			icloudApplicationagency.setSended("F");
			icloudApplicationagency.setUpdated("F");
			icloudApplicationagency.setOrganizationId(UserHolder.getOrgId());
			icloudApplicationagency.setCreateBy(UserHolder.getUserName());
			icloudApplicationagency.setModifyBy(UserHolder.getUserName());
			icloudApplicationagency.setId(null);

			icloudApplicationagencyDao.insert(icloudApplicationagency);
			icloudApplicationagencyDao.delete(fromApplicationAgencyId);
			Long toApplicationEntryId = icloudApplicationagency.getId();
			icloudUserApplicationagencyDao.updateByApplicationAgencyId(fromApplicationAgencyId,toApplicationEntryId);


		}else if(agency.getStatus().equals(AuditState.UNAUDITED)){

			icloudApplicationagency.setStatus(AuditState.AUDITING);
			icloudApplicationagency.setSended("F");
			icloudApplicationagency.setUpdated("T");
			icloudApplicationagency.setApprovalOpinion(null);
			icloudApplicationagencyDao.update(icloudApplicationagency);

		}

		return icloudApplicationagency;
	}

	@Override
	public List<Object> getAgencyAreaByParams(Map params) {
		return icloudApplicationagencyDao.getAgencyAreaByParams(params);
	}
}
