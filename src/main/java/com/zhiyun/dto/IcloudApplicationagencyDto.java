package com.zhiyun.dto;

import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.entity.IcloudApplicationagencyqualityimageshareurl;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/13 15:35
 * @Description:
 */
public class IcloudApplicationagencyDto extends IcloudApplicationagency {

    private String agencyTypeDesc;

    List<IcloudApplicationagencyqualityimageshareurl> qualityImageShareUrls;

    public String getAgencyTypeDesc() {
        return agencyTypeDesc;
    }

    public void setAgencyTypeDesc(String agencyTypeDesc) {
        this.agencyTypeDesc = agencyTypeDesc;
    }

    public List<IcloudApplicationagencyqualityimageshareurl> getQualityImageShareUrls() {
        return qualityImageShareUrls;
    }

    public void setQualityImageShareUrls(List<IcloudApplicationagencyqualityimageshareurl> qualityImageShareUrls) {
        this.qualityImageShareUrls = qualityImageShareUrls;
    }
}
