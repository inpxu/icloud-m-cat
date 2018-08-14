package com.zhiyun.dto;

import com.zhiyun.entity.McatRunDev;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 12:58
 * @Description:
 */
public class McatRunDevDto extends McatRunDev {

    private List<Long> companyIds;

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
    }
}
