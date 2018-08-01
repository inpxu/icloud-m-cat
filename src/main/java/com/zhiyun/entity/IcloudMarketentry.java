/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhiyun.base.entity.BaseEntity;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcloudMarketentry extends BaseEntity<Long> {

	private static final long serialVersionUID = 3688457119453123384L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long createUserId;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer businessType;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer marketClass;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer market;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String enterpriseProfile;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer status;
	

	private String sended;

	// T 是 F
	@Pattern(regexp="[\\s\\S]{0,2}", message="T 是 F 字段过长")
	private String updated;
	// 审批意见
	@Pattern(regexp="[\\s\\S]{0,255}", message="审批意见字段过长")
	private String approvalOpinion;


	private List<IcloudMarketentrydatashareurl> marketEntryDataShareUrls = new ArrayList<IcloudMarketentrydatashareurl>();
	
	public List<IcloudMarketentrydatashareurl> getMarketEntryDataShareUrls() {
		return marketEntryDataShareUrls;
	}

	public void setMarketEntryDataShareUrls(List<IcloudMarketentrydatashareurl> marketEntryDataShareUrls) {
		this.marketEntryDataShareUrls = marketEntryDataShareUrls;
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

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	
	public String getSended() {
		return sended;
	}

	public void setSended(String sended) {
		this.sended = sended;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 
	 */
	public Integer getBusinessType() {
		return this.businessType;
	}

	/**
	 * 
	 */
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	
	/**
	 * 
	 */
	public Integer getMarketClass() {
		return this.marketClass;
	}

	/**
	 * 
	 */
	public void setMarketClass(Integer marketClass) {
		this.marketClass = marketClass;
	}
	
	/**
	 * 
	 */
	public Integer getMarket() {
		return this.market;
	}

	/**
	 * 
	 */
	public void setMarket(Integer market) {
		this.market = market;
	}
	
	/**
	 * 
	 */
	public String getEnterpriseProfile() {
		return this.enterpriseProfile;
	}

	/**
	 * 
	 */
	public void setEnterpriseProfile(String enterpriseProfile) {
		this.enterpriseProfile = enterpriseProfile;
	}
	
	/**
	 * 
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
