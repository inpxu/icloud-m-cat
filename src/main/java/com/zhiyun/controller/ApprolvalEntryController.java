/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.util.CommonUtils;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.EnterpriseConstant;
import com.zhiyun.entity.*;
import com.zhiyun.internal.server.oc.approlval.service.ApprolvalInterface;
import com.zhiyun.internal.server.oc.approlval.service.ApprolvalUpdateInterface;
import com.zhiyun.internal.server.oc.entity.ApprolvalAuthentication;
import com.zhiyun.internal.server.oc.entity.ApprolvalEntry;
import com.zhiyun.internal.server.oc.entity.ApprolvalEntryMarket;
import com.zhiyun.internal.server.oc.entity.IcloudApplicationagency;
import com.zhiyun.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 入驻审核新增
 * 
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-16上午11:02:19
 */
@Component
public class ApprolvalEntryController {

    private static final Logger logger = LoggerFactory
    		.getLogger(ApprolvalEntryController.class);

    @Resource
    private ApprolvalInterface approlvalInterface;
    @Resource
    private IcloudApplicationentryService applicationentryService;
    @Resource
    private IcloudEnterpriseauthService enterpriseauthService;
    @Resource
    private IcloudMarketentryService marketentryService;
    @Resource
    private IcloudPersonalauthService personalauthService;
    @Resource
    private ApprolvalUpdateInterface approlvalUpdateInterface;
    @Resource
	private IcloudApplicationagencyService applicationagencyService;
    /**
     * 入驻
     *
     * @param: @return
     * @return: BaseResult<String>
     * @author: 徐飞
     * @date: 2018-6-16 下午2:04:54
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void insertApprolvalEntry() {
    	BaseResult<ApprolvalEntry> baseResult = new BaseResult<ApprolvalEntry>();
    	try {
    		List<IcloudApplicationentry> entrys = applicationentryService.findBySended();
    		if (!CommonUtils.isEmpty(entrys)) {
    			for (IcloudApplicationentry ic : entrys) {
    				ApprolvalEntry app = new ApprolvalEntry();
    				Long id = ic.getId();
    				Long userId = ic.getCreateUserId();
    				IcloudPersonalauth icPerson = personalauthService
    						.findByUserId(userId);
    				IcloudEnterpriseauth icEnter = enterpriseauthService
    						.findByUserId(userId);
    				if (icPerson != null) {
    					app.setUserName(icPerson.getName());
    					app.setInCity(icPerson.getCity());
    					app.setInDistrict(icPerson.getDistrict());
    					app.setInProvince(icPerson.getProvince());
    				}
    				if (icEnter != null) {
    					app.setInCity(icEnter.getCity());
    					app.setInDistrict(icEnter.getDistrict());
    					app.setInProvince(icEnter.getProvince());
    				}
    				app.setId(id);
    				app.setCompanyId(ic.getOrganizationId());
    				app.setAuthDate(new Date());
    				app.setCompanyName(ic.getName());
    				app.setCompanyOwner(ic.getLegalPerson());
    				app.setCompanyNature(EnterpriseConstant.Property
    						.getPropertyDesc(ic.getProperty()));
    				app.setCompanyType(EnterpriseConstant.Industry.getIndustryDesc(ic.getType()+""));
    				app.setCertificateImg(ic.getSiteImageShareUrl());
    				app.setAuthType(String.valueOf(AuditState.AUDITING));
    				app.setCompanyModel(EnterpriseConstant.EnterpriseScale.getEnterpriseScaleDesc(ic.getEnterpriseScale()));

                    app.setZhengjingjiNum(ic.getZhengjingjiNum());
                    app.setJingbianjiNum(ic.getJingbianjiNum());
                    app.setQitaNum(ic.getQitaNum());

    				// 判断是新增还是更新的信息

					System.out.println(JSON.toJSONString(app));

    				String updated = ic.getUpdated();
    				if ("F".equals(updated)) {
        				baseResult = approlvalInterface.insertApprolvalEntry(app);
					} else if ("T".equals(updated)) {
						baseResult = approlvalUpdateInterface.updateApprolvalEntry(app);
					}
    				if (baseResult.getResult()) {
    					applicationentryService.updateSended(id);
    				}
    				if (!baseResult.getResult()) {
    					throw new BusinessException(baseResult.getMessage());
    				}
    			}
    		}
    	} catch (BusinessException be) {
    		baseResult.setResult(false);
    		baseResult.setMessage(be.getMessage());
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.debug(e.getMessage());
    		baseResult.setResult(false);
    		baseResult.setMessage(e.getMessage());
    	}
    }

	/**
	 * 代理
	 *
	 * @param: @return
	 * @return: BaseResult<String>
	 * @author: 祝天洋
	 * @date: 2018-8-2 下午10:04:54
	 */
	@Scheduled(cron = "0 */1 * * * ?")
	public void insertApprolvalAgency() {
		BaseResult<IcloudApplicationagency> baseResult = new BaseResult<IcloudApplicationagency>();
		try {
			List<com.zhiyun.entity.IcloudApplicationagency> agencys = applicationagencyService.findBySended();
			if (!CommonUtils.isEmpty(agencys)) {
				for (com.zhiyun.entity.IcloudApplicationagency ic : agencys) {
					IcloudApplicationagency app = new IcloudApplicationagency();
					Long id = ic.getId();
					Long userId = ic.getUserId();
					/*IcloudPersonalauth icPerson = personalauthService
							.findByUserId(userId);
					IcloudEnterpriseauth icEnter = enterpriseauthService
							.findByUserId(userId);
					if (icPerson != null) {
						app.setUserName(icPerson.getName());
						app.setInCity(icPerson.getCity());
						app.setInDistrict(icPerson.getDistrict());
						app.setInProvince(icPerson.getProvince());
					}
					if (icEnter != null) {
						app.setInCity(icEnter.getCity());
						app.setInDistrict(icEnter.getDistrict());
						app.setInProvince(icEnter.getProvince());
					}*/
					app.setId(id);
					app.setUserId(userId);
					app.setAgencyArea(ic.getAgencyArea());
					app.setAgencyLevel(ic.getAgencyLevel());
					app.setAgencyType(ic.getAgencyType());
					app.setName(ic.getName());
					app.setQualityImageShareUrl(ic.getQualityImageShareUrl());
					app.setStatus(ic.getStatus());
					app.setCreateTime(ic.getCreateTime());
					app.setModifyBy(ic.getModifyBy());
					app.setCompanyId(ic.getOrganizationId());
					app.setAuthDate(new Date());

					// 判断是新增还是更新的信息

					System.out.println(JSON.toJSONString(app));

					String updated = ic.getUpdated();
					if ("F".equals(updated)) {
						baseResult = approlvalInterface.insertIcloudApplicationagency(app);
					} else if ("T".equals(updated)) {
						baseResult = approlvalUpdateInterface.updateIcloudApplicationagency(app);
					}
					if (baseResult.getResult()) {
						applicationagencyService.updateSended(id);
					}
					if (!baseResult.getResult()) {
						throw new BusinessException(baseResult.getMessage());
					}
				}
			}
		} catch (BusinessException be) {
			baseResult.setResult(false);
			baseResult.setMessage(be.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			baseResult.setResult(false);
			baseResult.setMessage(e.getMessage());
		}
	}

    /**
     * 个人认证
     *
     * @param: @return
     * @return: Object
     * @author: 徐飞
     * @date: 2018-6-19 上午8:53:03
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void personInsert() {
    	BaseResult<ApprolvalAuthentication> baseResult = new BaseResult<ApprolvalAuthentication>();
    	try {
    		List<IcloudPersonalauth> entrys = personalauthService.findBySended();
    		if (!CommonUtils.isEmpty(entrys)) {
    			for (IcloudPersonalauth ic : entrys) {
    				ApprolvalAuthentication app = new ApprolvalAuthentication();
    				Long id = ic.getId();
    				app.setAuthId(id);
    				app.setUserName(ic.getName());
    				app.setOwnerName(ic.getName());
    				app.setOwnerGender(String.valueOf(ic.getSex()));
    				app.setInProvince(ic.getProvince());
    				app.setInCity(ic.getCity());
    				app.setInDistrict(ic.getDistrict());
    				app.setCertificationType("个人");
    				app.setType("实名认证");
    				app.setOwnerCardtype(EnterpriseConstant.CertificateType
    						.getCertificateTypeDesc(ic.getCertificateType()));
    				app.setAuthDate(new Date());
    				app.setOwnerCardnum(ic.getCertificate());
    				app.setIdcardFrontImg(ic.getCertificatePositiveShareUrl());
    				app.setIdcardBackImg(ic.getCertificateNegativeShareUrl());
    				app.setAuthType(String.valueOf(AuditState.AUDITING));
    				app.setDetailAddress(ic.getDetailedAddress());
    				// 判断是新增还是更新的信息
    				String updated = ic.getUpdated();
    				if ("F".equals(updated)) {
        				baseResult = approlvalInterface.insertApprolvalAuthen(app);
					} else if ("T".equals(updated)) {
						baseResult = approlvalUpdateInterface.updateApprolvalAuthen(app);
					}
    				if (baseResult.getResult()) {
    					personalauthService.updateSended(id);
    				}
    				if (!baseResult.getResult()) {
    					throw new BusinessException(baseResult.getMessage());
    				}
    			}
    		}
    	} catch (BusinessException be) {
    		baseResult.setResult(false);
    		baseResult.setMessage(be.getMessage());
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.debug(e.getMessage());
    		baseResult.setResult(false);
    		baseResult.setMessage(e.getMessage());
    	}
    }

    /**
     * 企业认证
     *
     * @param: @return
     * @return: Object
     * @author: 徐飞
     * @date: 2018-6-19 上午9:39:56
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void enterInsert() {
    	BaseResult<ApprolvalAuthentication> baseResult = new BaseResult<ApprolvalAuthentication>();
    	try {
    		List<IcloudEnterpriseauth> entrys = enterpriseauthService.findBySended();
    		if (!CommonUtils.isEmpty(entrys)) {
    			for (IcloudEnterpriseauth ic : entrys) {
    				ApprolvalAuthentication app = new ApprolvalAuthentication();
    				Long id = ic.getId();
    				app.setAuthId(id);
    				app.setCompanyName(ic.getName());
    				app.setInProvince(ic.getProvince());
    				app.setInCity(ic.getCity());
    				app.setInDistrict(ic.getDistrict());
    				app.setCertificationType("企业");
    				app.setType("企业认证");
    				app.setCompanyLinkman(ic.getContactPerson());
    				app.setCompanyLinkphone(Long.valueOf(ic.getContactPhone()));
    				app.setAuthDate(new Date());
    				app.setCompanyOwner(ic.getLegalPerson());
    				app.setCompanyOwnerPhone(Long.valueOf(ic
    						.getLegalPersonPhone()));
    				app.setCompanyOwnerIdcard(Long.valueOf(ic
    						.getLegalPersonIdentityCard()));
    				app.setIdcardFrontImg(ic.getCertificatePositiveShareUrl());
    				app.setIdcardBackImg(ic.getCertificateNegativeShareUrl());
    				app.setSocialCreditCode(ic.getSocialCreditCode());
    				app.setBusinessLicenseImg(ic.getBusinessLicenceShareUrl());
    				app.setCompanyId(ic.getCompanyId());
    				app.setAuthType(String.valueOf(AuditState.AUDITING));
    				app.setDetailAddress(ic.getDetailedAddress());
    				// 判断是新增还是更新的信息
    				String updated = ic.getUpdated();
    				if ("F".equals(updated)) {
        				baseResult = approlvalInterface.insertApprolvalAuthen(app);
					} else if ("T".equals(updated)) {
						baseResult = approlvalUpdateInterface.updateApprolvalAuthen(app);
					}
    				if (baseResult.getResult()) {
    					enterpriseauthService.updateSended(id);
    				}
    				if (!baseResult.getResult()) {
    					throw new BusinessException(baseResult.getMessage());
    				}
    			}
    		}
    	} catch (BusinessException be) {
    		baseResult.setResult(false);
    		baseResult.setMessage(be.getMessage());
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.debug(e.getMessage());
    		baseResult.setResult(false);
    		baseResult.setMessage(e.getMessage());
    	}
    }

    /**
     * 入市
     *
     * @param: @return
     * @return: Object
     * @author: 徐飞
     * @date: 2018-6-19 上午10:59:03
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void marketInsert() {
    	BaseResult<ApprolvalEntryMarket> baseResult = new BaseResult<ApprolvalEntryMarket>();
    	try {
    		List<IcloudMarketentry> entrys = marketentryService.findBySended();
    		if (!CommonUtils.isEmpty(entrys)) {
    			for (IcloudMarketentry ic : entrys) {
    				ApprolvalEntryMarket app = new ApprolvalEntryMarket();
    				Long id = ic.getId();
    				Long userId = ic.getCreateUserId();
    				IcloudPersonalauth icPerson = personalauthService
    						.findByUserId(userId);
    				IcloudEnterpriseauth icEnter = enterpriseauthService
    						.findByUserId(userId);
    				List<IcloudMarketentrydatashareurl> urls = marketentryService
    						.findUrl(id);
    				if (icPerson != null) {
    					app.setUserName(icPerson.getName());
    					app.setInCity(icPerson.getCity());
    					app.setInDistrict(icPerson.getDistrict());
    					app.setInProvince(icPerson.getProvince());
    				}
    				if (icEnter != null) {
    					app.setUserName(icEnter.getName());
    					app.setInCity(icEnter.getCity());
    					app.setInDistrict(icEnter.getDistrict());
    					app.setInProvince(icEnter.getProvince());
    				}
    				app.setCompanyDescribe(ic.getEnterpriseProfile());
    				app.setWorkGroup(EnterpriseConstant.BusinessType
    						.getBusinessType(ic.getBusinessType()));

//    				app.setProductMain(EnterpriseConstant.MarketClass
//    						.getMarketClass(ic.getMarketClass()));
    				app.setProductSubclass(EnterpriseConstant.Market
    						.getMarket(ic.getMarket()));
    				app.setId(id);
    				app.setCertificateImg(JSONObject.toJSONString(urls));
    				app.setAuthDate(new Date());
    				app.setCompanyId(ic.getCompanyId());
    				app.setAuthType(String.valueOf(AuditState.AUDITING));
    				// 判断是新增还是更新的信息
    				String updated = ic.getUpdated();
    				if ("F".equals(updated)) {
        				baseResult = approlvalInterface.insertApprolvalEntryMarket(app);
					} else if ("T".equals(updated)) {
						baseResult = approlvalUpdateInterface.updateApprolvalEntryMarket(app);
					}
    				if (baseResult.getResult()) {
    					marketentryService.updateSended(id);
    				}
    				if (!baseResult.getResult()) {
    					throw new BusinessException(baseResult.getMessage());
    				}
    			}
    		}
    	} catch (BusinessException be) {
    		baseResult.setResult(false);
    		baseResult.setMessage(be.getMessage());
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.debug(e.getMessage());
    		baseResult.setResult(false);
    		baseResult.setMessage(e.getMessage());
    	}
    }

}
