/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.service.IcloudEnterpriseauthService;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudPersonalauthDao;
import com.zhiyun.entity.IcloudPersonalauth;
import com.zhiyun.service.IcloudPersonalauthService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudPersonalauthService")
public class IcloudPersonalauthServiceImpl extends BaseServiceImpl<IcloudPersonalauth, Long> implements IcloudPersonalauthService {

	@Resource
	private IcloudPersonalauthDao icloudPersonalauthDao;

	@Resource
	private IcloudEnterpriseauthService icloudEnterpriseauthService;

	@Override
	protected BaseDao<IcloudPersonalauth, Long> getBaseDao() {
		return this.icloudPersonalauthDao;
	}

	@Override
	public List<IcloudPersonalauth> findBySended() {
		return icloudPersonalauthDao.findBySended();
	}

	@Override
	public int updateSended(Long id) {
		return icloudPersonalauthDao.updateSended(id);
	}

	@Override
	public IcloudPersonalauth findByUserId(Long userId) {
		return icloudPersonalauthDao.findByUserId(userId);
	}

	@Transactional
	@Override
	public void updateIcloudPersonalauth(IcloudPersonalauth icloudPersonalauth) {

		Long id = icloudPersonalauth.getId();
		Integer status = this.get(icloudPersonalauth.getId()).getStatus();
		if (status != null && status == 1) {
			throw new BusinessException("申请正在审核中，不能进行修改");
		}

		if (status != null && status == 3){
			// TODO 这版先直接默认认证通过
			icloudPersonalauth.setStatus(AuditState.AUDITING);
			icloudPersonalauth.setUpdated("T");
			icloudPersonalauth.setSended("F");
			this.update(icloudPersonalauth);
		} else if (status != null && status == 2) {
			icloudPersonalauth.setStatus(AuditState.AUDITING);
			icloudPersonalauth.setSended("F");
			icloudPersonalauth.setUpdated("F");
			icloudPersonalauth.setId(null);
			this.insert(icloudPersonalauth);
			this.delete(id);
		}

	}

	@Override
	public void deleteByUserId(Long userId) {
		IcloudPersonalauth icloudPersonalauth = new IcloudPersonalauth();
		icloudPersonalauth.setDeleted("T");
		icloudPersonalauth.setUserId(userId);
		icloudPersonalauthDao.deleteByUserId(icloudPersonalauth);
	}

	@Override
	public void savePersonalauth(IcloudPersonalauth icloudPersonalauth) {
		this.insert(icloudPersonalauth);
		icloudEnterpriseauthService.deleteByUserId(UserHolder.getId());
	}
}
