/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import com.zhiyun.entity.IcloudMarketentrydatashareurl;

/**
 * MarketEntryDto
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月11日 下午8:09:15 
 */

public class MarketEntryDto {

	private Long id;

	private Long userId;

	private Integer businessType;

	private String businessTypeDesc;

	private Integer marketClass;

	private String marketClassDesc;

	private Integer market;

	private String marketDesc;

	private String enterpriseProfile;

	private Integer status;

	private List<IcloudMarketentrydatashareurl> marketEntryDataShareUrls = new ArrayList<IcloudMarketentrydatashareurl>();
	// 是否显示重新认证界面
	private boolean jurisdiction;
	// T 是 F
	private String updated;
	// 审批意见
	private String approvalOpinion;
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<IcloudMarketentrydatashareurl> getMarketEntryDataShareUrls() {
		return marketEntryDataShareUrls;
	}

	public void setMarketEntryDataShareUrls(List<IcloudMarketentrydatashareurl> marketEntryDataShareUrls) {
		this.marketEntryDataShareUrls = marketEntryDataShareUrls;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer getMarketClass() {
		return marketClass;
	}

	public void setMarketClass(Integer marketClass) {
		this.marketClass = marketClass;
	}

	public Integer getMarket() {
		return market;
	}

	public void setMarket(Integer market) {
		this.market = market;
	}

	public String getEnterpriseProfile() {
		return enterpriseProfile;
	}

	public void setEnterpriseProfile(String enterpriseProfile) {
		this.enterpriseProfile = enterpriseProfile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBusinessTypeDesc() {
		return businessTypeDesc;
	}

	public void setBusinessTypeDesc(String businessTypeDesc) {
		this.businessTypeDesc = businessTypeDesc;
	}

	public String getMarketClassDesc() {
		return marketClassDesc;
	}

	public void setMarketClassDesc(String marketClassDesc) {
		this.marketClassDesc = marketClassDesc;
	}

	public String getMarketDesc() {
		return marketDesc;
	}

	public void setMarketDesc(String marketDesc) {
		this.marketDesc = marketDesc;
	}

}
