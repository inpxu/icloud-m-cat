/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.CasCompanyAppDao;
import com.zhiyun.entity.CasCompanyApp;
import com.zhiyun.service.CasCompanyAppService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("casCompanyAppService")
public class CasCompanyAppServiceImpl extends BaseServiceImpl<CasCompanyApp, Long> implements CasCompanyAppService {

	@Resource
	private CasCompanyAppDao casCompanyAppDao;

	@Override
	protected BaseDao<CasCompanyApp, Long> getBaseDao() {
		return this.casCompanyAppDao;
	}
}
