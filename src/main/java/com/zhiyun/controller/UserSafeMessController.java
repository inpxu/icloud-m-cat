/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.UserDto;
import com.zhiyun.liferay.model.Message;
import com.zhiyun.liferay.model.MessageResult;
import com.zhiyun.liferay.model.SubmitCode;
import com.zhiyun.liferay.model.UpdatePassword;
import com.zhiyun.redis.RedisTemplateUtil;
import com.zhiyun.service.UserService;
import com.zhiyun.util.HttpUtil;
import com.zhiyun.util.MessageCodeUtil;

/**
 * 客户安全信息
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-11下午2:13:20
 */
@Controller
@RequestMapping(value = "/safeSet")
public class UserSafeMessController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserSafeMessController.class);
	
	@Value("${send.safe.code.url}")
	private String SEND_SAFE_CODE_URL = "";
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	

    public static final String SUCCESS_RET_CODE = "0000";

	
	/**
	 * 获取用户的安全等级信息
	 * @param: @return
	 * @return: Object 
	 * @author: 徐飞
	 * @date: 2018-6-11 下午2:06:20
	 */
	@ResponseBody
	@RequestMapping(value = "/safeMess", method = { RequestMethod.GET, RequestMethod.POST })
	public Object safeMess() {
		BaseResult<UserDto> baseResult = new BaseResult<UserDto>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			Long userid = UserHolder.getId();
			UserDto userDto = userService.findByUserId(userid);
			baseResult.setResult(true);
			baseResult.setModel(userDto);
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
	
	/**
	 * 原手机发送验证码
	 * @param: @return
	 * @return: Object 
	 * @author: 徐飞
	 * @date: 2018-6-11 下午7:31:50
	 */
	@ResponseBody
	@RequestMapping(value = "/sendFormerMess", method = { RequestMethod.GET, RequestMethod.POST })
	public Object sendFormerMess() {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			Long userid = UserHolder.getId();
			String code = MessageCodeUtil.generateMsgCode();
			String phone = userService.findByUserId(userid).getPhone();
	        JSONObject message = getMessage(phone, code);
	        String json = HttpUtil.post(SEND_SAFE_CODE_URL, message);
            MessageResult messageResult = JSON.parseObject(json, MessageResult.class);
            if (!SUCCESS_RET_CODE.equals(messageResult.getRetCode())) {
                logger.debug("短信发送失败：手机号{} 验证码{} , {}", new String []{phone, code, messageResult.getRetMsg()});
                baseResult.setResult(false);
                baseResult.setMessage("发送失败请稍后重试！");
            }else{
               redisTemplateUtil.set(phone, code, 120);
                baseResult.setMessage("验证码已发送, 有效期120秒");
            }
            System.out.println("code == "+code);
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
	
	/**
	 * 新手机号发送验证码
	 * @param: @param submitCode
	 * @param: @return
	 * @return: Object 
	 * @author: 徐飞
	 * @date: 2018-6-12 上午8:07:15
	 */
	@ResponseBody
	@RequestMapping(value = "/sendNewMess", method = { RequestMethod.GET, RequestMethod.POST })
	public Object sendNewMess(@Valid SubmitCode submitCode) {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			Long userid = UserHolder.getId();
			String code = MessageCodeUtil.generateMsgCode();
			String phoneN = submitCode.getPhone1();
			String phoneF = userService.findByUserId(userid).getPhone();
			if (phoneN == null) {
				throw new BusinessException("手机号不能为空！");
			} else if (phoneN.equals(phoneF)) {
				throw new BusinessException("手机号与原手机相同！");
			} else if (userService.findByPhone(phoneN) != null) {
				throw new BusinessException("该手机号已被注册！");
			}
	        JSONObject message = getMessage(phoneN, code);
	        String json = HttpUtil.post(SEND_SAFE_CODE_URL, message);
            MessageResult messageResult = JSON.parseObject(json, MessageResult.class);
            if (!SUCCESS_RET_CODE.equals(messageResult.getRetCode())) {
                logger.debug("短信发送失败：手机号{} 验证码{} , {}", new String []{phoneN, code, messageResult.getRetMsg()});
                baseResult.setResult(false);
                baseResult.setMessage("发送失败请稍后重试！");
            }else{
                redisTemplateUtil.set(phoneN, code, 120);
                baseResult.setMessage("验证码已发送, 有效期120秒");
            }
            System.out.println("code == "+code);
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
	
	/**
	 * 修改手机号
	 * @param: @param submitCode
	 * @param: @return
	 * @return: Object 
	 * @author: 徐飞
	 * @date: 2018-6-12 上午9:21:13
	 */
	@ResponseBody
	@RequestMapping(value = "/submitMess", method = { RequestMethod.GET, RequestMethod.POST })
	public Object submitMess(@Valid SubmitCode submitCode) {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			Long userid = UserHolder.getId();
			String phoneN = submitCode.getPhone1();
			String phoneF = userService.findByUserId(userid).getPhone();
			// 发送的验证码
			String codeN = String.valueOf(redisTemplateUtil.get(phoneN));
			String codeF = String.valueOf(redisTemplateUtil.get(phoneF));
			// 输入的验证码
			String code1 = submitCode.getCode1();
			String code2 = submitCode.getCode2();
			if (code1 == null || code2 == null || code1 == "" || code2 == "") {
				throw new BusinessException("验证码不能为空！");
			} else if (codeF == "null" || codeF == null) {
				throw new BusinessException("原手机的验证码已过期, 请重新获取！");
			} else if (codeN == "null" || codeN == null) {
				throw new BusinessException("新手机的验证码已过期, 请重新获取！");
			} else if (!code1.equals(codeF)) {
				throw new BusinessException("原手机的验证码输入错误, 请重新填写！");
			} else if (!code2.equals(codeN)) {
				throw new BusinessException("新手机的验证码输入错误, 请重新填写！");
			} else {
				userService.updateUrl(phoneN);
				userService.updateScreenname(userid, phoneN);
				UserHolder.getUser().setAccountName("p" + phoneN);
				baseResult.setMessage("手机号更改成功！");
				userService.dispatchUser(userid);
			}
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

	private JSONObject getMessage(String phone, String code) {
		Message message = new Message(phone, code);
		return JSONObject.parseObject(JSONObject.toJSONString(message));
	}
	
	/**
	 * 更改密码发送验证码
	 * @param: @param submitCode
	 * @param: @return
	 * @return: Object 
	 * @author: 徐飞
	 * @date: 2018-6-12 上午9:34:37
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMess", method = { RequestMethod.GET, RequestMethod.POST })
	public Object sendMess(@Valid SubmitCode submitCode,BindingResult bindingResult) {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			vaildParamsDefault(baseResult,bindingResult);
			Long userid = UserHolder.getId();
			String code = MessageCodeUtil.generateMsgCode();
			String phoneN = submitCode.getPhone1();
			String phoneF = userService.findByUserId(userid).getPhone();
			if (phoneN == null) {
				throw new BusinessException("手机号不能为空！");
			} else if (!phoneN.equals(phoneF)) {
				throw new BusinessException("输入的手机号与绑定的手机号不相同！");
			}
	        JSONObject message = getMessage(phoneN, code);
	        String json = HttpUtil.post(SEND_SAFE_CODE_URL, message);
            MessageResult messageResult = JSON.parseObject(json, MessageResult.class);
            if (!SUCCESS_RET_CODE.equals(messageResult.getRetCode())) {
                logger.debug("短信发送失败：手机号{} 验证码{} , {}", new String []{phoneN, code, messageResult.getRetMsg()});
                baseResult.setResult(false);
                baseResult.setMessage("发送失败请稍后重试！");
            }else{
                redisTemplateUtil.set(phoneN, code, 120);
                baseResult.setMessage("验证码已发送, 有效期120秒");
            }
            System.out.println("code == "+code);
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
	
	/**
	 * 修改密码提交
	 * @param: @param submitCode 验证码
	 * @param: @param updatePassword 密码
	 * @param: @return
	 * @return: Object 
	 * @author: 徐飞
	 * @date: 2018-6-12 上午10:44:57
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePw", method = { RequestMethod.GET, RequestMethod.POST })
	public Object updatePw(@Valid SubmitCode submitCode, UpdatePassword updatePassword) {
		BaseResult<String> baseResult = new BaseResult<String>();
		baseResult.setResult(true);
		baseResult.setMessage("操作成功");
		try {
			Long userid = UserHolder.getId();
			String phoneF = userService.findByUserId(userid).getPhone();
			String phoneN = submitCode.getPhone1();
			// 输入的密码
			String password1 = updatePassword.getPassword1();
			String password2 = updatePassword.getPassword2();
			// 输入的验证码
			String code1 = submitCode.getCode1();
			// 发送的验证码
			String codeN = String.valueOf(redisTemplateUtil.get(phoneN));
			// 手机号判断

			if (phoneN == null || !phoneN.equals(phoneF)) {
				throw new BusinessException("获取验证码后, 请勿修改输入的手机号码！");
			} 
			// 验证码判断
			if (code1 == null || code1 == "") {
				throw new BusinessException("验证码不能为空！");
			} else if (codeN == "null" || codeN == null) {
				throw new BusinessException("验证码已过期, 请重新获取！");
			}else if (!code1.equals(codeN)) {
				throw new BusinessException("验证码输入错误, 请重新输入！");
			}
			// 密码判断
			if (password1 == null || password1 == "" || password2 == null || password2 == "") {
				throw new BusinessException("密码不能为空！");
			} else if (!password2.equals(password1)) {
				throw new BusinessException("两次输入的密码不同, 请重新输入！");
			}
			userService.updatePassword(updatePassword);
			baseResult.setMessage("密码修改成功！");
	       
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
