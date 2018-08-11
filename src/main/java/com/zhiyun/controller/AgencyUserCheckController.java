package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.dto.AgencyUserCheckDto;
import com.zhiyun.dto.AgencyUserCheckSearchDto;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.internal.server.icloud.service.ApplicationEntryInterface;
import com.zhiyun.internal.server.oc.approlval.service.ApprolvalUpdateInterface;
import com.zhiyun.internal.server.oc.entity.ApprolvalEntry;
import com.zhiyun.service.IcloudApplicationagencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AgencyUserCheckController
 *
 * @author 祝天洋
 * @version v1.0
 * @date 2018年7月28日 下午1:04:32
 */

@RestController
@RequestMapping(value = "/agencyUserCheck")
public class AgencyUserCheckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgencyUserCheckController.class);
    @Autowired
    private IcloudApplicationagencyService icloudApplicationagencyService;

    @Autowired
    private ApplicationEntryInterface applicationEntryInterface;

    @Resource
    private ApprolvalUpdateInterface approlvalUpdateInterface;

    @ResponseBody
    @RequestMapping(value = "/get/{pageNum}/{pageSize}", method = { RequestMethod.GET, RequestMethod.POST })
    public Object get(@PathVariable int pageSize, @PathVariable int pageNum, AgencyUserCheckSearchDto searchDto) {

        BaseResult<JSONObject> baseResult = new BaseResult<>();
        try {
            baseResult.setResult(true);
            Long userId = UserHolder.getId();
            Map params = new HashMap();
            if(!StringUtils.isEmpty(searchDto.getInProvince())){
                params.put("inProvince", searchDto.getInProvince());
            }
            if(!StringUtils.isEmpty(searchDto.getInCity())){
                params.put("inCity", searchDto.getInCity());
            }
            if(!StringUtils.isEmpty(searchDto.getCompanyNature())){
                List<Integer> propertyIds = new ArrayList<>();
                EnterpriseConstant.Property[] values = EnterpriseConstant.Property.values();
                for (EnterpriseConstant.Property property : values) {
                    if (property.getDesc().indexOf(searchDto.getCompanyNature())>=0){
                        propertyIds.add(property.getId());
                    }
                }
                params.put("companyNature", propertyIds);
            }
            if(!StringUtils.isEmpty(searchDto.getCompanyModel())){
                params.put("companyModel", searchDto.getCompanyModel());
            }
            if(null != searchDto.getZhengjingjiNum()){
                params.put("zhengjingjiNum", searchDto.getZhengjingjiNum());
            }
            if(null != searchDto.getJingbianjiNum()){
                params.put("jingbianjiNum", searchDto.getJingbianjiNum());
            }
            if(null != searchDto.getQitaNum()){
                params.put("qitaNum", searchDto.getQitaNum());
            }
            if(null != searchDto.getAuthType()){
                params.put("authType", searchDto.getAuthType());
            }

            Date authDateL = null;
            Date authDateH = null;
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(searchDto.getAuthDateL() !=null  ){
                authDateL = sDateFormat.parse(searchDto.getAuthDateL());
            }
            if(searchDto.getAuthDateH() !=null  ) {
                authDateH = sDateFormat.parse(searchDto.getAuthDateH());
            }

            Calendar currentDate = new GregorianCalendar();
            if (null != searchDto.getChecktype()) {
                switch (searchDto.getChecktype()) {
                    case "day":
                        currentDate = new GregorianCalendar();

                        currentDate.set(Calendar.HOUR_OF_DAY, 0);
                        currentDate.set(Calendar.MINUTE, 0);
                        currentDate.set(Calendar.SECOND, 0);
                        authDateL = (Date) currentDate.getTime().clone();
                        currentDate = new GregorianCalendar();
                        currentDate.set(Calendar.HOUR_OF_DAY, 23);
                        currentDate.set(Calendar.MINUTE, 59);
                        currentDate.set(Calendar.SECOND, 59);
                        authDateH = (Date) currentDate.getTime().clone();
                        break;
                    case "week":
                        currentDate = new GregorianCalendar();
                        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
                        currentDate.set(Calendar.HOUR_OF_DAY, 0);
                        currentDate.set(Calendar.MINUTE, 0);
                        currentDate.set(Calendar.SECOND, 0);
                        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        authDateL = (Date) currentDate.getTime().clone();
                        currentDate = new GregorianCalendar();
                        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
                        currentDate.set(Calendar.HOUR_OF_DAY, 23);
                        currentDate.set(Calendar.MINUTE, 59);
                        currentDate.set(Calendar.SECOND, 59);
                        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        authDateH = (Date) currentDate.getTime().clone();
                    case "month":
                        currentDate = new GregorianCalendar();
                        currentDate.set(Calendar.HOUR_OF_DAY, 0);
                        currentDate.set(Calendar.MINUTE, 0);
                        currentDate.set(Calendar.SECOND, 0);
                        currentDate.set(Calendar.DAY_OF_MONTH, currentDate.getActualMinimum(Calendar.DAY_OF_MONTH));
                        authDateL = (Date) currentDate.getTime().clone();
                        currentDate = new GregorianCalendar();
                        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
                        currentDate.set(Calendar.HOUR_OF_DAY, 23);
                        currentDate.set(Calendar.MINUTE, 59);
                        currentDate.set(Calendar.SECOND, 59);
                        currentDate.set(Calendar.DAY_OF_MONTH, currentDate.getActualMaximum(Calendar.DAY_OF_MONTH));
                        authDateH = (Date) currentDate.getTime().clone();
                        break;
                    case "year": //消息回执
                        currentDate = new GregorianCalendar();
                        currentDate.set(Calendar.HOUR_OF_DAY, 0);
                        currentDate.set(Calendar.MINUTE, 0);
                        currentDate.set(Calendar.SECOND, 0);
                        currentDate.set(Calendar.DAY_OF_YEAR, currentDate.getActualMinimum(Calendar.DAY_OF_YEAR));
                        authDateL = (Date) currentDate.getTime().clone();
                        currentDate = new GregorianCalendar();
                        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
                        currentDate.set(Calendar.HOUR_OF_DAY, 23);
                        currentDate.set(Calendar.MINUTE, 59);
                        currentDate.set(Calendar.SECOND, 59);
                        currentDate.set(Calendar.DAY_OF_MONTH, currentDate.getActualMaximum(Calendar.DAY_OF_YEAR));
                        authDateH = (Date) currentDate.getTime().clone();
                    default:
                        break;
                }
            }
            params.put("authDateL", authDateL);
            params.put("authDateH", authDateH);

            if (null != userId){
                IcloudApplicationagency icloudApplicationagency = new IcloudApplicationagency();
                icloudApplicationagency.setUserId(userId);
                icloudApplicationagency.setStatus(2);
                icloudApplicationagency.setDeleted("F");
                List<IcloudApplicationagency> icloudApplicationagencies = icloudApplicationagencyService.find(icloudApplicationagency);
                List<JSONObject> pList = new ArrayList<>();
                JSONObject pObj = null;
                for (IcloudApplicationagency item :icloudApplicationagencies) {
                    pObj=new JSONObject();
                    String province =EnterpriseConstant.Province.getProvince(Integer.parseInt(item.getAgencyArea()));
                    province =province ==null?null:province.substring(0, 2);
                    pObj.put("province", province);
                    pObj.put("type", item.getAgencyType());
                    pList.add(pObj);
                }
                if (pList.size()>0){
                    params.put("provinceAndType", pList);
                }else{
                    throw new BusinessException("无代理区域或代理行业");
                }

            }else {
                throw new BusinessException("无法获取用户id");
            }
            params.put("pageNum", (pageNum-1)*pageSize);
            params.put("pageSize", pageSize);
            List<AgencyUserCheckDto> pageData = icloudApplicationagencyService.getAgencyUserCheckByparams(params);
            int total = icloudApplicationagencyService.getAgencyUserCheckByparamsCount(params);
            for (AgencyUserCheckDto dto:pageData) {
                dto.setPropertyDesc(EnterpriseConstant.Property.getPropertyDesc(dto.getProperty()));
                dto.setEnterpriseScaleDesc(EnterpriseConstant.EnterpriseScale
                        .getEnterpriseScaleDesc(dto.getEnterpriseScale()));
                dto.setTypeDesc(EnterpriseConstant.Industry.getIndustryDesc(dto.getType()+""));
                dto.setStatusDesc(AuditState.getStateDesc(dto.getStatus()));
            }
            JSONObject result = new JSONObject();
            result.put("data", pageData);
            result.put("total", total);
            baseResult.setModel(result);

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

    /**
     *
     * @Title: check
     * @Description: 审批
     * @author: 祝天洋
     * @param: @param icloudApplicationentry
     * @param: @param bindingResult
     * @param: @return
     * @return: Object
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/check", method = { RequestMethod.GET, RequestMethod.POST })
    public Object update(Long id,Integer checkResult,String opinion ) {
        BaseResult<BaseResult<String>> baseResult = new BaseResult<BaseResult<String>>();
        try {
            if(null == id){
                throw new BusinessException("缺少Id！");
            }
            if(null == checkResult){
                throw new BusinessException("缺少审批结果参数！");
            }
            baseResult.setResult(true);
            BaseResult<String> stringBaseResult = applicationEntryInterface.updateStatus(id, checkResult, opinion);
            ApprolvalEntry app = new ApprolvalEntry();

            app.setId(id);
            app.setAuthType(checkResult+"");
            app.setRemark(opinion);
            approlvalUpdateInterface.updateApprolvalEntry(app);

            baseResult.setModel(stringBaseResult);
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
     * @Description: 获取企业规模
     * @author: 祝天洋
     * @param: @param
     * @param: @param
     * @param: @return
     * @return: Object
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/getEnterpriseScale", method = { RequestMethod.GET, RequestMethod.POST })
    public Object getEnterpriseScale() {
        BaseResult<JSONArray> baseResult = new BaseResult<>();
        JSONArray businessTypes = new JSONArray();
        try {
            baseResult.setResult(true);
            EnterpriseConstant.EnterpriseScale[] values = EnterpriseConstant.EnterpriseScale.values();
            for (EnterpriseConstant.EnterpriseScale enterpriseScale : values) {
                JSONObject pObj = new JSONObject();
                pObj.put("value", enterpriseScale.getId());
                pObj.put("name", enterpriseScale.getDesc());
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
