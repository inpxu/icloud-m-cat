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
public class IcloudOnicloud extends BaseEntity<Long> {

	private static final long serialVersionUID = 1887841155244298640L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long createUserId;
	// 企业名称
	@Pattern(regexp="[\\s\\S]{0,75}", message="企业名称字段过长")
	private String name;
	// 企业性质
	@Max(value=99999999999L,message="企业性质字段过长")
	private Integer property;
	// 行业
	@Max(value=99999999999L,message="行业字段过长")
	private Integer trade;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String province;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String city;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String district;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String detailedAddress;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String contactPerson;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String contactPhone;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer employeeScale;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer equipmentScale;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer onIcloudScale;
	// 
	@Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
	private String siteImageShareUrl;
	// 
	@Max(value=99999999999L,message="字段过长")
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
	 * 
	 */
	public Long getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	/**
	 * 企业名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 企业名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 企业性质
	 */
	public Integer getProperty() {
		return this.property;
	}

	/**
	 * 企业性质
	 */
	public void setProperty(Integer property) {
		this.property = property;
	}
	
	/**
	 * 行业
	 */
	public Integer getTrade() {
		return this.trade;
	}

	/**
	 * 行业
	 */
	public void setTrade(Integer trade) {
		this.trade = trade;
	}
	
	/**
	 * 
	 */
	public String getProvince() {
		return this.province;
	}

	/**
	 * 
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 
	 */
	public String getDistrict() {
		return this.district;
	}

	/**
	 * 
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	
	/**
	 * 
	 */
	public String getDetailedAddress() {
		return this.detailedAddress;
	}

	/**
	 * 
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	
	/**
	 * 
	 */
	public String getContactPerson() {
		return this.contactPerson;
	}

	/**
	 * 
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	/**
	 * 
	 */
	public String getContactPhone() {
		return this.contactPhone;
	}

	/**
	 * 
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	/**
	 * 
	 */
	public Integer getEmployeeScale() {
		return this.employeeScale;
	}

	/**
	 * 
	 */
	public void setEmployeeScale(Integer employeeScale) {
		this.employeeScale = employeeScale;
	}
	
	/**
	 * 
	 */
	public Integer getEquipmentScale() {
		return this.equipmentScale;
	}

	/**
	 * 
	 */
	public void setEquipmentScale(Integer equipmentScale) {
		this.equipmentScale = equipmentScale;
	}
	
	/**
	 * 
	 */
	public Integer getOnIcloudScale() {
		return this.onIcloudScale;
	}

	/**
	 * 
	 */
	public void setOnIcloudScale(Integer onIcloudScale) {
		this.onIcloudScale = onIcloudScale;
	}
	
	/**
	 * 
	 */
	public String getSiteImageShareUrl() {
		return this.siteImageShareUrl;
	}

	/**
	 * 
	 */
	public void setSiteImageShareUrl(String siteImageShareUrl) {
		this.siteImageShareUrl = siteImageShareUrl;
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
