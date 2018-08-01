/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudMarketentrydatashareurlDao;
import com.zhiyun.entity.IcloudMarketentrydatashareurl;
import com.zhiyun.service.IcloudMarketentrydatashareurlService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudMarketentrydatashareurlService")
public class IcloudMarketentrydatashareurlServiceImpl extends BaseServiceImpl<IcloudMarketentrydatashareurl, Long> implements IcloudMarketentrydatashareurlService {

	@Resource
	private IcloudMarketentrydatashareurlDao icloudMarketentrydatashareurlDao;

	@Override
	protected BaseDao<IcloudMarketentrydatashareurl, Long> getBaseDao() {
		return this.icloudMarketentrydatashareurlDao;
	}

	@Override
	public List<IcloudMarketentrydatashareurl> findByMarketEntryId(long marketEntryId) {
		return icloudMarketentrydatashareurlDao.findByMarketEntryId(marketEntryId);
	}
}
