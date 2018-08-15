package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 20:42
 * @Description:
 */
public class McatRunDevDistribution extends BaseEntity<Long> {

    private Long companyId;

    private String companyName;

    private String longitude;

    private String latitude;

    private Long count;

    private List<McatRunDevInfo> infos;

    @Override
    public Long getCompanyId() {
        return companyId;
    }

    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<McatRunDevInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<McatRunDevInfo> infos) {
        this.infos = infos;
    }
}
