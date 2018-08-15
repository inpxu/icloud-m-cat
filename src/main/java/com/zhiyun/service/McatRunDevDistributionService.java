package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.McatRunDevDto;
import com.zhiyun.dto.McatRundevCompanyDto;
import com.zhiyun.dto.McatRundevStateDto;
import com.zhiyun.entity.McatRunDev;
import com.zhiyun.entity.McatRunDevDistribution;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 16:57
 * @Description:
 */
public interface McatRunDevDistributionService extends BaseService<McatRunDevDistribution, Long> {

    List<McatRundevCompanyDto> listEnterpriseCompany();

    List<McatRundevCompanyDto> listAgencyCompany();

    List<McatRunDevDistribution> listEnterpriseMcatRunDevDistribution();

    List<McatRunDevDistribution> listAgencyMcatRunDevDistribution();

    List<McatRundevStateDto> listEnterpriseMcatRundevStateDto(McatRunDevDto mcatRunDevDto);

    List<McatRundevStateDto> listAgencyMcatRundevStateDto(McatRunDevDto mcatRunDevDto);

    List<McatRunDev> listEnterpriseMcatRunDev(McatRunDevDto mcatRunDevDto);

    List<McatRunDev> listAgencyMcatRunDev(McatRunDevDto mcatRunDevDto);

}
