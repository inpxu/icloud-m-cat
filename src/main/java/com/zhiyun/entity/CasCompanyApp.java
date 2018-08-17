/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CasCompanyApp extends BaseEntity<Long> {

	private static final long serialVersionUID = 5549074732735233598L;

	// ~~~~实体属性
	// 应用主键
	@Max(value=9223372036854775807L,message="应用主键字段过长")
	private Long appId;
	// 生效日期
	private java.util.Date effectiveDate;
	// 失效日期
	private java.util.Date expiryDate;
	// 企业主键
	@Max(value=9223372036854775807L,message="企业主键字段过长")
	private Long companyId;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	/**
	 * 应用主键
	 */
	public Long getAppId() {
		return this.appId;
	}

	/**
	 * 应用主键
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	/**
	 * 生效日期
	 */
	public java.util.Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * 生效日期
	 */
	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * 失效日期
	 */
	public java.util.Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * 失效日期
	 */
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/**
	 * 企业主键
	 */
	public Long getCompanyId() {
		return this.companyId;
	}

	/**
	 * 企业主键
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
