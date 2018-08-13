/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dto.PersonalAuthDto;
import com.zhiyun.entity.IcloudPersonalauth;
import com.zhiyun.service.IcloudEnterpriseauthService;
import com.zhiyun.service.IcloudPersonalauthService;
import com.zhiyun.service.IcloudUserEnterpriseauthService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

/**
 * 个人认证controller
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-06-07 11:10
 */
@Controller
@RequestMapping(value = "/personalAuth", produces = "application/json;charset=UTF-8")
@ResponseBody
public class IcloudPersonalauthController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IcloudPersonalauthController.class);

	@Resource
	private IcloudPersonalauthService icloudPersonalauthService;


	@Resource
	private IcloudEnterpriseauthService icloudEnterpriseauthService;

	/**
	 * 用户提交个人认证方法
	 *
	 * @param icloudPersonalauth 个人认证实体
	 * @param bindingResult 参数校验
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/7 13:06
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object add(@Valid @RequestBody IcloudPersonalauth icloudPersonalauth, BindingResult bindingResult) {
		BaseResult<String> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("个人认证提交成功");
		try {
			// 校验参数是否合法
			vaildParamsDefault(baseResult,bindingResult);
			if(!baseResult.getResult()){
				return baseResult;
			}

			icloudPersonalauthService.savePersonalauth(icloudPersonalauth);
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(@Valid @RequestBody IcloudPersonalauth icloudPersonalauth, BindingResult bindingResult) {
		BaseResult<IcloudPersonalauth> baseResult = new BaseResult<IcloudPersonalauth>();
		baseResult.setResult(true);
		baseResult.setMessage("更新成功");
		try {

			// 校验参数是否合法
			vaildParamsDefault(baseResult,bindingResult);
			if(!baseResult.getResult()){
				baseResult.setResult(false);
				return baseResult;
			}
            icloudPersonalauth.setUserId(UserHolder.getId());
			icloudPersonalauthService.updateIcloudPersonalauth(icloudPersonalauth);
			baseResult.setModel(icloudPersonalauth);
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;
	}

	/**
	 * 分页查询
	 *
	 * @param icloudPersonalauth 参数实体
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/7 13:37
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	public Object page(@RequestBody IcloudPersonalauth icloudPersonalauth) {
		BaseResult<PersonalAuthDto> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("查询成功");
		try {
			if (icloudPersonalauth.getUserId() == null) {
				baseResult.setResult(true);
				baseResult.setMessage("userid不能为空");
				return JSON.toJSONString(baseResult);
			}
			List<IcloudPersonalauth> icloudPersonalauths = icloudPersonalauthService.find(icloudPersonalauth);
			if (CollectionUtils.isNotEmpty(icloudPersonalauths)) {
				IcloudPersonalauth ipa = icloudPersonalauths.get(0);
				PersonalAuthDto personalAuthDto = new PersonalAuthDto();
				personalAuthDto.setCertificate(ipa.getCertificate());
				personalAuthDto.setCertificateNegativeShareUrl(ipa.getCertificateNegativeShareUrl());
				personalAuthDto.setCertificatePositiveShareUrl(ipa.getCertificatePositiveShareUrl());
				personalAuthDto.setCity(ipa.getCity());
				personalAuthDto.setDistrict(ipa.getDistrict());
				personalAuthDto.setProvince(ipa.getProvince());
				personalAuthDto.setId(ipa.getId());
				personalAuthDto.setSex(ipa.getSex());
				personalAuthDto.setSexDesc(ipa.getSex() == 1 ? "男" : "女");
				personalAuthDto.setCertificateType(ipa.getCertificateType());
				personalAuthDto.setCertificateTypeDesc(
						ipa.getCertificateType() == 0 ? "身份证" : (ipa.getCertificateType() == 1 ? "护照" : "港澳台通行证"));
				personalAuthDto.setName(ipa.getName());
				personalAuthDto.setUserId(ipa.getUserId());
				personalAuthDto.setDetailedAddress(ipa.getDetailedAddress());
				personalAuthDto.setApprovalOpinion(ipa.getApprovalOpinion());
				baseResult.setModel(personalAuthDto);
			}
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}

		return baseResult;
	}

	/**
	 * 删除
	 *
	 * @param ids id数组
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/7 13:42
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(Long[] ids) {
		BaseResult<String> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("删除成功");
		try {
			// 校验参数是否合法
			if (ArrayUtils.isNotEmpty(ids)) {
				for (Long id : ids) {
					icloudPersonalauthService.delete(id);
				}
			} else {
				baseResult.setResult(false);
				baseResult.setMessage("必须参入id");
			}
		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;
	}

}
