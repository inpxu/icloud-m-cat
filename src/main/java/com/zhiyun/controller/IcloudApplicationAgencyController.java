/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dto.ApplicationAgencyDto;
import com.zhiyun.dto.IcloudApplicationagencyDto;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.entity.User;
import com.zhiyun.service.IcloudApplicationagencyService;
import com.zhiyun.service.IcloudEnterpriseauthService;
import com.zhiyun.service.IcloudPersonalauthService;
import com.zhiyun.service.IcloudUserApplicationentryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ApplicationAgencyController
 *代理
 * @author 祝天洋
 * @version v1.0
 * @date 2018年7月11日 下午1:04:32
 */

@RestController
@RequestMapping(value = "/applicationAgency")
public class IcloudApplicationAgencyController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IcloudApplicationAgencyController.class);

	@Resource
	private IcloudApplicationagencyService icloudApplicationagencyService;

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
	 * @author: 祝天洋
	 * @param: @param icloudApplicationentry
	 * @param: @param bindingResult
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public Object add(@Valid @RequestBody IcloudApplicationagencyDto icloudApplicationagencyDto, BindingResult bindingResult) {

		BaseResult<IcloudApplicationagencyDto> baseResult = new BaseResult<>();
		try {
			String agencyType = icloudApplicationagencyDto.getAgencyType();
			if(StringUtils.isEmpty(agencyType)){
				throw new BusinessException("代理行业不能为空，请选择! ");
			}
			String agencyArea = icloudApplicationagencyDto.getAgencyArea();
			if(StringUtils.isEmpty(agencyArea)){
				throw new BusinessException("代理地区不能为空，请选择! ");
			}
			IcloudApplicationagency agencys = new IcloudApplicationagency();
			agencys.setAgencyType(agencyType);
			agencys.setAgencyArea(agencyArea);

			List<IcloudApplicationagency> icloudApplicationagencies = icloudApplicationagencyService.find(agencys);
			if (null != icloudApplicationagencies && icloudApplicationagencies.size()>0){
				throw new BusinessException("该代理行业和地区已存在，请重新选择! ");
			}
			vaildParamsDefault(baseResult, bindingResult);
			baseResult.setResult(true);
            icloudApplicationagencyDto.setOrganizationId(UserHolder.getCompanyId());
            icloudApplicationagencyDto.setUserId(UserHolder.getId());
			icloudApplicationagencyService.save(icloudApplicationagencyDto);
			baseResult.setModel(icloudApplicationagencyDto);
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
	 * @author: zty
	 * @param: @param applicationagency
	 * @param: @param bindingResult
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public Object update(@Valid @RequestBody IcloudApplicationagencyDto icloudApplicationagencyDto,
			BindingResult bindingResult) {

		BaseResult<IcloudApplicationagency> baseResult = new BaseResult<IcloudApplicationagency>();
		try {
			Long id = icloudApplicationagencyDto.getId();
			if(StringUtils.isEmpty(id)){
				throw new BusinessException("缺少参数id! ");
			}
			String agencyType = icloudApplicationagencyDto.getAgencyType();
			if(StringUtils.isEmpty(agencyType)){
				throw new BusinessException("代理行业不能为空，请选择! ");
			}
			String agencyArea = icloudApplicationagencyDto.getAgencyArea();
			if(StringUtils.isEmpty(agencyArea)){
				throw new BusinessException("代理地区不能为空，请选择! ");
			}
			IcloudApplicationagency agencys = new IcloudApplicationagency();
			agencys.setAgencyType(agencyType);
			agencys.setAgencyArea(agencyArea);
			List<IcloudApplicationagency> icloudApplicationagencies = icloudApplicationagencyService.find(agencys);
			if (null != icloudApplicationagencies && icloudApplicationagencies.size()>0){
				for (IcloudApplicationagency item : icloudApplicationagencies) {
					if (!item.getUserId().equals(UserHolder.getId())){
						throw new BusinessException("该代理行业和地区已存在，请重新选择! ");
					}
				}
			}
			vaildParamsDefault(baseResult, bindingResult);
			baseResult.setResult(true);
			icloudApplicationagencyDto.setUserId(UserHolder.getId());
			icloudApplicationagencyDto.setCompanyId(UserHolder.getCompanyId());
            icloudApplicationagencyDto = icloudApplicationagencyService.updateApplicationAgency(icloudApplicationagencyDto);
			baseResult.setModel(icloudApplicationagencyDto);
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
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public Object list() {
		BaseResult<List<IcloudApplicationagencyDto>> baseResult = new BaseResult<>();
		try {
		    baseResult.setResult(Boolean.TRUE);
            List<IcloudApplicationagencyDto> icloudApplicationagencyDtos = icloudApplicationagencyService.listApplicationAgencyDto();
		    baseResult.setModel(icloudApplicationagencyDtos);
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
	 * @Title: getAgencyArea
	 * @Description: 获取可申请地区
	 * @author: 祝天洋
	 * @param: @param
	 * @param: @param
	 * @param: @return
	 * @return: Object
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getAgencyArea", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getAgencyArea(@RequestParam(value = "exceptId", required = false) Long exceptId,
                                @RequestParam(value = "agencyType", required =  true) String agencyType) {

		BaseResult<JSONArray> baseResult = new BaseResult<>();
		try {
			if (StringUtils.isEmpty(agencyType)){
				throw new BusinessException("请先选择代理行业! ");
			}
            baseResult.setResult(true);
			JSONArray jsonArray = icloudApplicationagencyService.getAgencyArea(exceptId,agencyType);
			baseResult.setModel(jsonArray);

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
	 * @Title: getAgencyType
	 * @Description: 获取代理行业
	 * @author: 祝天洋
	 * @param: @param
	 * @param: @param
	 * @param: @return
	 * @return: Object
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getAgencyType", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getAgencyType() {
		BaseResult<JSONArray> baseResult = new BaseResult<>();
		JSONArray businessTypes = new JSONArray();
		try {
			baseResult.setResult(true);
			EnterpriseConstant.Industry[] values = EnterpriseConstant.Industry.values();
			for (EnterpriseConstant.Industry businessType : values) {
				JSONObject pObj = new JSONObject();
				pObj.put("id", businessType.id);
				pObj.put("name", businessType.label);
				businessTypes.add(pObj);
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
		baseResult.setModel(businessTypes);
		return baseResult;
	}

}
