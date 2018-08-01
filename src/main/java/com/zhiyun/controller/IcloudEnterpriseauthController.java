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
import com.zhiyun.entity.IcloudEnterpriseauth;
import com.zhiyun.service.IcloudEnterpriseauthService;
import com.zhiyun.service.IcloudPersonalauthService;
import com.zhiyun.service.IcloudUserMarketentryService;

import com.zhiyun.service.IcloudUserPersonalauthService;
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
 * 企业认证controller
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-06-08 09:34
 */
@Controller
@RequestMapping(value = "/enterpriseauth", produces = "application/json;charset=UTF-8")
@ResponseBody
public class IcloudEnterpriseauthController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IcloudEnterpriseauthController.class);
	@Resource
	private IcloudEnterpriseauthService icloudEnterpriseauthService;
	@Resource
	private IcloudUserMarketentryService icloudUserMarketentryService;
	@Resource
	private IcloudPersonalauthService icloudPersonalauthService;



	/**
	 * 添加企业认证
	 *
	 * @param icloudEnterpriseauth 企业认证实体
	 * @param bindingResult 参数校验
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/8 9:37
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object add(@Valid @RequestBody IcloudEnterpriseauth icloudEnterpriseauth, BindingResult bindingResult) {
		BaseResult<String> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("添加企业认证成功");
		try {
			StringBuilder stringBuilder = new StringBuilder();
			if (bindingResult.hasErrors()) {
				baseResult.setResult(false);
				baseResult.setMessage("提交企业认证参数不合法");
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();
				if (CollectionUtils.isNotEmpty(fieldErrors)) {
					for (FieldError fieldError : fieldErrors) {
						stringBuilder.append(fieldError.getDefaultMessage());
						stringBuilder.append(",");
					}
					baseResult.setMessage(stringBuilder.toString());
				}
				return JSON.toJSONString(baseResult);
			}
			icloudEnterpriseauth.setStatus(AuditState.AUDITING);
			icloudEnterpriseauth.setSended("F");
			icloudEnterpriseauth.setUpdated("F");

			icloudEnterpriseauthService.saveIcloudEnterpriseauth(icloudEnterpriseauth);

		} catch (BusinessException be) {
			LOGGER.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			LOGGER.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return JSON.toJSONString(baseResult);
	}

	/**
	 * 修改添加企业认证
	 *
	 * @param icloudEnterpriseauth 企业认证实体
	 * @param bindingResult 参数校验
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/8 9:37
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(@Valid @RequestBody IcloudEnterpriseauth icloudEnterpriseauth, BindingResult bindingResult) {
		BaseResult<IcloudEnterpriseauth> baseResult = new BaseResult<IcloudEnterpriseauth>();
		baseResult.setResult(true);
		baseResult.setMessage("更新企业认证成功");
		try {

			// 校验参数是否合法
			StringBuilder stringBuilder = new StringBuilder();
			if (bindingResult.hasErrors()) {
				baseResult.setResult(false);
				baseResult.setMessage("提交更新企业认证参数不合法");
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();
				if (CollectionUtils.isNotEmpty(fieldErrors)) {
					for (FieldError fieldError : fieldErrors) {
						stringBuilder.append(fieldError.getDefaultMessage());
						stringBuilder.append(",");
					}
					baseResult.setMessage(stringBuilder.toString());
				}
				return baseResult;
			}

			icloudEnterpriseauthService.updateEnterpriseauth(icloudEnterpriseauth);
			baseResult.setModel(icloudEnterpriseauth);
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
	 * 企业认证查询
	 *
	 * @param icloudEnterpriseauth 条件实体
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/8 9:46
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	public Object page(@Valid @RequestBody IcloudEnterpriseauth icloudEnterpriseauth, BindingResult bindingResult) {
		BaseResult<IcloudEnterpriseauth> baseResult = new BaseResult<>();
		baseResult.setResult(true);

		try {
			StringBuilder stringBuilder = new StringBuilder();
			if (bindingResult.hasErrors()) {
				baseResult.setResult(false);
				baseResult.setMessage("提交更新企业认证参数不合法");
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();
				if (CollectionUtils.isNotEmpty(fieldErrors)) {
					for (FieldError fieldError : fieldErrors) {
						stringBuilder.append(fieldError.getDefaultMessage());
						stringBuilder.append(",");
					}
					baseResult.setMessage(stringBuilder.toString());
				}
				return baseResult;
			}
			List<IcloudEnterpriseauth> icloudEnterpriseauths = icloudEnterpriseauthService.find(icloudEnterpriseauth);
			if (CollectionUtils.isNotEmpty(icloudEnterpriseauths)) {
				IcloudEnterpriseauth icloudEnterpriseauth1 = icloudEnterpriseauths.get(0);
				baseResult.setMessage("查询企业认证成功");
				baseResult.setModel(icloudEnterpriseauth1);
			} else {
				baseResult.setResult(true);
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
	 * @param ids id集合
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/8 10:08
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(Long[] ids) {
		BaseResult<IcloudEnterpriseauth> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("删除企业认证成功");
		try {
			if (ArrayUtils.isNotEmpty(ids)) {
				for (Long id : ids) {
					icloudEnterpriseauthService.delete(id);
				}
			} else {
				baseResult.setResult(false);
				baseResult.setMessage("删除企业认证成功必须要传入企业id");
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
