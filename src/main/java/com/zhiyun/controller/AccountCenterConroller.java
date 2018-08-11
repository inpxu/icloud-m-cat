package com.zhiyun.controller;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.BaseUserInfoDto;
import com.zhiyun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/11 10:14
 * @Description:
 */
@RestController
@RequestMapping(value = "/accountCenter")
public class AccountCenterConroller extends BaseController {

    @Resource
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCenterConroller.class);

    @ResponseBody
    @RequestMapping(value = "/getAccountInfo", method = { RequestMethod.GET, RequestMethod.POST })
    public Object getAccountInfo() {
        BaseResult<BaseUserInfoDto> baseResult = new BaseResult<BaseUserInfoDto>();
        baseResult.setResult(true);
        baseResult.setMessage("查询账号信息成功");
        try {

            BaseUserInfoDto baseUserInfoDto = userService.getBaseUserInfoDto(UserHolder.getId());
            baseResult.setModel(baseUserInfoDto);

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
