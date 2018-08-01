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
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dto.ApplicationEntryDto;
import com.zhiyun.dto.UserStateDto;
import com.zhiyun.entity.IcloudApplicationentry;
import com.zhiyun.service.IcloudApplicationentryService;
import com.zhiyun.service.IcloudEnterpriseauthService;
import com.zhiyun.service.IcloudPersonalauthService;
import com.zhiyun.service.IcloudUserApplicationentryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * ApplicationEntryController
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月9日 下午1:04:32 
 */

@RestController
@RequestMapping(value = "/applicationEntry")
public class IcloudApplicationEntryController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IcloudApplicationEntryController.class);

	@Resource
	private IcloudApplicationentryService icloudApplicationentryService;

	@Resource
	private IcloudEnterpriseauthService enterpriseauthService;
	@Resource
	private IcloudPersonalauthService personalauthService;
	@Resource
	private IcloudUserApplicationentryService icloudUserApplicationentryService;
	/**
	 * 
	 * @Title: add   
	 * @Description: 新增
	 * @author: 孙云涛  
	 * @param: @param icloudApplicationentry
	 * @param: @param bindingResult
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public Object add(@Valid @RequestBody IcloudApplicationentry icloudApplicationentry, BindingResult bindingResult) {

		BaseResult<IcloudApplicationentry> baseResult = new BaseResult<>();
		try {
			if(!icloudUserApplicationentryService.findUser(UserHolder.getId())){
				throw new BusinessException("该账户已绑定所属公司! ");
			}
			vaildParamsDefault(baseResult, bindingResult);

			baseResult.setResult(true);
			icloudApplicationentry.setStatus(AuditState.AUDITING);
			icloudApplicationentry.setSended("F");
			icloudApplicationentry.setUpdated("F");
			icloudApplicationentryService.save(icloudApplicationentry);
			baseResult.setModel(icloudApplicationentry);

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
	 * 
	 * @Title: update   
	 * @Description: 修改
	 * @author: 孙云涛  
	 * @param: @param icloudApplicationentry
	 * @param: @param bindingResult
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public Object update(@Valid @RequestBody ApplicationEntryDto applicationentry,
			BindingResult bindingResult) {

		BaseResult<IcloudApplicationentry> baseResult = new BaseResult<IcloudApplicationentry>();
		try {
			vaildParamsDefault(baseResult, bindingResult);

			baseResult.setResult(true);
			IcloudApplicationentry icloudApplicationentry = new IcloudApplicationentry();
			BeanUtils.copyProperties(applicationentry, icloudApplicationentry);
			icloudApplicationentry = icloudApplicationentryService.updateApplicationEntry(icloudApplicationentry);
			baseResult.setModel(icloudApplicationentry);
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

	@ResponseBody
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	public Object get(@RequestParam(value = "userId", required = false) Long userId) {

		BaseResult<ApplicationEntryDto> baseResult = new BaseResult<>();
		try {
			baseResult.setResult(true);
			IcloudApplicationentry icloudApplicationentry = icloudApplicationentryService
					.findIcloudApplicationentryByUserId(userId);
			if (icloudApplicationentry != null) {
				ApplicationEntryDto applicationEntryDto = new ApplicationEntryDto();
				applicationEntryDto.setCreateUserId(icloudApplicationentry.getCreateUserId());
				applicationEntryDto.setName(icloudApplicationentry.getName());
				applicationEntryDto.setLegalPerson(icloudApplicationentry.getLegalPerson());
				applicationEntryDto.setProperty(icloudApplicationentry.getProperty());
				applicationEntryDto.setPropertyDesc(
						EnterpriseConstant.Property.getPropertyDesc(icloudApplicationentry.getProperty()));
				applicationEntryDto.setType(icloudApplicationentry.getType());
				applicationEntryDto.setTypeDesc(EnterpriseConstant.Industry.getIndustryDesc(icloudApplicationentry.getType()+""));
				applicationEntryDto.setEnterpriseScale(icloudApplicationentry.getEnterpriseScale());
				applicationEntryDto.setEnterpriseScaleDesc(EnterpriseConstant.EnterpriseScale
						.getEnterpriseScaleDesc(icloudApplicationentry.getEnterpriseScale()));

				applicationEntryDto.setSiteImageShareUrl(icloudApplicationentry.getSiteImageShareUrl());
				applicationEntryDto.setStatus(icloudApplicationentry.getStatus());
				applicationEntryDto.setStatusDesc(AuditState.getStateDesc(icloudApplicationentry.getStatus()));
				applicationEntryDto.setId(icloudApplicationentry.getId());
				applicationEntryDto.setApprovalOpinion(icloudApplicationentry.getApprovalOpinion());
				applicationEntryDto.setJingbianjiNum(icloudApplicationentry.getJingbianjiNum());
				applicationEntryDto.setZhengjingjiNum(icloudApplicationentry.getZhengjingjiNum());
				applicationEntryDto.setQitaNum(icloudApplicationentry.getQitaNum());
				Long LogId = UserHolder.getId();
				Long id = icloudApplicationentry.getId();
				Long usersId = icloudApplicationentryService.get(id).getCreateUserId(); 
				if (LogId != null && LogId.equals(usersId)) {
					applicationEntryDto.setJurisdiction(true);
				} else if (LogId != null && !LogId.equals(usersId)){
					applicationEntryDto.setJurisdiction(false);
				}
				baseResult.setModel(applicationEntryDto);
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
		System.out.println(JSON.toJSONString(baseResult));
		return baseResult;
	}

}
