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
import com.zhiyun.dao.IcloudApplicationagencyqualityimageshareurlDao;
import com.zhiyun.entity.IcloudApplicationagencyqualityimageshareurl;
import com.zhiyun.service.IcloudApplicationagencyqualityimageshareurlService;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudApplicationagencyqualityimageshareurlService")
public class IcloudApplicationagencyqualityimageshareurlServiceImpl extends BaseServiceImpl<IcloudApplicationagencyqualityimageshareurl, Long> implements IcloudApplicationagencyqualityimageshareurlService {

	@Resource
	private IcloudApplicationagencyqualityimageshareurlDao icloudApplicationagencyqualityimageshareurlDao;

	@Override
	protected BaseDao<IcloudApplicationagencyqualityimageshareurl, Long> getBaseDao() {
		return this.icloudApplicationagencyqualityimageshareurlDao;
	}
	


	@Override
	public List<String> findUrl(Long id) {
		return icloudApplicationagencyqualityimageshareurlDao.findUrl(id);
	}
}
