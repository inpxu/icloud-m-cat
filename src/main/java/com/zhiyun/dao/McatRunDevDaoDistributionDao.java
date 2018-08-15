package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.McatRundevCompanyDto;
import com.zhiyun.dto.McatRundevStateDto;
import com.zhiyun.entity.McatRunDevDistribution;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 20:33
 * @Description:
 */
public interface McatRunDevDaoDistributionDao extends BaseDao<McatRunDevDistribution, Long> {

    List<McatRundevCompanyDto> listMcatRundevCompanyDto(Params params);

    List<McatRunDevDistribution> listMcatRunDevDistribution(Params params);

    List<McatRundevStateDto> listMcatRundevState(Params params);
}
