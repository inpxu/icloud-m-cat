/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.BaseUserInfoDto;
import com.zhiyun.dto.EnterpriseAuthDto;
import com.zhiyun.dto.UserDto;
import com.zhiyun.dto.UserStateDto;
import com.zhiyun.entity.User;
import com.zhiyun.liferay.model.FriendlyUrl;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import com.zhiyun.liferay.model.UpdatePassword;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface UserService extends BaseService<User, Long> {

	User findUserByScreenNameAndEmail(String accout);

	UserStateDto findUserStateDtoByScreenName(String screenName);

	boolean isAuthorized(User user);

	//通过userid查找安全信息
	UserDto findByUserId(Long userId);

	// 通过userid修改screenName
	int updateScreenname(Long userid, String phone);
	
	// 通过 phone查找客户信息
	User findByPhone(String phone);
	
	// 修改客户密码
	LiferayInvokerResult updatePassword(UpdatePassword updatePassword);
	
	int updateUrl(String phone);

	void dispatchUser(Long userId);

	BaseUserInfoDto getBaseUserInfoDto(Long userId);

	void updateAvatar(Long userId, String avatar);

    EnterpriseAuthDto getEnterpriseAuth(Long userId);

}
	