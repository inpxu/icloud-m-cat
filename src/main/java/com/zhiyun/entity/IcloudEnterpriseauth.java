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
public class IcloudEnterpriseauth extends BaseEntity<Long> {

	private static final long serialVersionUID = 3359111847201383741L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long userId;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String name;
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
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String legalPerson;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String legalPersonPhone;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String legalPersonIdentityCard;
	// 
	@Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
	private String certificatePositiveShareUrl;
	// 
	@Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
	private String certificateNegativeShareUrl;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String socialCreditCode;
	// 
	@Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
	private String businessLicenceShareUrl;
	// 0 认证中 1 认证通过 2 认证未通过
	@Max(value=99999999999L,message="0 认证中 1 认证通过 2 认证未通过字段过长")
	private Integer status;
	// 
	@Pattern(regexp="[\\s\\S]{0,255}", message="字段过长")
	private String sended;
	// T 是 F 
	@Pattern(regexp="[\\s\\S]{0,1}", message="T 是 F 字段过长")
	private String updated;
	// 审批意见
	@Pattern(regexp="[\\s\\S]{0,255}", message="审批意见字段过长")
	private String approvalOpinion;

    @Max(value=99999999999L,message="行业字段过长")
    private Integer trade;

    @Max(value=99999999999L,message="字段过长")
    private Integer legalPersonIdentityType;

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
	public String getLegalPersonPhone() {
		return this.legalPersonPhone;
	}

	/**
	 * 
	 */
	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
	}
	
	/**
	 * 
	 */
	public String getLegalPersonIdentityCard() {
		return this.legalPersonIdentityCard;
	}

	/**
	 * 
	 */
	public void setLegalPersonIdentityCard(String legalPersonIdentityCard) {
		this.legalPersonIdentityCard = legalPersonIdentityCard;
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
	 * 
	 */
	public String getSocialCreditCode() {
		return this.socialCreditCode;
	}

	/**
	 * 
	 */
	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	
	/**
	 * 
	 */
	public String getBusinessLicenceShareUrl() {
		return this.businessLicenceShareUrl;
	}

	/**
	 * 
	 */
	public void setBusinessLicenceShareUrl(String businessLicenceShareUrl) {
		this.businessLicenceShareUrl = businessLicenceShareUrl;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTrade() {
        return trade;
    }

    public void setTrade(Integer trade) {
        this.trade = trade;
    }

    /**
     *
     */
    public Integer getLegalPersonIdentityType() {
        return this.legalPersonIdentityType;
    }

    /**
     *
     */
    public void setLegalPersonIdentityType(Integer legalPersonIdentityType) {
        this.legalPersonIdentityType = legalPersonIdentityType;
    }
}
