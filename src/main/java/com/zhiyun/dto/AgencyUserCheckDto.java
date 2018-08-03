package com.zhiyun.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class AgencyUserCheckDto {

    // ~~~~实体属性
    private Long id;
    // 用户id
    private Long userId;

    private String companyName;

    private Integer property;

    private String legalPerson;

    private String propertyDesc;

    private String inProvince;

    private String inCity;

    private Integer type;

    private String typeDesc;

    private Integer enterpriseScale;

    private String enterpriseScaleDesc;

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

    private String authDate;

    private Integer status;

    private String statusDesc;

    private String imageUrl;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public String getInProvince() {
        return inProvince;
    }

    public void setInProvince(String inProvince) {
        this.inProvince = inProvince;
    }

    public String getInCity() {
        return inCity;
    }

    public void setInCity(String inCity) {
        this.inCity = inCity;
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

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
