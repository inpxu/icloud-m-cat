/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.zhiyun.entity.*;
import com.zhiyun.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dto.BusinessTypeDto;
import com.zhiyun.dto.MarketClassDto;
import com.zhiyun.dto.MarketDto;
import com.zhiyun.dto.MarketEntryDto;

/**
 * 入市场controller
 *
 * @author 邓艺
 * @version v1.0
 * @date 2018-06-09 10:38
 */
@Controller
@RequestMapping(value = "/marketentry")
@ResponseBody
public class IcloudMarketentryController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IcloudMarketentryController.class);
	@Resource
	private IcloudMarketentryService icloudMarketentryService;

	@Resource
	private IcloudMarketentrydatashareurlService icloudMarketentrydatashareurlService;

	@Resource
	private IcloudEnterpriseauthService enterpriseauthService;
	@Resource
	private IcloudPersonalauthService personalauthService;
	@Resource
	private IcloudApplicationentryService icloudApplicationentryService;
	@Resource
	private IcloudUserMarketentryService icloudUserMarketentryService;

	/**
	 * 添加
	 *
	 * @param icloudMarketentry 参数
	 * @param bindingResult 参数校验
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/9 10:46
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object add(@Valid @RequestBody IcloudMarketentry icloudMarketentry, BindingResult bindingResult) {
		BaseResult<IcloudMarketentry> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("入市申请新增成功");
		try {
			if(!icloudUserMarketentryService.findUser(UserHolder.getId())){
				throw new BusinessException("该账户已入市! ");
			}
			// 校验参数是否合法
			if (icloudMarketentry.getStatus() == null) {
				// FIX ME
				icloudMarketentry.setStatus(AuditState.AUDITING);
			}
			StringBuilder stringBuilder = new StringBuilder();
			if (bindingResult.hasErrors()) {
				baseResult.setResult(false);
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
			icloudMarketentry.setSended("F");
			icloudMarketentry.setUpdated("F");
			icloudMarketentryService.save(icloudMarketentry);
			baseResult.setModel(icloudMarketentry);
			Long marketentryId = icloudMarketentry.getId();
			IcloudUserMarketentry marketentry = new IcloudUserMarketentry();
			marketentry.setMarketentryId(marketentryId);
			marketentry.setUserId(UserHolder.getId());
			icloudUserMarketentryService.insert(marketentry);
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
	 * 更新
	 *
	 * @param  参数
	 * @param bindingResult 参数校验
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/9 10:46
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(@Valid @RequestBody MarketEntryDto marketEntryDto, BindingResult bindingResult) {
		BaseResult<IcloudMarketentry> baseResult = new BaseResult<IcloudMarketentry>();
		baseResult.setResult(true);
		baseResult.setMessage("入市申请修改成功");
		try {
			IcloudMarketentry icloudMarketentry = new IcloudMarketentry();
			BeanUtils.copyProperties(marketEntryDto, icloudMarketentry);

			StringBuilder stringBuilder = new StringBuilder();
			if (bindingResult.hasErrors()) {
				baseResult.setResult(false);
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
			icloudMarketentryService.updateIcloudMarketentry(icloudMarketentry);

			baseResult.setModel(icloudMarketentry);
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
	 * 查询
	 *
	 * @param icloudMarketentry 条件
	 * @param bindingResult 参数校验
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/9 10:45
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public Object page(@Valid @RequestBody IcloudMarketentry icloudMarketentry, BindingResult bindingResult) {
		BaseResult<MarketEntryDto> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		try {
			// 校验参数是否合法
			StringBuilder stringBuilder = new StringBuilder();
			if (bindingResult.hasErrors()) {
				baseResult.setResult(false);
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
			IcloudMarketentry ime = icloudMarketentryService.findByUserId(icloudMarketentry.getCreateUserId());

			if(ime != null) {
				List<IcloudMarketentrydatashareurl> icloudMarketentrydatashareurls = icloudMarketentrydatashareurlService
						.findByMarketEntryId(ime.getId());

				MarketEntryDto marketEntryDto = new MarketEntryDto();
				marketEntryDto.setId(ime.getId());
				marketEntryDto.setBusinessType(ime.getBusinessType());
				marketEntryDto.setMarket(ime.getMarket());
				marketEntryDto.setMarketClass(ime.getMarketClass());
				marketEntryDto.setMarketEntryDataShareUrls(icloudMarketentrydatashareurls);
				marketEntryDto.setStatus(ime.getStatus());
				marketEntryDto.setEnterpriseProfile(ime.getEnterpriseProfile());
				marketEntryDto.setUserId(ime.getCreateUserId());
				marketEntryDto
						.setBusinessTypeDesc(EnterpriseConstant.BusinessType.getBusinessType(ime.getBusinessType()));
				marketEntryDto.setMarketDesc(EnterpriseConstant.Market.getMarket(ime.getMarket()));
//				marketEntryDto.setMarketClassDesc(EnterpriseConstant.MarketClass.getMarketClass(ime.getMarketClass()));
				marketEntryDto.setApprovalOpinion(ime.getApprovalOpinion());
				Long LogId = UserHolder.getId();
				Long id = ime.getId();
				Long usersId = icloudMarketentryService.get(id).getCreateUserId();
				if (LogId != null && LogId.equals(usersId)) {
					marketEntryDto.setJurisdiction(true);
				} else if (LogId != null && !LogId.equals(usersId)) {
					marketEntryDto.setJurisdiction(false);
				}
				baseResult.setModel(marketEntryDto);
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
	 * 入市申请删除
	 *
	 * @param ids id数组
	 * @return java.lang.String
	 * @author 邓艺
	 * @date 2018/6/9 10:51
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(Long[] ids) {
		BaseResult<IcloudMarketentry> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		baseResult.setMessage("入市申请删除成功");
		try {

			if (ArrayUtils.isEmpty(ids)) {
				baseResult.setResult(false);
				baseResult.setMessage("ids不能为空");
				return baseResult;
			}
			for (Long id : ids) {
				icloudMarketentryService.delete(id);
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

	@RequestMapping(value = "getBusinessType", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBusinessType(@RequestParam(value = "userId", required = false) Long userId) {

		BaseResult<List<BusinessTypeDto>> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		try {
			List<BusinessTypeDto> businessTypeDtos = new ArrayList<>();
			for (EnterpriseConstant.BusinessType businessType : EnterpriseConstant.BusinessType.values()) {
				businessTypeDtos.add(new BusinessTypeDto(businessType.getId(), businessType.getDesc()));
			}
			baseResult.setModel(businessTypeDtos);
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

	@RequestMapping(value = "getMarketClass", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getMarketClass(@RequestParam(value = "userId", required = false) Long userId) {
		BaseResult<List<MarketClassDto>> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		try {
			List<MarketClassDto> marketClasseDtos = new ArrayList<>();
			for (EnterpriseConstant.MarketClass marketClass : EnterpriseConstant.MarketClass.values()) {
				marketClasseDtos.add(new MarketClassDto(marketClass.getId(), marketClass.getDesc()));
			}
			baseResult.setModel(marketClasseDtos);
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

	@RequestMapping(value = "getMarket", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getMarket(@RequestParam(value = "userId", required = false) Long userId) {
		BaseResult<List<MarketDto>> baseResult = new BaseResult<>();
		baseResult.setResult(true);
		try {
			List<MarketDto> marketDtos = new ArrayList<>();
			for (EnterpriseConstant.Market market : EnterpriseConstant.Market.values()) {
				marketDtos.add(new MarketDto(market.getId(), market.getDesc()));
			}
			baseResult.setModel(marketDtos);
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
