package com.zhiyun.controller;

import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.entity.IcloudOnicloud;
import com.zhiyun.service.IcloudOnicloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/13 13:16
 * @Description:
 */
@RestController
@RequestMapping(value = "/customerManagement")
public class CustomerManagementController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerManagementController.class);

    @Resource
    private IcloudOnicloudService icloudOnicloudService;

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("list")
    public Object list(String search, Integer status, Pager pager){
        BaseResult<DataGrid<IcloudOnicloudDto>> baseResult = new BaseResult();

        baseResult.setResult(true);
        baseResult.setMessage("操作成功");
        try {
            Params params = Params.create();
            params.add("userId",UserHolder.getId());
            params.add("search",search);
            params.add("status",status);
            DataGrid<IcloudOnicloudDto> icloudOnicloudDataGrid= icloudOnicloudService.myPage(params,pager);

            if(icloudOnicloudDataGrid.getNumRows()>0){
                icloudOnicloudDataGrid.getItems().forEach(item->{
                    item.setPropertyDesc(EnterpriseConstant.Property.getPropertyDesc(item.getProperty()));
                    item.setEmployeeScaleDesc(EnterpriseConstant.EmployeeScale.getEmployeeScaleDesc(item.getEmployeeScale()));
                    item.setTradeDesc(EnterpriseConstant.Industry.getIndustryDesc(item.getTrade()+""));
                });
            }

            baseResult.setModel(icloudOnicloudDataGrid);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常"+be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常"+e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return baseResult;
    }


    /**
     *
     * @param
     * @return
     */
    @RequestMapping("get")
    public Object get(Long id){
        BaseResult<IcloudOnicloud> baseResult = new BaseResult();

        baseResult.setResult(true);
        baseResult.setMessage("操作成功");
        try {
            baseResult.setModel(icloudOnicloudService.get(id));
        } catch (BusinessException be) {
            LOGGER.debug("业务异常"+be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常"+e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return baseResult;
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("audit")
    public Object audit(IcloudOnicloud icloudOnicloud){
        BaseResult<IcloudOnicloud> baseResult = new BaseResult();

        baseResult.setResult(true);
        baseResult.setMessage("操作成功");
        try {
            //FIX ME 调用审核的接口
            icloudOnicloudService.update(icloudOnicloud);
            baseResult.setModel(icloudOnicloud);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常"+be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常"+e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return baseResult;
    }


}
