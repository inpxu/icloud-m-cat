/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class IcloudApplicationentry extends BaseEntity<Long> {

	private static final long serialVersionUID = 4386438149729520868L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long createUserId;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String name;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String legalPerson;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer property;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer type;
	// 
	@Max(value=99999999999L,message="字段过长")
	@NotNull(message="企业规模不能为空")
	private Integer enterpriseScale;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer equipmentScale;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer annualSales;
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
	//
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
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 */
	public String getLegalPerson() {
		return this.legalPerson;
	}

	/**
	 * 
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	/**
	 * 
	 */
	public Integer getProperty() {
		return this.property;
	}

	/**
	 * 
	 */
	public void setProperty(Integer property) {
		this.property = property;
	}
	
	/**
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 
	 */
	public Integer getEnterpriseScale() {
		return this.enterpriseScale;
	}

	/**
	 * 
	 */
	public void setEnterpriseScale(Integer enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
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
	public Integer getAnnualSales() {
		return this.annualSales;
	}

	/**
	 * 
	 */
	public void setAnnualSales(Integer annualSales) {
		this.annualSales = annualSales;
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
	
	/**
	 * 
	 */
	public String getZhengjingjiNum() {
		return this.zhengjingjiNum;
	}

	/**
	 * 
	 */
	public void setZhengjingjiNum(String zhengjingjiNum) {
		this.zhengjingjiNum = zhengjingjiNum;
	}
	
	/**
	 * 
	 */
	public String getJingbianjiNum() {
		return this.jingbianjiNum;
	}

	/**
	 * 
	 */
	public void setJingbianjiNum(String jingbianjiNum) {
		this.jingbianjiNum = jingbianjiNum;
	}
	
	/**
	 * 
	 */
	public String getQitaNum() {
		return this.qitaNum;
	}

	/**
	 * 
	 */
	public void setQitaNum(String qitaNum) {
		this.qitaNum = qitaNum;
	}
}
