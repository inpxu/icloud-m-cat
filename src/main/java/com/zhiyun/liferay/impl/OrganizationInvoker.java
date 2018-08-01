/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.liferay.impl;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.zhiyun.liferay.BaseLiferayInvoker;
import com.zhiyun.liferay.constants.CompanyConstant;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.model.LiferayInvokerResult;

/**
 * OrganizationInvoker
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月9日 下午6:51:33 
 */
@Repository
public class OrganizationInvoker extends BaseLiferayInvoker {

	private static final String MODULE_PATH = "organization";

	private static final String GET_ORGANIZATION_METHOD = "get-organization";

	private static final String GET_ORGANIZATION_ID_METHOD = "get-organization-id";

	private static final String ADD_ORGANIZATION_METHOD = "add-organization";

	private static final String UPDATE_ORGANIZATION = "update-organization";

	private static final String GET_USER_ORGANIZATIONS = "get-user-organizations";

	private static final String DELETE_ORGANIZATION = "delete-organization";

	public LiferayInvokerResult deleteOrganization(Long organizationId) {

		JSONObject params = new JSONObject();
		params.put("organizationId", organizationId);

		LiferayInvokerResult result = invoke(DELETE_ORGANIZATION, params);

		return result;

	}


	public LiferayInvokerResult getUserOrganization(Long userId) {

		JSONObject params = new JSONObject();
		params.put("userId", userId);

		LiferayInvokerResult result = invoke(GET_USER_ORGANIZATIONS, params);

		return result;

	}

	public LiferayInvokerResult addOrganization(String orgName) {

		JSONObject params = new JSONObject();
		params.put("parentOrganizationId", CompanyConstant.PARENTORGANIZATIONID);
		params.put("name", orgName);
		params.put("type", CompanyConstant.ORGTYPE);
		params.put("regionId", CompanyConstant.REGIONID);
		params.put("countryId", CompanyConstant.COUNTRYID);
		params.put("statusId", CompanyConstant.STATUSID);
		params.put("comments", CompanyConstant.COMMENTS);
		params.put("site", Boolean.FALSE);

		LiferayInvokerResult result = invoke(ADD_ORGANIZATION_METHOD, params);

		return result;

	}

	public LiferayInvokerResult updateOrganization(Long orgId, String orgName) {

		JSONObject params = new JSONObject();
		params.put("organizationId", orgId);
		params.put("parentOrganizationId", 0);
		params.put("name", orgName);
		params.put("type", CompanyConstant.ORGTYPE);
		params.put("regionId", CompanyConstant.REGIONID);
		params.put("countryId", CompanyConstant.COUNTRYID);
		params.put("statusId", CompanyConstant.STATUSID);
		params.put("comments", CompanyConstant.COMMENTS);
		params.put("site", Boolean.FALSE);

		LiferayInvokerResult result = invoke(UPDATE_ORGANIZATION, params);

		return result;
	}

	public LiferayInvokerResult getOrganizationId(String orgName) {
		JSONObject params = new JSONObject();
		params.put("companyId", UserConstant.COMPANYID);
		params.put("name", orgName);

		LiferayInvokerResult result = invoke(GET_ORGANIZATION_ID_METHOD, params);
		return result;
	}

	@Override
	public String getSubPath() {
		return MODULE_PATH;
	}

}
