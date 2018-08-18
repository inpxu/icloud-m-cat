/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.UserDao;
import com.zhiyun.entity.User;
import com.zhiyun.liferay.model.FriendlyUrl;

/**
 * UserDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

	@Override
	public User findUserStatus(String screenName) {
		Params params = Params.create().add("screenName", screenName);
		return this.selectOne(getMethodName(), params);
	}

	@Override
	public User findUserByScreenName(String screenName) {
		Params params = Params.create().add("screenName", screenName);
		return this.selectOne(getMethodName(), params);
	}

	@Override
	public User findUserByUserId(Long userId) {
		Params params = Params.create().add("userId", userId);
		return this.selectOne(getMethodName(), params);
	}

	@Override
	public User findUserByScreenNameAndEmail(String account) {
		Params params = Params.create().add("account", account);
		return this.selectOne(getMethodName(), params);
	}

	@Override
	public User findByUserId(Long userId) {
		Params params = Params.create().add("userId", userId);
		return this.selectOne(getMethodName(), params);
	}

	@Override
	public int updatePhone(User user) {
		return this.update(getMethodName(), user);
	}

	@Override
	public int updateUrl(FriendlyUrl friendlyUrl) {
		return this.update(getMethodName(),friendlyUrl);
	}

	@Override
    public void updateUserByUserId(User user){
	    this.update(getMethodName(),user);
    }

	@Override
	public Long getUserIdByPhone(String screenname){
		return this.selectOne(getMethodName(),screenname);
	}
}
