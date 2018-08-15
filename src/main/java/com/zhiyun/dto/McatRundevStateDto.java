package com.zhiyun.dto;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/15 10:08
 * @Description:
 */
public class McatRundevStateDto {

    private Long companyId;

    private String companyName;

    private Long onLineDevs;

    private Long offLineDevs;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getOnLineDevs() {
        return onLineDevs;
    }

    public void setOnLineDevs(Long onLineDevs) {
        this.onLineDevs = onLineDevs;
    }

    public Long getOffLineDevs() {
        return offLineDevs;
    }

    public void setOffLineDevs(Long offLineDevs) {
        this.offLineDevs = offLineDevs;
    }
}
