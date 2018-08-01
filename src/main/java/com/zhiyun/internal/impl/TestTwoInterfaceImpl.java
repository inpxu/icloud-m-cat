package com.zhiyun.internal.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.internal.base.BaseInterfaceImpl;
import com.zhiyun.internal.server.gateway.TestTwoInterface;
import com.zhiyun.internal.server.gateway.dto.TestDto;

@Service("testTwoInterface")
public class TestTwoInterfaceImpl extends BaseInterfaceImpl implements TestTwoInterface {
	private static final Logger logger = LoggerFactory
			.getLogger(TestTwoInterfaceImpl.class);

	@Override
	public BaseResult<String> doTest(TestDto certDto) {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		try {
			System.out.println(JSON.toJSONString(certDto));
			baseResult.setModel("okey");
		} catch (BusinessException be) {
			logger.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			logger.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;
	}

	@Override
	public BaseResult<String> runTest(TestDto certDto) {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		try {
			System.out.println(JSON.toJSONString(certDto));
			baseResult.setModel("ok");
		} catch (BusinessException be) {
			logger.debug("业务异常" + be);
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			logger.debug("系统异常" + e);
			baseResult.setResult(false);
			baseResult.setMessage("系统异常");
		}
		return baseResult;
	}


}
