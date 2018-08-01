/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zhiyun.dao.IcloudUserApplicationentryDao;
import com.zhiyun.entity.IcloudUserApplicationentry;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.mapper.Mapper.Null;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.enums.Deleted;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudApplicationentryDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.dto.DataCenterUserDto;
import com.zhiyun.entity.IcloudApplicationentry;
import com.zhiyun.entity.User;
import com.zhiyun.liferay.constants.LiferayErrorCodeConstant;
import com.zhiyun.liferay.impl.OrganizationInvoker;
import com.zhiyun.liferay.impl.UserInvoker;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import com.zhiyun.service.IcloudApplicationentryService;
import com.zhiyun.util.HttpUtil;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudApplicationentryService")
public class IcloudApplicationentryServiceImpl extends BaseServiceImpl<IcloudApplicationentry, Long>
		implements IcloudApplicationentryService {

	private static final int DATA_CENTER_USER_ADD = 1;

	private static final int DATA_CENTER_USER_UPDATE = 2;

	@Value("${data.center.user.distribution.url}")
	private String dataCenterUserDistributionUrl;

	@Value("${application.process.def.url}")
	private String applicationProcessDefUrl;

	@Resource
	private IcloudApplicationentryDao icloudApplicationentryDao;

	@Resource
	private IcloudUserApplicationentryDao icloudUserApplicationentryDao;

	@Resource
	private UserDao userDao;

	@Resource
	private OrganizationInvoker organizationInvoker;

	@Resource
	private UserInvoker userInvoker;

	private static final Logger LOGGER = LoggerFactory.getLogger(IcloudApplicationentryServiceImpl.class);

	@Override
	protected BaseDao<IcloudApplicationentry, Long> getBaseDao() {
		return this.icloudApplicationentryDao;
	}

	@Override
	public IcloudApplicationentry findIcloudApplicationentryByUserId(Long userId) {

		User user = null;
		if (userId == null) {
			String screenName = UserHolder.getUser().getAccountName();
			user = userDao.findUserByScreenName(screenName);
			userId = user.getUserid();
		}

		IcloudApplicationentry icloudApplicationentry = icloudApplicationentryDao.findByUserId(userId);

		return icloudApplicationentry;
	}

	@Override
	public IcloudApplicationentry updateApplicationEntry(IcloudApplicationentry icloudApplicationentry) {

		IcloudApplicationentry entry = icloudApplicationentryDao.findByUserId(UserHolder.getId());

		if(!entry.getCreateUserId().equals(UserHolder.getId())){
			throw new BusinessException("非企业管理者无法修改");
		}

		if(entry.getStatus().equals(AuditState.AUDITING)){
			throw new BusinessException("申请正在审核中，不能进行修改");
		}

		LiferayInvokerResult result = organizationInvoker.getUserOrganization(UserHolder.getId());

		if (result.getResult() == null || ((JSONArray) result.getResult()).size() <= 0) {
			throw new BusinessException("系统异常请重试！");
		}

		long orgId = ((JSONArray) result.getResult()).getJSONObject(0).getLong("organizationId");
		result = organizationInvoker.updateOrganization(orgId, icloudApplicationentry.getName());

		icloudApplicationentry.setOrganizationId(orgId);
		if (result.getResult() == null) {
			if (result.getError() != null && ((JSONObject) result.getError())
					.getLong("code") == LiferayErrorCodeConstant.DUPLICATE_ORGANIZATION_ERROR) {
				throw new BusinessException("公司名已存在！");
			}
			throw new BusinessException("重新入驻失败请重试");
		}

		if(entry.getStatus().equals(AuditState.AUDITED)){

			Long fromApplicationEntryId = icloudApplicationentry.getId();

			icloudApplicationentry.setStatus(AuditState.AUDITING);
			icloudApplicationentry.setSended("F");
			icloudApplicationentry.setUpdated("F");
			icloudApplicationentry.setId(null);

			icloudApplicationentryDao.insert(icloudApplicationentry);
			icloudApplicationentryDao.delete(fromApplicationEntryId);
			Long toApplicationEntryId = icloudApplicationentry.getId();
			icloudUserApplicationentryDao.updateByApplicationEntryId(fromApplicationEntryId,toApplicationEntryId);


		}else if(entry.getStatus().equals(AuditState.UNAUDITED)){

			icloudApplicationentry.setStatus(AuditState.AUDITING);
			icloudApplicationentry.setSended("F");
			icloudApplicationentry.setUpdated("T");
			icloudApplicationentry.setApprovalOpinion(null);
			icloudApplicationentryDao.update(icloudApplicationentry);

		}

		return icloudApplicationentry;

	}

	@Override
	public void save(IcloudApplicationentry icloudApplicationentry) {

		icloudApplicationentry.setStatus(AuditState.AUDITING);
		icloudApplicationentry.setSended("F");
		icloudApplicationentry.setUpdated("F");

		LiferayInvokerResult result = organizationInvoker.addOrganization(icloudApplicationentry.getName());

		if (result.getError() != null) {
			if (result.getError() != null && ((JSONObject) result.getError())
					.getLong("code") == LiferayErrorCodeConstant.DUPLICATE_ORGANIZATION_ERROR) {
				throw new BusinessException("公司名已存在！");
			}
			throw new BusinessException("入驻申请提交失败请重试！");
		}

		User user = null;
		Long userId = icloudApplicationentry.getCreateUserId();
		if (userId == null) {
			user = userDao.findUserByScreenName(UserHolder.getUser().getAccountName());
			userId = user.getUserid();
		} else {
			user = userDao.findUserByUserId(userId);
		}

		JSONObject org = (JSONObject) result.getResult();
		userInvoker.updateOrganizations(user.getUserid(), new long[] { org.getLong("organizationId") });

		icloudApplicationentry.setOrganizationId(org.getLong("organizationId"));
		icloudApplicationentryDao.insert(icloudApplicationentry);

		IcloudUserApplicationentry icloudUserApplicationentry = new IcloudUserApplicationentry();
		icloudUserApplicationentry.setUserId(userId);
		icloudUserApplicationentry.setApplicationentryId(icloudApplicationentry.getId());

		icloudUserApplicationentryDao.insert(icloudUserApplicationentry);

	}

	private void doAudit(IcloudApplicationentry icloudApplicationentry) {
		// FIX ME
	}

	private void addUserInApplicationProcess(long companyId, long userId) {
		JSONObject data = new JSONObject();
		data.put("companyId", companyId);
		data.put("userId", userId);
		String result = HttpUtil.post(applicationProcessDefUrl, data);
		LOGGER.info("add user in application process result is " + result);

	}

	private void doDistributeUser(String companyName, long companyId, long userId, String screenName, String name,
			String email, int disposeType) {
		List<DataCenterUserDto> userDtos = new ArrayList<>();
		String phone = screenName.replaceAll("p", "");
		userDtos.add(new DataCenterUserDto(companyName, companyId, userId, phone, name, email, "企业", screenName));
		JSONObject data = new JSONObject();
		data.put("data", userDtos);
		data.put("disposeType", disposeType);
		String result = HttpUtil.post(dataCenterUserDistributionUrl, data);
		LOGGER.info("do distribute user in data center result is " + result);
	}

	public void addUserInDataCenter(String companyName, long companyId, long userId, String screenName, String name,
			String email) {
		doDistributeUser(companyName, companyId, userId, screenName, name, email, DATA_CENTER_USER_ADD);
	}

	public void updateUserInDataCenter(String companyName, long companyId, long userId, String screenName, String name,
			String email) {
		doDistributeUser(companyName, companyId, userId, screenName, name, email, DATA_CENTER_USER_UPDATE);
	}

	@Override
	public List<IcloudApplicationentry> findBySended() {
		return icloudApplicationentryDao.findBySended();
	}

	@Override
	public int updateSended(Long id) {
		return icloudApplicationentryDao.updateSended(id);
	}

	@Override
	public IcloudApplicationentry findByUserId(Long userId) {
		return icloudApplicationentryDao.findByUserId(userId);
	}

}
