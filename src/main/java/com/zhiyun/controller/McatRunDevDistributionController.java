package com.zhiyun.controller;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.service.McatRunDevService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
    private McatRunDevService mcatRunDevService;



}
