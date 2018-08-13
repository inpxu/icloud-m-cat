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
public class IcloudApplicationagencyqualityimageshareurl extends BaseEntity<Long> {

	private static final long serialVersionUID = 6803660613399504383L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long applicationAgencyId;
	// 
	@Pattern(regexp="[\\s\\S]{0,100}", message="字段过长")
	private String qualityImageShareUrl;

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
	public Long getApplicationAgencyId() {
		return this.applicationAgencyId;
	}

	/**
	 * 
	 */
	public void setApplicationAgencyId(Long applicationAgencyId) {
		this.applicationAgencyId = applicationAgencyId;
	}
	
	/**
	 * 
	 */
	public String getQualityImageShareUrl() {
		return this.qualityImageShareUrl;
	}

	/**
	 * 
	 */
	public void setQualityImageShareUrl(String qualityImageShareUrl) {
		this.qualityImageShareUrl = qualityImageShareUrl;
	}
}
