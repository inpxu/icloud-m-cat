package com.zhiyun.dto;

import javax.validation.constraints.Pattern;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/11 11:23
 * @Description:
 */
public class LegalPersonDto {

    private Long userId;

    private String name;

    private String identityCard;

    @Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
    private String certificatePositiveShareUrl;
    //
    @Pattern(regexp="[\\s\\S]{0,500}", message="字段过长")
    private String certificateNegativeShareUrl;

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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
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
}
