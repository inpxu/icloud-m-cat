/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;
import java.util.*;


import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.zhiyun.constants.RoleEnum;
import com.zhiyun.dao.*;
import com.zhiyun.dto.*;
import com.zhiyun.entity.*;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import com.zhiyun.liferay.util.ScreenNameConventer;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.AuthType;
import com.zhiyun.liferay.impl.UserInvoker;
import com.zhiyun.liferay.model.FriendlyUrl;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import com.zhiyun.liferay.model.UpdatePassword;
import com.zhiyun.service.IcloudApplicationentryService;
import com.zhiyun.service.UserService;



/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	public static String DEFAULT_FORMAT = "yyyy-MM-dd";

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	}

	@Resource
	private UserDao userDao;

	@Resource
	private IcloudPersonalauthDao personalauthDao;

	@Resource
	private IcloudEnterpriseauthDao enterpriseauthDao;

	@Autowired
	private IcloudApplicationentryService icloudApplicationentryService;

	@Autowired
	private IcloudApplicationentryDao icloudApplicationentryDao;

	@Autowired
	private InterfaceForUser interfaceForUser;

	@Resource
	private UserInvoker userInvoker;

	@Resource
    private RoleDao roleDao;

	@Override
	protected BaseDao<User, Long> getBaseDao() {
		return this.userDao;
	}

	@Override
	public User findUserByScreenNameAndEmail(String accout) {
		return userDao.findUserByScreenNameAndEmail(accout);
	}

	public static synchronized String defaultFormat(Date date) {
		long rightTime = (long) (date.getTime() + 8 * 60 * 60 * 1000);
		return sdf.format(rightTime);
	}

	@Override
	public UserStateDto findUserStateDtoByScreenName(String screenName) {

		User user = new User();
		UserStateDto userDto = new UserStateDto();
		user = userDao.findUserStatus(screenName);
		userDto.setName(user.getLastname() + user.getFirstname());

		userDto.setLastLoginDate(defaultFormat(user.getLastlogindate()));

		if (user.getIcloudEnterpriseauth() != null && user.getIcloudEnterpriseauth().getStatus() != null) {
			userDto.setAuthStatus(user.getIcloudEnterpriseauth().getStatus());
			userDto.setAuthType(AuthType.ENTERPRISE_AUTHORIZED_TYPE);
		} else if (user.getIcloudPersonalauth() != null && user.getIcloudPersonalauth().getStatus() != null) {
			userDto.setAuthStatus(user.getIcloudPersonalauth().getStatus());
			userDto.setAuthType(AuthType.PERSONAL_AUTHORIZED_TYPE);
		} else {
			userDto.setAuthStatus(AuthType.UN_AUTHORIZED_TYPE);
			userDto.setAuthType(AuditState.UNDOAUDIT);
		}

		return userDto;
	}

	@Override
	public boolean isAuthorized(User user) {

		user = userDao.findUserStatus(user.getScreenname());

		if (user.getIcloudEnterpriseauth() == null && user.getIcloudPersonalauth() == null) {
			return false;
		}

		if (user.getIcloudEnterpriseauth().getStatus() == AuditState.AUDITED
				|| user.getIcloudPersonalauth().getStatus() == AuditState.AUDITED) {
			return true;
		}
		return false;
	}

	@Override
	public UserDto findByUserId(Long userId) {
		User user = userDao.findByUserId(userId);
		UserDto ud = new UserDto();
		BeanUtils.copyProperties(user, ud);

		ud.setPhone(user.getScreenname().replaceAll("p", ""));
		// ud.setPhone(user.getScreenname().replaceAll("[a-zA-Z]",""));
		if (user.getEmailaddressverified() == true) {
			ud.setSafeName("高");
			ud.setSafeNum(6);
		} else {
			ud.setSafeName("中");
			ud.setSafeNum(4);
		}
		return ud;
	}

	@Override
	public int updateScreenname(Long userid, String phone) {
		String screenname = "p" + phone;
		User user = new User();
		user.setScreenname(screenname);
		user.setUserid(userid);
		String companyName = UserHolder.getCompanyName();
		Long companyId = UserHolder.getCompanyId();
		String email = userDao.findByUserId(userid).getEmailaddress();
		String name = userDao.findByUserId(userid).getLastname() + userDao.findByUserId(userid).getFirstname();
		if (UserHolder.getCompanyId() != null && companyName != null) {
			icloudApplicationentryService.updateUserInDataCenter(companyName, companyId, userid, screenname, name,
					email);
		}
		return userDao.updatePhone(user);
	}

	@Override
	public User findByPhone(String phone) {
		String screenname = "p" + phone;
		User user = userDao.findUserByScreenName(screenname);
		return user;
	}

	@Override
	public LiferayInvokerResult updatePassword(UpdatePassword updatePassword) {
		Long userId = UserHolder.getId();
		String password1 = updatePassword.getPassword1();
		String password2 = updatePassword.getPassword2();
		return userInvoker.updatePassword(userId, password1, password2);
	}

	@Override
	public int updateUrl(String phone) {
		String formerUrl = "/" + userDao.findByUserId(UserHolder.getId()).getScreenname();
		String newUrl = "/p" + phone;
		FriendlyUrl friendlyUrl = new FriendlyUrl();
		friendlyUrl.setFormerUrl(formerUrl);
		friendlyUrl.setNewUrl(newUrl);
		return userDao.updateUrl(friendlyUrl);
	}

	//FIX ME
	@Override
	public void dispatchUser(Long userId) {

		IcloudApplicationentry iae = icloudApplicationentryDao.findByUserId(userId);


		com.zhiyun.internal.server.auth.entity.CasCompany casCompany = new com.zhiyun.internal.server.auth.entity.CasCompany();
		casCompany.setCompanyName(iae.getName());
		casCompany.setCompanyId(iae.getOrganizationId());
		casCompany.setId(iae.getOrganizationId());
		User user = userDao.findByUserId(iae.getCreateUserId());
		com.zhiyun.internal.server.auth.entity.CasUser casUser = new com.zhiyun.internal.server.auth.entity.CasUser();
		casUser.setAccount(user.getScreenname());
		casUser.setEmail(user.getEmailaddress());
		casUser.setName(user.getLastname() + user.getFirstname());
		casUser.setId(user.getId());
		casUser.setPhone(ScreenNameConventer.screenNameMinusP(user.getScreenname()));
		casUser.setId(user.getUserid());
		casUser.setIsAble(true);

		try {
			interfaceForUser.insertOrUpdateUser(casUser,casCompany);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

    @Override
    public BaseUserInfoDto getBaseUserInfoDto(Long userId) {

	    BaseUserInfoDto baseUserInfoDto = new BaseUserInfoDto();

        IcloudPersonalauth icloudPersonalauth = personalauthDao.findByUserId(userId);
        IcloudEnterpriseauth icloudEnterpriseauth = enterpriseauthDao.findByUserId(userId);

        AccountDto accountDto = getAccountDto(userId,icloudPersonalauth,icloudEnterpriseauth);
        PersonalAuthDto personalAuthDto = getPersonalAuthDto(icloudPersonalauth);
        EnterpriseAuthDto enterpriseAuthDto = getEnterpriseAuthDto(icloudEnterpriseauth);
        LegalPersonDto legalPersonDto = getLegalPersonDto(icloudEnterpriseauth);

        baseUserInfoDto.setAccount(accountDto);
        baseUserInfoDto.setPersonalAuth(personalAuthDto);
        baseUserInfoDto.setEnterpriseAuth(enterpriseAuthDto);
        baseUserInfoDto.setLegalPerson(legalPersonDto);

        return baseUserInfoDto;
    }

    public AccountDto getAccountDto(Long userId , IcloudPersonalauth icloudPersonalauth,IcloudEnterpriseauth icloudEnterpriseauth){

	    AccountDto accountDto = new AccountDto();

        if(icloudPersonalauth != null ) {
            accountDto.setAuthType(AuthType.PERSONAL_AUTHORIZED_TYPE);
            accountDto.setAuthState(icloudPersonalauth.getStatus());
        }else if(icloudEnterpriseauth != null ){
            accountDto.setAuthType(AuthType.ENTERPRISE_AUTHORIZED_TYPE);
            accountDto.setAuthState(icloudEnterpriseauth.getStatus());
        }else {
            accountDto.setAuthType(AuthType.UN_AUTHORIZED_TYPE);
            accountDto.setAuthState(AuditState.UNDOAUDIT);
        }

        User user = userDao.findByUserId(userId);

        accountDto.setAvatar(user.getAvatar());
        accountDto.setEmailAddress(user.getEmailaddress());
        accountDto.setPwdRank(user.getPwdrank());
        accountDto.setUserId(userId);
        accountDto.setUsername(user.getLastname()+user.getFirstname());
        accountDto.setPhone(ScreenNameConventer.screenNameMinusP(user.getScreenname()));

        List<Role> roles = roleDao.findByUserId(userId);
        if(CollectionUtils.isNotEmpty(roles)){
            accountDto.setRoleNames(roles.stream().filter(role -> {
                return RoleEnum.traslateRoleName(role.getName()) != null;
            }).map(role -> {
                return RoleEnum.traslateRoleName(role.getName());
            }).collect(Collectors.toList()));
        }

	    return accountDto;
    }

    public PersonalAuthDto getPersonalAuthDto(IcloudPersonalauth icloudPersonalauth){

        PersonalAuthDto personalAuthDto = null;

        if(icloudPersonalauth != null ) {
            personalAuthDto = new PersonalAuthDto();

            personalAuthDto.setUpdated(icloudPersonalauth.getUpdated());
            personalAuthDto.setApprovalOpinion(icloudPersonalauth.getApprovalOpinion());
            personalAuthDto.setId(icloudPersonalauth.getId());
            personalAuthDto.setUserId(icloudPersonalauth.getUserId());
            personalAuthDto.setName(icloudPersonalauth.getName());
            personalAuthDto.setCertificateType(icloudPersonalauth.getCertificateType());
            personalAuthDto.setCertificateTypeDesc(
                    icloudPersonalauth.getCertificateType() == 0 ? "身份证" :
                            (icloudPersonalauth.getCertificateType() == 1 ? "护照" : "港澳台通行证"));
            personalAuthDto.setCertificate(icloudPersonalauth.getCertificate());
            personalAuthDto.setSex(icloudPersonalauth.getSex());
            personalAuthDto.setSexDesc(icloudPersonalauth.getSex() == 0 ? "女" : "男");
            personalAuthDto.setProvince(icloudPersonalauth.getProvince());
            personalAuthDto.setCity(icloudPersonalauth.getCity());
            personalAuthDto.setDistrict(icloudPersonalauth.getDistrict());
            personalAuthDto.setDetailedAddress(icloudPersonalauth.getDetailedAddress());
            personalAuthDto.setCertificatePositiveShareUrl(icloudPersonalauth.getCertificatePositiveShareUrl());
            personalAuthDto.setCertificateNegativeShareUrl(icloudPersonalauth.getCertificateNegativeShareUrl());
            personalAuthDto.setStatus(icloudPersonalauth.getStatus());

        }

	    return personalAuthDto;
    }

    public EnterpriseAuthDto getEnterpriseAuthDto(IcloudEnterpriseauth icloudEnterpriseauth){

        EnterpriseAuthDto enterpriseAuthDto = null;

        if(icloudEnterpriseauth != null ){
            enterpriseAuthDto = new EnterpriseAuthDto();
            BeanUtils.copyProperties(icloudEnterpriseauth,enterpriseAuthDto);
        }

	    return enterpriseAuthDto;
    }

    public LegalPersonDto getLegalPersonDto(IcloudEnterpriseauth icloudEnterpriseauth){

        LegalPersonDto legalPersonDto = null;

        if(icloudEnterpriseauth != null ) {
            legalPersonDto = new LegalPersonDto();

            legalPersonDto.setUserId(icloudEnterpriseauth.getUserId());
            legalPersonDto.setName(icloudEnterpriseauth.getName());
            legalPersonDto.setIdentityCard(icloudEnterpriseauth.getLegalPersonIdentityCard());
            legalPersonDto.setCertificatePositiveShareUrl(icloudEnterpriseauth.getCertificatePositiveShareUrl());
            legalPersonDto.setCertificateNegativeShareUrl(icloudEnterpriseauth.getCertificateNegativeShareUrl());

        }

        return legalPersonDto;
    }


    @Override
    public void updateAvatar(Long userId, String avatar) {
        User user = userDao.findUserByUserId(userId);
        user.setAvatar(avatar);
        userDao.updateUserByUserId(user);
    }

    @Override
    public EnterpriseAuthDto getEnterpriseAuth(Long userId) {

        IcloudEnterpriseauth icloudEnterpriseauth = enterpriseauthDao.findByUserId(userId);
        EnterpriseAuthDto enterpriseAuthDto = getEnterpriseAuthDto(icloudEnterpriseauth);

        return enterpriseAuthDto;

    }
}
