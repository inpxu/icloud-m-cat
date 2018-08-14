/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudUserOnicloudDao;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.entity.IcloudUserOnicloud;
import com.zhiyun.liferay.constants.LiferayErrorCodeConstant;
import com.zhiyun.liferay.impl.OrganizationInvoker;
import com.zhiyun.liferay.impl.UserInvoker;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.IcloudOnicloudDao;
import com.zhiyun.entity.IcloudOnicloud;
import com.zhiyun.service.IcloudOnicloudService;

import java.util.List;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("icloudOnicloudService")
public class IcloudOnicloudServiceImpl extends BaseServiceImpl<IcloudOnicloud, Long> implements IcloudOnicloudService {

	@Resource
	private IcloudOnicloudDao icloudOnicloudDao;

	@Resource
    private IcloudUserOnicloudDao icloudUserOnicloudDao;

	@Resource
    private OrganizationInvoker organizationInvoker;

	@Resource
    private UserInvoker userInvoker;

	@Override
	protected BaseDao<IcloudOnicloud, Long> getBaseDao() {
		return this.icloudOnicloudDao;
	}

    @Override
    public void addOnIncloud(IcloudOnicloud icloudOnicloud) {

        IcloudUserOnicloud icloudUserOnicloud = getIcloudUserOnicloudByUserId(UserHolder.getId());

        if(icloudUserOnicloud != null){
            throw new BusinessException("不能重复上云!");
        }

        LiferayInvokerResult result = organizationInvoker.addOrganization(icloudOnicloud.getName());
        if (result.getError() != null) {
            if (result.getError() != null && ((JSONObject) result.getError())
                    .getLong("code") == LiferayErrorCodeConstant.DUPLICATE_ORGANIZATION_ERROR) {
                throw new BusinessException("公司名已存在！");
            }
            throw new BusinessException("入驻申请提交失败请重试！");
        }

        JSONObject org = (JSONObject) result.getResult();
        userInvoker.updateOrganizations(UserHolder.getId(), new long[] { org.getLong("organizationId") });

        icloudOnicloud.setOrganizationId(org.getLong("organizationId"));
        icloudOnicloud.setSended("F");
        icloudOnicloud.setUpdated("F");
        icloudOnicloud.setStatus(AuditState.AUDITING);
        icloudOnicloud.setCreateUserId(UserHolder.getId());

        icloudOnicloudDao.insert(icloudOnicloud);

        icloudUserOnicloud = new IcloudUserOnicloud();
        icloudUserOnicloud.setOnicloudId(icloudOnicloud.getId());
        icloudUserOnicloud.setUserId(UserHolder.getId());
        icloudUserOnicloudDao.insert(icloudUserOnicloud);

    }

    @Override
    public IcloudOnicloud getOnIcloudByUserId(Long userId) {

        IcloudUserOnicloud icloudUserOnicloud =getIcloudUserOnicloudByUserId(userId);
        if(icloudUserOnicloud == null){
            return null;
        }
        IcloudOnicloud icloudOnicloud = get(icloudUserOnicloud.getOnicloudId());

        return icloudOnicloud;
    }

    @Override
    public void updateOnIncloud(IcloudOnicloud icloudOnicloud) {

        IcloudOnicloud dbIcloudOnIcloud = get(icloudOnicloud.getId());

        if(dbIcloudOnIcloud != null && !dbIcloudOnIcloud.getCreateUserId().equals(UserHolder.getId())){
            throw new BusinessException("非企业管理者无法修改");
        }
        if(dbIcloudOnIcloud !=null && dbIcloudOnIcloud.getStatus().equals(AuditState.AUDITING)){
            throw new BusinessException("申请正在审核中，不能进行修改");
        }

        LiferayInvokerResult result = organizationInvoker.getUserOrganization(UserHolder.getId());
        if (result.getResult() == null || ((JSONArray) result.getResult()).size() <= 0) {
            throw new BusinessException("系统异常请重试！");
        }

        long orgId = ((JSONArray) result.getResult()).getJSONObject(0).getLong("organizationId");
        result = organizationInvoker.updateOrganization(orgId, icloudOnicloud.getName());
        icloudOnicloud.setOrganizationId(orgId);
        if (result.getResult() == null) {
            if (result.getError() != null && ((JSONObject) result.getError())
                    .getLong("code") == LiferayErrorCodeConstant.DUPLICATE_ORGANIZATION_ERROR) {
                throw new BusinessException("公司名已存在！");
            }
            throw new BusinessException("重新入驻失败请重试");
        }

        icloudOnicloud.setCreateUserId(UserHolder.getId());

        if(dbIcloudOnIcloud.getStatus().equals(AuditState.AUDITED)){

            Long fromIcloudOnicloudId = icloudOnicloud.getId();

            icloudOnicloud.setStatus(AuditState.AUDITING);
            icloudOnicloud.setSended("F");
            icloudOnicloud.setUpdated("F");
            icloudOnicloud.setId(null);

            icloudOnicloudDao.insert(icloudOnicloud);
            icloudOnicloudDao.delete(fromIcloudOnicloudId);
            Long toApplicationEntryId = icloudOnicloud.getId();
            IcloudUserOnicloud icloudUserOnicloud = getIcloudUserOnicloudByUserId(UserHolder.getId());
            icloudUserOnicloud.setOnicloudId(icloudOnicloud.getId());
            icloudUserOnicloudDao.update(icloudUserOnicloud);


        }else if(dbIcloudOnIcloud.getStatus().equals(AuditState.UNAUDITED)){

            icloudOnicloud.setStatus(AuditState.AUDITING);
            icloudOnicloud.setSended("F");
            icloudOnicloud.setUpdated("T");
            icloudOnicloud.setApprovalOpinion(null);
            icloudOnicloudDao.update(icloudOnicloud);

        }

    }

    private IcloudUserOnicloud getIcloudUserOnicloudByUserId(Long userId){

        IcloudUserOnicloud icloudUserOnicloud = new IcloudUserOnicloud();
        icloudUserOnicloud.setUserId(userId);
        List<IcloudUserOnicloud> icloudUserOniclouds= icloudUserOnicloudDao.find(icloudUserOnicloud);

        if(CollectionUtils.isNotEmpty(icloudUserOniclouds)){
            return icloudUserOniclouds.get(0);
        }

        return null;
    }

    @Override
    public DataGrid<IcloudOnicloudDto> myPage(Params params, Pager pager) {
        return icloudOnicloudDao.myPage(params,pager);
    }

	@Override
	public List<IcloudOnicloud> findBySended() {
		return icloudOnicloudDao.findBySended();
	}

	@Override
	public int updateSended(Long id) {
		return icloudOnicloudDao.updateSended(id);
	}
}
