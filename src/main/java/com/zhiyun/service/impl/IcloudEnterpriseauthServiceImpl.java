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
import com.zhiyun.dao.IcloudPersonalauthDao;
import com.zhiyun.dao.IcloudUserPersonalauthDao;
import com.zhiyun.entity.IcloudPersonalauth;
import com.zhiyun.service.IcloudPersonalauthService;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudEnterpriseauthDao;
import com.zhiyun.entity.IcloudEnterpriseauth;
import com.zhiyun.service.IcloudEnterpriseauthService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudEnterpriseauthService")
public class IcloudEnterpriseauthServiceImpl extends BaseServiceImpl<IcloudEnterpriseauth, Long> implements IcloudEnterpriseauthService {

	@Resource
	private IcloudEnterpriseauthDao icloudEnterpriseauthDao;

	@Resource
	private IcloudPersonalauthService icloudPersonalauthService;

	@Override
	protected BaseDao<IcloudEnterpriseauth, Long> getBaseDao() {
		return this.icloudEnterpriseauthDao;
	}

	@Override
	public List<IcloudEnterpriseauth> findBySended() {
		return icloudEnterpriseauthDao.findBySended();
	}

	@Override
	public int updateSended(Long id) {
		return icloudEnterpriseauthDao.updateSended(id);
	}

	@Override
	public IcloudEnterpriseauth findByUserId(Long userId) {
		return icloudEnterpriseauthDao.findByUserId(userId);
	}

	@Transactional
	@Override
	public void updateEnterpriseauth(IcloudEnterpriseauth icloudEnterpriseauth) {

		Long id = icloudEnterpriseauth.getId();
		Integer status = 0;

		if(id != null){
            status = this.get(icloudEnterpriseauth.getId()).getStatus();
            if (status != null && status == 1) {
                throw new BusinessException("申请正在审核中，不能进行修改");
            }
        }else {
		    saveIcloudEnterpriseauth(icloudEnterpriseauth);
		    return;
        }


		if (status != null && status == 3) {
			icloudEnterpriseauth.setStatus(AuditState.AUDITING);
			icloudEnterpriseauth.setUpdated("T");
			icloudEnterpriseauth.setSended("F");
			this.update(icloudEnterpriseauth);
		} else if (status != null && status == 2) {
			icloudEnterpriseauth.setStatus(AuditState.AUDITING);
			icloudEnterpriseauth.setSended("F");
			icloudEnterpriseauth.setUpdated("F");
			icloudEnterpriseauth.setId(null);
			this.insert(icloudEnterpriseauth);
			this.delete(id);
		}

	}



	@Override
	public void deleteByUserId(Long userId) {
		IcloudEnterpriseauth icloudEnterpriseauth = new IcloudEnterpriseauth();
		icloudEnterpriseauth.setDeleted("T");
		icloudEnterpriseauth.setUserId(userId);
		icloudEnterpriseauthDao.deleteByUserId(icloudEnterpriseauth);
	}

	@Transactional
	@Override
	public void saveIcloudEnterpriseauth(IcloudEnterpriseauth icloudEnterpriseauth) {

        icloudEnterpriseauth.setStatus(AuditState.AUDITING);
        icloudEnterpriseauth.setSended("F");
        icloudEnterpriseauth.setUpdated("F");

        IcloudEnterpriseauth dbIcloudEnterpriseauth = icloudEnterpriseauthDao.findByUserId(UserHolder.getId());

        if(dbIcloudEnterpriseauth != null){
            throw new BusinessException("请勿重复认证");
        }

		this.insert(icloudEnterpriseauth);
		icloudPersonalauthService.deleteByUserId(UserHolder.getId());
	}
}
