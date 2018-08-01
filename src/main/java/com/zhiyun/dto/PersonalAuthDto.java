/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PersonalAuthDto
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月12日 下午5:23:54 
 */

public class PersonalAuthDto {

	private Long id;

	private Long userId;

	private String name;

	private Integer certificateType;

	private String certificateTypeDesc;

	private String certificate;

	private Integer sex;

	private String sexDesc;

	private String province;

	private String city;

	private String district;

	private String detailedAddress;

	private String certificatePositiveShareUrl;

	private String certificateNegativeShareUrl;

	private Integer status;

	// 是否更新过 T F
	private String updated;
	// 审批意见
	private String approvalOpinion;

	
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateTypeDesc() {
		return certificateTypeDesc;
	}

	public void setCertificateTypeDesc(String certificateTypeDesc) {
		this.certificateTypeDesc = certificateTypeDesc;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getCertificatePositiveShareUrl() {
		return certificatePositiveShareUrl;
	}

	public void setCertificatePositiveShareUrl(String certificatePositiveShareUrl) {
		this.certificatePositiveShareUrl = certificatePositiveShareUrl;
	}

	public String getCertificateNegativeShareUrl() {
		return certificateNegativeShareUrl;
	}

	public void setCertificateNegativeShareUrl(String certificateNegativeShareUrl) {
		this.certificateNegativeShareUrl = certificateNegativeShareUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
