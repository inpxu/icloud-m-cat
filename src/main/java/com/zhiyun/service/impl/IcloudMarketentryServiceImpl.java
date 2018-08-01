/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.service.IcloudUserMarketentryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.oned.EAN13Reader;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudMarketentryDao;
import com.zhiyun.dao.IcloudMarketentrydatashareurlDao;
import com.zhiyun.entity.IcloudMarketentry;
import com.zhiyun.entity.IcloudMarketentrydatashareurl;
import com.zhiyun.service.IcloudMarketentryService;
import org.springframework.validation.FieldError;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudMarketentryService")
public class IcloudMarketentryServiceImpl extends BaseServiceImpl<IcloudMarketentry, Long>
		implements IcloudMarketentryService {

	@Resource
	private IcloudMarketentryDao icloudMarketentryDao;

	@Resource
	private IcloudMarketentrydatashareurlDao icloudMarketentrydatashareurlDao;

	@Resource
	private IcloudUserMarketentryService icloudUserMarketentryService;

	@Override
	protected BaseDao<IcloudMarketentry, Long> getBaseDao() {
		return this.icloudMarketentryDao;
	}

	@Override
	@Transactional
	public void save(IcloudMarketentry icloudMarketentry) {

		Long oldMarketentryId = icloudMarketentry.getId();
		icloudMarketentry.setId(null);

		try {
			icloudMarketentryDao.insert(icloudMarketentry);
		} catch (Exception e) {
			if (e instanceof org.springframework.dao.DuplicateKeyException) {
				throw new BusinessException("请勿重复入市！");
			}
		}
		if(oldMarketentryId != null){
			icloudMarketentrydatashareurlDao.deleteByMarketEntryId(oldMarketentryId);
		}
		List<IcloudMarketentrydatashareurl> urls = icloudMarketentry.getMarketEntryDataShareUrls();
		if (CollectionUtils.isNotEmpty(urls)) {
			for (IcloudMarketentrydatashareurl url : urls) {
				url.setId(null);
				url.setIcloudMarketEntryId(icloudMarketentry.getId());
			}
			icloudMarketentrydatashareurlDao.insert(urls);
		}
	}

	@Override
	@Transactional
	public void update(IcloudMarketentry icloudMarketentry) {
		icloudMarketentryDao.update(icloudMarketentry);

		if (icloudMarketentry.getId() != null) {
			icloudMarketentrydatashareurlDao.deleteByMarketEntryId(icloudMarketentry.getId());
			List<IcloudMarketentrydatashareurl> urls = icloudMarketentry.getMarketEntryDataShareUrls();
			if (CollectionUtils.isNotEmpty(urls)) {
				for (IcloudMarketentrydatashareurl url : urls) {
					url.setId(null);
					url.setIcloudMarketEntryId(icloudMarketentry.getId());
				}
				icloudMarketentrydatashareurlDao.insert(urls);
			}
		}

	}

	@Override
	public List<IcloudMarketentry> findBySended() {
		return icloudMarketentryDao.findBySended();
	}

	@Override
	public int updateSended(Long id) {
		return icloudMarketentryDao.updateSended(id);
	}

	@Override
	public List<IcloudMarketentrydatashareurl> findUrl(Long id) {
		return icloudMarketentrydatashareurlDao.findUrl(id);
	}

	@Override
	public IcloudMarketentry findByUserId(Long userId) {
		return icloudMarketentryDao.findByUserId(userId);
	}

	@Transactional
	@Override
	public void updateIcloudMarketentry(IcloudMarketentry icloudMarketentry) {

		Long id = icloudMarketentry.getId();
		Long LogId = UserHolder.getId();


		IcloudMarketentry entity =new IcloudMarketentry();
		entity.setId(id);
		this.find(entity);

		Long userId = this.get(id).getCreateUserId();
		if (LogId != null && !LogId.equals(userId)) {
			throw new BusinessException("权限不足，不能进行修改");
		}
		Integer status = this.get(id).getStatus();
		if (status != null && status.equals(AuditState.AUDITING)) {
			throw new BusinessException("申请正在审核中，不能进行修改");
		}
		// 校验参数是否合法


		icloudMarketentry.setCreateUserId(UserHolder.getId());

		if (status != null && status.equals(AuditState.UNAUDITED)) {
			// 未通过, 修改
			icloudMarketentry.setStatus(AuditState.AUDITING);
			icloudMarketentry.setUpdated("T");
			icloudMarketentry.setSended("F");
			icloudMarketentry.setApprovalOpinion(null);
			this.update(icloudMarketentry);
		} else if (status != null && status.equals(AuditState.AUDITED)) {
			// 通过, 删除原数据, 新增
			icloudMarketentry.setStatus(AuditState.AUDITING);
			icloudMarketentry.setSended("F");
			icloudMarketentry.setUpdated("F");
			this.save(icloudMarketentry);
			this.delete(id);
			// 修改关联的marketentry_id
			Long newId = icloudMarketentry.getId();
			icloudUserMarketentryService.updateMarketentryId(id, newId);
		}
	}

}
