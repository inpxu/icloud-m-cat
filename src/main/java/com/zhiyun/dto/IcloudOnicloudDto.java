package com.zhiyun.dto;

import com.zhiyun.entity.IcloudOnicloud;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/12 15:19
 * @Description:
 */
public class IcloudOnicloudDto extends IcloudOnicloud {

    private String propertyDesc;

    private String tradeDesc;

    private String employeeScaleDesc;

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }

    public String getEmployeeScaleDesc() {
        return employeeScaleDesc;
    }

    public void setEmployeeScaleDesc(String employeeScaleDesc) {
        this.employeeScaleDesc = employeeScaleDesc;
    }
}
