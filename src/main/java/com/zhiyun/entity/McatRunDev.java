package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 10:38
 * @Description:
 */
public class McatRunDev  extends BaseEntity<Long> {

    private Long companyId;

    private String companyName;

    private Long modelId;

    private String modelName;

    private String manufactory;

    private String spec;

    private String commCode;

    private String longitude;

    private String latitude;

    private String transferMethod;

    private String onlineStatus;

    private String faultStatus;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getCommCode() {
        return commCode;
    }

    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTransferMethod() {
        return transferMethod;
    }

    public void setTransferMethod(String transferMethod) {
        this.transferMethod = transferMethod;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(String faultStatus) {
        this.faultStatus = faultStatus;
    }
}
