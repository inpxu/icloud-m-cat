/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.entity.CasUser;
import com.zhiyun.service.CasUserService;

@Controller
@RequestMapping(value="/test")
public class TestController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Resource
	private CasUserService casUserService;
	
    /**
     * 新增用户
     *
     * @param casUser 用户对象
     * @return java.lang.String json格式字符串
     * @author 庄超
     */
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object add(@Valid CasUser casUser, BindingResult bindingResult) {
		BaseResult<CasUser> baseResult = new BaseResult<CasUser>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			logger.debug("MVCrequest:/demoView/add请求参数={}",JSON.toJSONString(casUser));
			//初步参数验证
			vaildParamsDefault(baseResult, bindingResult);
			//业务操作
			casUserService.insert(casUser);
			
			baseResult.setModel(casUser);
		} catch (BusinessException be) {
			logger.debug("业务异常"+be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			logger.debug("系统异常"+e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		logger.debug("MVCresponse:/demoView/add响应结果={}",JSON.toJSONString(baseResult));
		return JSON.toJSONString(baseResult);
	}
	
    /**
     * 查询所有用户
     *
     * @return java.lang.String json格式字符串
     * @author 庄超
     */
	@ResponseBody
	@RequestMapping(value = "/listAll", method = { RequestMethod.POST })
	public Object listAll() {
		BaseResult<List<CasUser>> baseResult = new BaseResult<List<CasUser>>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			logger.debug("/demoView/listAll请求参数={}");
			List<CasUser> dataGrid = casUserService.findAll();
			
			baseResult.setModel(dataGrid);
		} catch (BusinessException be) {
			logger.debug("业务异常"+be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			logger.debug("系统异常"+e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		logger.debug("/demoView/page响应结果={}",JSON.toJSONString(baseResult));
		return JSON.toJSONString(baseResult);
	}
	
	/**
	 * 分页
	 * @param CasUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page", method = { RequestMethod.POST })
	public Object page(@Valid CasUser CasUser, BindingResult bindingResult, Pager pager) {
		BaseResult<DataGrid<CasUser>> baseResult = new BaseResult<DataGrid<CasUser>>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			logger.debug("/demoView/page请求参数={}",JSON.toJSONString(CasUser));
			//初步参数验证
			vaildParamsDefault(baseResult, bindingResult);
			
			//业务操作
			Params params = Params.create();
			params.add("entity", CasUser);
			DataGrid<CasUser> dataGrid = casUserService.page(params, pager.getPage());
			
			baseResult.setModel(dataGrid);
		} catch (BusinessException be) {
			logger.debug("业务异常"+be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			logger.debug("系统异常"+e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		logger.debug("/demoView/page响应结果={}",JSON.toJSONString(baseResult));
		return JSON.toJSONString(baseResult);
	}
	
	/**
	 * 批量操作
	 * @param CasUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Object delete(@RequestParam(value = "ids[]") Long[] ids) {
		BaseResult<CasUser> baseResult = new BaseResult<CasUser>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			logger.debug("/demoView/delete请求参数={}",JSON.toJSONString(ids));
			//参数验证
			if (ids == null || ids.length == 0) {
				throw new BusinessException("集合为空");
			}
			//业务操作
			casUserService.delete(Arrays.asList(ids));
		} catch (BusinessException be) {
			logger.debug("业务异常"+be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			logger.debug("系统异常"+e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		logger.debug("/demoView/delete响应结果={}",JSON.toJSONString(baseResult));
		return JSON.toJSONString(baseResult);
	}
	
}
