/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.User;
import com.zhiyun.liferay.model.FriendlyUrl;

/**
 * UserDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface UserDao extends BaseDao<User, Long> {
    User findUserStatus(String screenName);

    User findUserByScreenName(String screenName);

    User findUserByUserId(Long userId);

    String findPhoneByUserId(Long userId);

    User findUserByScreenNameAndEmail(String accout);

    User findByUserId(Long userId);
    
    // 修改手机号码
    int updatePhone(User user);
    
    int updateUrl(FriendlyUrl friendlyUrl);

    void updateUserByUserId(User user);
    // 通过手机号查询userid
    Long getUserIdByPhone(String screenname);
}
