/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ApplicationEntryDto
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月11日 上午9:49:28 
 */

public class ApplicationEntryDto {

	private Long id;

	private Long createUserId;

	private String name;

	private String legalPerson;

	private Integer property;

	private String propertyDesc;

	private Integer type;

	private String typeDesc;

	private Integer enterpriseScale;

	private String enterpriseScaleDesc;

	private Integer equipmentScale;

	private String equipmentScaleDesc;

	private Integer annualSales;

	private String annualSalesDesc;

	private String siteImageShareUrl;

	private Integer status;

	private String statusDesc;
	// T 是 F
	private String updated;
	// 审批意见
	private String approvalOpinion;
	// 是否显示重新认证界面
	private boolean jurisdiction;

	@NotEmpty(message="整经机数量不能为空")
	@Pattern(regexp="[\\s\\S]{0,10}", message="整经机数量过多")
	private String zhengjingjiNum;
	//
	@NotEmpty(message="经编机数量不能为空")
	@Pattern(regexp="[\\s\\S]{0,10}", message="经编机数量过多")
	private String jingbianjiNum;
	//
	@NotEmpty(message="其他设备数量不能为空")
	@Pattern(regexp="[\\s\\S]{0,10}", message="其他设备数量设置多大")
	private String qitaNum;

	public String getZhengjingjiNum() {
		return zhengjingjiNum;
	}

	public void setZhengjingjiNum(String zhengjingjiNum) {
		this.zhengjingjiNum = zhengjingjiNum;
	}

	public String getJingbianjiNum() {
		return jingbianjiNum;
	}

	public void setJingbianjiNum(String jingbianjiNum) {
		this.jingbianjiNum = jingbianjiNum;
	}

	public String getQitaNum() {
		return qitaNum;
	}

	public void setQitaNum(String qitaNum) {
		this.qitaNum = qitaNum;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public String getPropertyDesc() {
		return propertyDesc;
	}

	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(Integer enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	public String getEnterpriseScaleDesc() {
		return enterpriseScaleDesc;
	}

	public void setEnterpriseScaleDesc(String enterpriseScaleDesc) {
		this.enterpriseScaleDesc = enterpriseScaleDesc;
	}

	public Integer getEquipmentScale() {
		return equipmentScale;
	}

	public void setEquipmentScale(Integer equipmentScale) {
		this.equipmentScale = equipmentScale;
	}

	public String getEquipmentScaleDesc() {
		return equipmentScaleDesc;
	}

	public void setEquipmentScaleDesc(String equipmentScaleDesc) {
		this.equipmentScaleDesc = equipmentScaleDesc;
	}

	public Integer getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(Integer annualSales) {
		this.annualSales = annualSales;
	}

	public String getAnnualSalesDesc() {
		return annualSalesDesc;
	}

	public void setAnnualSalesDesc(String annualSalesDesc) {
		this.annualSalesDesc = annualSalesDesc;
	}

	public String getSiteImageShareUrl() {
		return siteImageShareUrl;
	}

	public void setSiteImageShareUrl(String siteImageShareUrl) {
		this.siteImageShareUrl = siteImageShareUrl;
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

}
