/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

/**
 * ApplicationEntryDto
 *
 * @author 祝天洋
 * @version v1.0
 * @date 2018年7月11日 上午9:49:28
 */

public class ApplicationAgencyDto {

	// ~~~~实体属性
	private Long id;
	// 用户id
	private Long userId;
	// 用户姓名
	private String name;
	// 代理行业
	private String agencyType;
	// 代理级别
	private String agencyLevel;
	// 代理区域
	private String agencyArea;
	// 相关资质图片
	private String qualityImageShareUrl;
	// 代理行业名称
	private String agencyTypeDesc;
	// 代理区域名称
	private String agencyAreaDesc;

	private Integer status;

	private String statusDesc;
	// T 是 F
	private String updated;
	// 审批意见
	private String approvalOpinion;
	// 是否显示重新认证界面
	private boolean jurisdiction;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id=id;
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
	 * 代理行业名称
	 */
	public String getAgencyTypeDesc() {
		return this.agencyTypeDesc;
	}

	/**
	 * 代理行业名称
	 */
	public void setAgencyTypeDesc(String agencyTypeDesc) {
		this.agencyTypeDesc = agencyTypeDesc;
	}
	/**
	 * 代理区域名称
	 */
	public String getAgencyAreaDesc() {
		return this.agencyAreaDesc;
	}

	/**
	 * 代理区域名称
	 */
	public void setAgencyAreaDesc(String agencyAreaDesc) {
		this.agencyAreaDesc = agencyAreaDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public boolean isJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(boolean jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

}
