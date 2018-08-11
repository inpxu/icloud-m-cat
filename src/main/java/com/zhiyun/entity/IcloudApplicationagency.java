/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 实体类
 *代理
 * @author auto
 * @version v1.0
 * @date
 */
public class IcloudApplicationagency extends BaseEntity<Long> {

	private static final long serialVersionUID = 1726285389892752506L;

	// ~~~~实体属性
	// 用户id
	@Max(value=9223372036854775807L,message="用户id字段过长")
	private Long userId;
	// 用户姓名
	@Pattern(regexp="[\\s\\S]{0,75}", message="用户姓名字段过长")
	private String name;
	// 代理行业
	@Pattern(regexp="[\\s\\S]{0,20}", message="代理行业字段过长")
	private String agencyType;
	// 代理级别
	@Pattern(regexp="[\\s\\S]{0,20}", message="代理级别字段过长")
	private String agencyLevel;
	// 代理区域
	@Pattern(regexp="[\\s\\S]{0,20}", message="代理区域字段过长")
	private String agencyArea;
	// 相关资质图片
	@Pattern(regexp="[\\s\\S]{0,500}", message="相关资质图片字段过长")
	private String qualityImageShareUrl;
	// 状态
	@Max(value=99999999999L,message="状态字段过长")
	private Integer status;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long organizationId;
	// 
	@Pattern(regexp="[\\s\\S]{0,255}", message="字段过长")
	private String sended;
	// T 是 F 
	@Pattern(regexp="[\\s\\S]{0,1}", message="T 是 F 字段过长")
	private String updated;
	// 审批意见
	@Pattern(regexp="[\\s\\S]{0,255}", message="审批意见字段过长")
	private String approvalOpinion;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	/**
	 * 用户id
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 用户姓名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 代理行业
	 */
	public String getAgencyType() {
		return this.agencyType;
	}

	/**
	 * 代理行业
	 */
	public void setAgencyType(String agencyType) {
		this.agencyType = agencyType;
	}
	
	/**
	 * 代理级别
	 */
	public String getAgencyLevel() {
		return this.agencyLevel;
	}

	/**
	 * 代理级别
	 */
	public void setAgencyLevel(String agencyLevel) {
		this.agencyLevel = agencyLevel;
	}
	
	/**
	 * 代理区域
	 */
	public String getAgencyArea() {
		return this.agencyArea;
	}

	/**
	 * 代理区域
	 */
	public void setAgencyArea(String agencyArea) {
		this.agencyArea = agencyArea;
	}
	
	/**
	 * 相关资质图片
	 */
	public String getQualityImageShareUrl() {
		return this.qualityImageShareUrl;
	}

	/**
	 * 相关资质图片
	 */
	public void setQualityImageShareUrl(String qualityImageShareUrl) {
		this.qualityImageShareUrl = qualityImageShareUrl;
	}
	
	/**
	 * 状态
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 
	 */
	public Long getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * 
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	/**
	 * 
	 */
	public String getSended() {
		return this.sended;
	}

	/**
	 * 
	 */
	public void setSended(String sended) {
		this.sended = sended;
	}
	
	/**
	 * T 是 F 
	 */
	public String getUpdated() {
		return this.updated;
	}

	/**
	 * T 是 F 
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	/**
	 * 审批意见
	 */
	public String getApprovalOpinion() {
		return this.approvalOpinion;
	}

	/**
	 * 审批意见
	 */
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
}
