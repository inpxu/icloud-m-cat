/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;

/**
 * 实体类
 * 代理关联
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class IcloudUserApplicationagency extends BaseEntity<Long> {

	private static final long serialVersionUID = 2196403967473734094L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long userId;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long applicationagencyId;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	/**
	 * 
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 
	 */
	public Long getApplicationagencyId() {
		return this.applicationagencyId;
	}

	/**
	 * 
	 */
	public void setApplicationagencyId(Long applicationagencyId) {
		this.applicationagencyId = applicationagencyId;
	}
}
