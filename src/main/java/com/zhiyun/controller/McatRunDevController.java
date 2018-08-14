package com.zhiyun.controller;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.dto.McatRunDevDto;
import com.zhiyun.entity.McatRunDev;
import com.zhiyun.service.McatRunDevService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 设备运维
 * @Auther: sunyuntao
 * @Date: 2018/08/14 10:01
 * @Description:
 */
@Controller
@RequestMapping(value = "/mcatRunDev")
public class McatRunDevController  extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(McatRunDevController.class);

    @Resource
    private McatRunDevService mcatRunDevService;

    /**
     * 远程运维查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listEnterprise", method = RequestMethod.POST)
    public Object listEnterprise(McatRunDevDto mcatRunDevDto , Pager pager) {
        BaseResult<DataGrid<McatRunDev>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("远程运维查询");
        try {
            baseResult.setModel(mcatRunDevService.myEnterprisePage(mcatRunDevDto,pager));
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
     * 远程运维查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listAgency", method = RequestMethod.POST)
    public Object listAgency(McatRunDevDto mcatRunDevDto , Pager pager) {
        BaseResult<DataGrid<McatRunDev>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("远程运维查询");
        try {
            baseResult.setModel(mcatRunDevService.myAgencyPage(mcatRunDevDto,pager));
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
     * 指令下发
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "sendInstruction", method = RequestMethod.POST)
    public Object sendInstruction(String[] commcodes,Integer startTime,Integer endTime,Integer isClear) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("远程运维下发指令");
        try {
            mcatRunDevService.sendInstruction(Arrays.asList(commcodes),startTime,endTime,isClear);
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
