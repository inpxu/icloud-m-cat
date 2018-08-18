package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dto.ApplicationEntryDto;
import com.zhiyun.dto.EnterpriseAuthDto;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.entity.IcloudApplicationentry;
import com.zhiyun.entity.IcloudOnicloud;
import com.zhiyun.service.IcloudOnicloudService;
import com.zhiyun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/12 11:15
 * @Description:
 */
@RestController
@RequestMapping(value = "/onicloud")
public class IcloudOnIcloudController  extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IcloudOnIcloudController.class);

    @Resource
    private IcloudOnicloudService icloudOnicloudService;

    @Resource
    private UserService userService;

    /**
     *
     * @Title: add
     * @Description: 新增
     * @author: 孙云涛
     * @param: @param IcloudOnIcloudController
     * @param: @param bindingResult
     * @param: @return
     * @return: Object
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
    public Object add(@Valid @RequestBody IcloudOnicloud icloudOnicloud, BindingResult bindingResult) {

        BaseResult<IcloudOnicloud> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        try {
            vaildParamsDefault(baseResult,bindingResult);
            icloudOnicloudService.addOnIncloud(icloudOnicloud);

            if(icloudOnicloud != null){
                int in = icloudOnicloud.getEquipmentScale();
                int on = icloudOnicloud.getOnIcloudScale();
                if (in < on){
                    throw new BusinessException("上云设备数不能大于设备总数！");
                }
                IcloudOnicloudDto icloudOnicloudDto = new IcloudOnicloudDto();
                BeanUtils.copyProperties(icloudOnicloud,icloudOnicloudDto);
                icloudOnicloudDto.setPropertyDesc(EnterpriseConstant.Property.getPropertyDesc(icloudOnicloud.getProperty()));
                icloudOnicloudDto.setEmployeeScaleDesc(EnterpriseConstant.EmployeeScale.getEmployeeScaleDesc(icloudOnicloud.getEmployeeScale()));
                icloudOnicloudDto.setTradeDesc(EnterpriseConstant.Industry.getIndustryDesc(icloudOnicloud.getTrade()+""));
                baseResult.setModel(icloudOnicloudDto);
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
    public Object update(@Valid @RequestBody IcloudOnicloud icloudOnicloud,
                         BindingResult bindingResult) {

        BaseResult<IcloudOnicloud> baseResult = new BaseResult<IcloudOnicloud>();
        try {
            vaildParamsDefault(baseResult, bindingResult);

            baseResult.setResult(true);
            icloudOnicloudService.updateOnIncloud(icloudOnicloud);
            baseResult.setModel(icloudOnicloud);
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
    public Object get() {

        BaseResult<IcloudOnicloud> baseResult = new BaseResult<>();
        try {

            baseResult.setResult(true);
            IcloudOnicloud icloudOnicloud = icloudOnicloudService.getOnIcloudByUserId(UserHolder.getId());

            IcloudOnicloudDto icloudOnicloudDto = new IcloudOnicloudDto();
            if(icloudOnicloud != null){
                BeanUtils.copyProperties(icloudOnicloud,icloudOnicloudDto);
                icloudOnicloudDto.setPropertyDesc(EnterpriseConstant.Property.getPropertyDesc(icloudOnicloud.getProperty()));
                icloudOnicloudDto.setEmployeeScaleDesc(EnterpriseConstant.EmployeeScale.getEmployeeScaleDesc(icloudOnicloud.getEmployeeScale()));
                icloudOnicloudDto.setTradeDesc(EnterpriseConstant.Industry.getIndustryDesc(icloudOnicloud.getTrade()+""));
            }else {
                EnterpriseAuthDto enterpriseAuthDto =  userService.getEnterpriseAuth(UserHolder.getId());
                if(enterpriseAuthDto != null){
                    icloudOnicloudDto.setCity(enterpriseAuthDto.getCity());
                    icloudOnicloudDto.setProvince(enterpriseAuthDto.getProvince());
                    icloudOnicloudDto.setDistrict(enterpriseAuthDto.getDeleted());
                    icloudOnicloudDto.setDetailedAddress(enterpriseAuthDto.getDetailedAddress());
                    icloudOnicloudDto.setName(enterpriseAuthDto.getName());
                    icloudOnicloudDto.setContactPerson(enterpriseAuthDto.getContactPerson());
                    icloudOnicloudDto.setTrade(enterpriseAuthDto.getTrade());
                    icloudOnicloudDto.setTradeDesc(EnterpriseConstant.Industry.getIndustryDesc(enterpriseAuthDto.getTrade()+""));
                    icloudOnicloudDto.setContactPhone(enterpriseAuthDto.getContactPhone());
                }
                icloudOnicloudDto.setStatus(AuditState.UNDOAUDIT);
            }

            baseResult.setModel(icloudOnicloudDto);
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
