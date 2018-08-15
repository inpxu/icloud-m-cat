package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudOnicloudDao;
import com.zhiyun.dao.McatRunDevDao;
import com.zhiyun.dao.McatRunDevDaoDistributionDao;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.dto.McatRunDevDto;
import com.zhiyun.dto.McatRundevCompanyDto;
import com.zhiyun.dto.McatRundevStateDto;
import com.zhiyun.entity.IcloudOnicloud;
import com.zhiyun.entity.McatRunDev;
import com.zhiyun.entity.McatRunDevDistribution;
import com.zhiyun.service.McatRunDevDistributionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 20:48
 * @Description:
 */
@Service("mcatRunDevDistributionService")
public class McatRunDevDistributionServiceImpl extends BaseServiceImpl<McatRunDevDistribution, Long> implements McatRunDevDistributionService {

    @Resource
    private McatRunDevDaoDistributionDao mcatRunDevDaoDistributionDao;

    @Resource
    private McatRunDevDao mcatRunDevDao;

    @Resource
    private IcloudOnicloudDao icloudOnicloudDao;

    @Override
    protected BaseDao<McatRunDevDistribution, Long> getBaseDao() {
        return this.mcatRunDevDaoDistributionDao;
    }

    @Override
    public List<McatRundevCompanyDto> listEnterpriseCompany() {

        Params params = Params.create();

        List<Long> companyIds = listEnterpriseCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);

        return mcatRunDevDaoDistributionDao.listMcatRundevCompanyDto(params);
    }

    @Override
    public List<McatRundevCompanyDto> listAgencyCompany() {

        Params params = Params.create();

        List<Long> companyIds = listAgencyCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);

        return mcatRunDevDaoDistributionDao.listMcatRundevCompanyDto(params);
    }


    @Override
    public List<McatRunDevDistribution> listEnterpriseMcatRunDevDistribution() {
        Params params = Params.create();

        List<Long> companyIds = listEnterpriseCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);

        return mcatRunDevDaoDistributionDao.listMcatRunDevDistribution(params);
    }

    @Override
    public List<McatRunDevDistribution> listAgencyMcatRunDevDistribution() {
        Params params = Params.create();

        List<Long> companyIds = listAgencyCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);

        return mcatRunDevDaoDistributionDao.listMcatRunDevDistribution(params);
    }

    @Override
    public List<McatRundevStateDto> listEnterpriseMcatRundevStateDto(McatRunDevDto mcatRunDevDto) {

        Params params = Params.create();

        List<Long> companyIds = listEnterpriseCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);
        params.add("companyName", Optional.ofNullable(mcatRunDevDto).map(McatRunDevDto::getCompanyName).orElse(""));

        return mcatRunDevDaoDistributionDao.listMcatRundevState(params);
    }

    @Override
    public List<McatRundevStateDto> listAgencyMcatRundevStateDto(McatRunDevDto mcatRunDevDto) {

        Params params = Params.create();

        List<Long> companyIds = listAgencyCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);
        params.add("companyName", Optional.ofNullable(mcatRunDevDto).map(McatRunDevDto::getCompanyName).orElse(""));

        return mcatRunDevDaoDistributionDao.listMcatRundevState(params);
    }


    @Override
    public List<McatRunDev> listEnterpriseMcatRunDev(McatRunDevDto mcatRunDevDto) {

        Params params = Params.create();

        List<Long> companyIds = listEnterpriseCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);
        params.add("entity", mcatRunDevDto);

        return mcatRunDevDao.listByCompanyName(params);
    }

    @Override
    public List<McatRunDev> listAgencyMcatRunDev(McatRunDevDto mcatRunDevDto) {

        Params params = Params.create();

        List<Long> companyIds = listAgencyCompanyIds();
        if(CollectionUtils.isEmpty(companyIds)){
            return null;
        }

        params.add("companyIds",companyIds);
        params.add("entity", mcatRunDevDto);

        return mcatRunDevDao.listByCompanyName(params);
    }

    private List<Long> listEnterpriseCompanyIds(){

        IcloudOnicloud icloudOnicloud = new IcloudOnicloud();
        icloudOnicloud.setCreateUserId(UserHolder.getId());
        List<IcloudOnicloud> icloudOniclouds = icloudOnicloudDao.find(icloudOnicloud);

        if(!CollectionUtils.isNotEmpty(icloudOniclouds)){
            return null;
        }

        List<Long> companyIds = icloudOniclouds.stream().map(IcloudOnicloud::getOrganizationId).collect(Collectors.toList());

        return companyIds;
    }

    private List<Long> listAgencyCompanyIds(){


        Params params = Params.create();

        Params p = Params.create();
        p.add("status",AuditState.AUDITED);
        p.add("userId",UserHolder.getId());

        List<IcloudOnicloudDto> icloudOniclouds =
                icloudOnicloudDao.findByUserIdAndStatus(p);

        if(!CollectionUtils.isNotEmpty(icloudOniclouds)){
            return null;
        }

        List<Long> companyIds = icloudOniclouds.stream().map(IcloudOnicloud::getOrganizationId).collect(Collectors.toList());

        return companyIds;

    }
}
