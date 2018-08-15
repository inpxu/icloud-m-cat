package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.McatRunDevDaoDistributionDao;
import com.zhiyun.dto.McatRundevCompanyDto;
import com.zhiyun.dto.McatRundevStateDto;
import com.zhiyun.entity.McatRunDevDistribution;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 20:45
 * @Description:
 */
@Repository("mcatRunDevDaoDistributionDao")
public class McatRunDevDaoDistributionDaoImpl extends BaseDaoImpl<McatRunDevDistribution, Long> implements McatRunDevDaoDistributionDao {


    @Override
    public List<McatRundevCompanyDto> listMcatRundevCompanyDto(Params params) {
        return this.selectList(getMethodName(),params);
    }

    @Override
    public List<McatRunDevDistribution> listMcatRunDevDistribution(Params params) {
        return this.selectList(getMethodName(),params);
    }

    @Override
    public List<McatRundevStateDto> listMcatRundevState(Params params){
        return this.selectList(getMethodName(),params);
    }
}
