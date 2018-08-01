/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Resource;

import com.zhiyun.dao.IcloudApplicationentryDao;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import com.zhiyun.liferay.util.ScreenNameConventer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.constants.AuthType;
import com.zhiyun.dao.IcloudEnterpriseauthDao;
import com.zhiyun.dao.IcloudPersonalauthDao;
import com.zhiyun.dao.UserDao;
import com.zhiyun.dto.UserDto;
import com.zhiyun.dto.UserStateDto;
import com.zhiyun.entity.IcloudApplicationentry;
import com.zhiyun.entity.User;
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


}
