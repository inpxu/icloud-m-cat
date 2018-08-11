/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.model.Params;
import org.springframework.stereotype.Repository;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.RoleDao;
import com.zhiyun.entity.Role;

import java.util.List;

/**
 * RoleDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

    public List<Role> findByUserId(Long userId){
        return this.selectList(getMethodName(),Params.create().add("userId",userId));
    }

}
