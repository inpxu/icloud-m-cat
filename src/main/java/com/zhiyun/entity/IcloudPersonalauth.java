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
 * 个人认证
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class IcloudPersonalauth extends BaseEntity<Long> {

	private static final long serialVersionUID = 2746155676987357492L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long userId;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="姓名长度过长")
	private String name;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer certificateType;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String certificate;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer sex;
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
	@Pattern(regexp="[\\s\\S]{0,75}", message="详细地址长度过长")
	private String detailedAddress;
	// 
	@Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
	private String certificatePositiveShareUrl;
	// 
	@Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
	private String certificateNegativeShareUrl;
	// 0 认证中 1 认证通过 2 认证未通过
	@Max(value=99999999999L,message="0 认证中 1 认证通过 2 认证未通过字段过长")
	private Integer status;
	// F
	@Pattern(regexp="[\\s\\S]{0,255}", message="F字段过长")
	private String sended;
	// 是否更新过 T F
	@Pattern(regexp="[\\s\\S]{0,1}", message="是否更新过 T F字段过长")
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
	public Integer getCertificateType() {
		return this.certificateType;
	}

	/**
	 * 
	 */
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	
	/**
	 * 
	 */
	public String getCertificate() {
		return this.certificate;
	}

	/**
	 * 
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	/**
	 * 
	 */
	public Integer getSex() {
		return this.sex;
	}

	/**
	 * 
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
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
	public String getCertificatePositiveShareUrl() {
		return this.certificatePositiveShareUrl;
	}

	/**
	 * 
	 */
	public void setCertificatePositiveShareUrl(String certificatePositiveShareUrl) {
		this.certificatePositiveShareUrl = certificatePositiveShareUrl;
	}
	
	/**
	 * 
	 */
	public String getCertificateNegativeShareUrl() {
		return this.certificateNegativeShareUrl;
	}

	/**
	 * 
	 */
	public void setCertificateNegativeShareUrl(String certificateNegativeShareUrl) {
		this.certificateNegativeShareUrl = certificateNegativeShareUrl;
	}
	
	/**
	 * 0 认证中 1 认证通过 2 认证未通过
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 0 认证中 1 认证通过 2 认证未通过
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * F
	 */
	public String getSended() {
		return this.sended;
	}

	/**
	 * F
	 */
	public void setSended(String sended) {
		this.sended = sended;
	}
	
	/**
	 * 是否更新过 T F
	 */
	public String getUpdated() {
		return this.updated;
	}

	/**
	 * 是否更新过 T F
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
