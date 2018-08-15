package com.zhiyun.controller;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.dto.McatRunDevDto;
import com.zhiyun.dto.McatRundevCompanyDto;
import com.zhiyun.dto.McatRundevStateDto;
import com.zhiyun.entity.McatRunDev;
import com.zhiyun.entity.McatRunDevDistribution;
import com.zhiyun.service.McatRunDevDistributionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备分布
 * @Auther: sunyuntao
 * @Date: 2018/08/14 16:54
 * @Description:
 */
@Controller
@RequestMapping(value = "/mcatRunDevDistribution")
public class McatRunDevDistributionController  extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(McatRunDevDistributionController.class);

    @Resource
    private McatRunDevDistributionService mcatRunDevDistributionService;

    /**
     * 个人查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listEnterpriseCompany", method = RequestMethod.POST)
    public Object listEnterpriseCompany() {
        BaseResult<List<McatRundevCompanyDto>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listEnterpriseCompany());
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
     * 代理查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listAgencyCompany", method = RequestMethod.POST)
    public Object listAgencyCompany() {
        BaseResult<List<McatRundevCompanyDto>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listAgencyCompany());
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
     * 代理查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listEnterpriseMcatRunDevDistribution", method = RequestMethod.POST)
    public Object listEnterpriseMcatRunDevDistribution() {
        BaseResult<List<McatRunDevDistribution>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listEnterpriseMcatRunDevDistribution());
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
     * 代理查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listAgencyMcatRunDevDistribution", method = RequestMethod.POST)
    public Object listAgencyMcatRunDevDistribution() {
        BaseResult<List<McatRunDevDistribution>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listAgencyMcatRunDevDistribution());
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
     * 数量查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listAgencyMcatRundevStateDto", method = RequestMethod.POST)
    public Object listAgencyMcatRundevStateDto(McatRunDevDto mcatRunDevDto) {
        BaseResult<List<McatRundevStateDto>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("数量查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listAgencyMcatRundevStateDto(mcatRunDevDto));
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
     * 数量查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listEnterpriseMcatRundevStateDto", method = RequestMethod.POST)
    public Object listEnterpriseMcatRundevStateDto(McatRunDevDto mcatRunDevDto) {
        BaseResult<List<McatRundevStateDto>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listEnterpriseMcatRundevStateDto(mcatRunDevDto));
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
     * 数量查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listAgencyMcatRunDev", method = RequestMethod.POST)
    public Object listAgencyMcatRunDev(McatRunDevDto mcatRunDevDto) {
        BaseResult<List<McatRunDev>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listAgencyMcatRunDev(mcatRunDevDto));
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
     * 数量查询
     *
     * @param
     * @param
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/6/7 13:06
     */
    @ResponseBody
    @RequestMapping(value = "listEnterpriseMcatRunDev", method = RequestMethod.POST)
    public Object listEnterpriseMcatRunDev(McatRunDevDto mcatRunDevDto) {
        BaseResult<List<McatRunDev>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("公司查询");
        try {
            baseResult.setModel(mcatRunDevDistributionService.listEnterpriseMcatRunDev(mcatRunDevDto));
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
