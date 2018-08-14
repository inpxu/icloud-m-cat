/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dao.IcloudApplicationagencyqualityimageshareurlDao;
import com.zhiyun.dto.IcloudApplicationagencyDto;
import com.zhiyun.entity.IcloudApplicationagencyqualityimageshareurl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudApplicationagencyDao;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.service.IcloudApplicationagencyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudApplicationagencyService")
public class IcloudApplicationagencyServiceImpl extends BaseServiceImpl<IcloudApplicationagency, Long> implements IcloudApplicationagencyService {

	@Resource
	private IcloudApplicationagencyDao icloudApplicationagencyDao;

	@Resource
    private IcloudApplicationagencyqualityimageshareurlDao icloudApplicationagencyqualityimageshareurlDao;

	@Override
	protected BaseDao<IcloudApplicationagency, Long> getBaseDao() {
		return this.icloudApplicationagencyDao;
	}

	@Transactional
    @Override
    public void save(IcloudApplicationagencyDto icloudApplicationagencyDto) {

	    if(!CollectionUtils.isNotEmpty(icloudApplicationagencyDto.getQualityImageShareUrls())){
	        throw new BusinessException("请上传相关资质");
        }

        icloudApplicationagencyDto.setOrganizationId(UserHolder.getCompanyId());
        icloudApplicationagencyDto.setSended("F");
        icloudApplicationagencyDto.setUpdated("F");
        icloudApplicationagencyDto.setStatus(AuditState.AUDITING);
	    icloudApplicationagencyDao.insert(icloudApplicationagencyDto);

        icloudApplicationagencyDto.getQualityImageShareUrls().forEach(img ->{
            img.setApplicationAgencyId(icloudApplicationagencyDto.getId());
        });

        icloudApplicationagencyqualityimageshareurlDao.insert(icloudApplicationagencyDto.getQualityImageShareUrls());

    }

    @Override
    public IcloudApplicationagencyDto updateApplicationAgency(IcloudApplicationagencyDto icloudApplicationagencyDto) {


        if(!CollectionUtils.isNotEmpty(icloudApplicationagencyDto.getQualityImageShareUrls())){
            throw new BusinessException("请上传相关资质");
        }

        IcloudApplicationagency dbIcloudApplicationagency = icloudApplicationagencyDao.get(icloudApplicationagencyDto.getId());
        if(AuditState.AUDITING == dbIcloudApplicationagency.getStatus()){
            throw new BusinessException("代理申请正在审核");
        }

        icloudApplicationagencyDto.setOrganizationId(UserHolder.getCompanyId());
        icloudApplicationagencyDto.setStatus(AuditState.AUDITING);
        if(dbIcloudApplicationagency.getStatus() == AuditState.UNAUDITED){
            icloudApplicationagencyDto.setSended("F");
            icloudApplicationagencyDto.setUpdated("T");
            icloudApplicationagencyDao.update(icloudApplicationagencyDto);
        }else {
            icloudApplicationagencyDto.setSended("F");
            icloudApplicationagencyDto.setUpdated("F");
            icloudApplicationagencyDao.delete(icloudApplicationagencyDto.getId());
            icloudApplicationagencyDto.setId(null);
            icloudApplicationagencyDao.insert(icloudApplicationagencyDto);
        }

        List<Long> qualityImageShareUrlIds = new ArrayList<>();
        icloudApplicationagencyDto.getQualityImageShareUrls().forEach(img ->{
            img.setApplicationAgencyId(icloudApplicationagencyDto.getId());
            qualityImageShareUrlIds.add(img.getId());
            img.setId(null);
        });

        icloudApplicationagencyqualityimageshareurlDao.delete(qualityImageShareUrlIds);
        icloudApplicationagencyqualityimageshareurlDao.insert(icloudApplicationagencyDto.getQualityImageShareUrls());

	    return icloudApplicationagencyDto;
    }

    @Override
    public List<IcloudApplicationagencyDto> listApplicationAgencyDto() {

        IcloudApplicationagency param = new IcloudApplicationagency();
        param.setUserId(UserHolder.getId());
        List<IcloudApplicationagency> icloudApplicationagencies = icloudApplicationagencyDao.find(param);

        List<IcloudApplicationagencyDto> icloudApplicationagencyDtos = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(icloudApplicationagencies)){
            List<Long> agencyIds = new ArrayList<>();
            icloudApplicationagencies.forEach(agency ->{
                IcloudApplicationagencyDto icloudApplicationagencyDto = new IcloudApplicationagencyDto();
                BeanUtils.copyProperties(agency,icloudApplicationagencyDto);
                icloudApplicationagencyDto.setQualityImageShareUrls(new ArrayList<>());
                agencyIds.add(agency.getId());
                icloudApplicationagencyDtos.add(icloudApplicationagencyDto);
            });
            List<IcloudApplicationagencyqualityimageshareurl> qualityImageShareUrls= icloudApplicationagencyqualityimageshareurlDao.findByApplicationAgencyIds(agencyIds);

            for(IcloudApplicationagencyDto icloudApplicationagencyDto : icloudApplicationagencyDtos){
                for(IcloudApplicationagencyqualityimageshareurl image:qualityImageShareUrls){
                    if(icloudApplicationagencyDto.getId().equals(image.getApplicationAgencyId())){
                        icloudApplicationagencyDto.getQualityImageShareUrls().add(image);
                    }
                }
            }

            icloudApplicationagencyDtos.forEach( a ->{
                a.setAgencyTypeDesc(EnterpriseConstant.Industry.getIndustryDesc(a.getAgencyType()));
            });
        }

        return icloudApplicationagencyDtos;
    }


    @Override
    public JSONArray getAgencyArea(Long exceptId, String agencyType) {

        List<IcloudApplicationagency> icloudApplicationagencies = icloudApplicationagencyDao.listByExceptIdAndAgencyType(exceptId,agencyType);

        JSONArray provinces = new JSONArray();
        EnterpriseConstant.Province[] values = EnterpriseConstant.Province.values();
        for (EnterpriseConstant.Province province : values) {
            JSONObject pObj = new JSONObject();
            pObj.put("id", province.getId());
            pObj.put("name", province.getName());
            pObj.put("status", "1");
            for (IcloudApplicationagency icloudApplicationagency:icloudApplicationagencies) {
                if(icloudApplicationagency.getAgencyArea().equals(province.getName())){
                    pObj.put("status","0");
                }
            }
            provinces.add(pObj);
        }

        return provinces;
    }

	@Override
	public List<IcloudApplicationagency> findBySended() {
		return icloudApplicationagencyDao.findBySended();
	}

	@Override
	public int updateSended(Long id) {
		return icloudApplicationagencyDao.updateSended(id);
	}
}

