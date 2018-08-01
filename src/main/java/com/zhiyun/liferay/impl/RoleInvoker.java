package com.zhiyun.liferay.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhiyun.liferay.BaseLiferayInvoker;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import org.springframework.stereotype.Repository;

/**
 * @Auther: sunyuntao
 * @Date: 2018/07/04 15:42
 * @Description:
 */
@Repository
public class RoleInvoker extends BaseLiferayInvoker {

    private static final String MODULE_PATH = "role";

    private static final String DELETE_ROLE_USER = "delete-role-user";

    private static final String GET_USER_ROLES = "get-user-roles";

    private static final String ADD_USER_ROLES = "add-user-roles";

    private static final String UNSET_USER_ROLES = "unset-user-roles";

    @Override
    public String getSubPath() {
        return MODULE_PATH;
    }

    public LiferayInvokerResult deleteRoleUser(Long userId,Long roleId) {

        JSONObject params = new JSONObject();
        params.put("userId", userId);
        params.put("roleId", roleId);

        LiferayInvokerResult result = invoke(DELETE_ROLE_USER, params);

        return result;
    }

    public LiferayInvokerResult getUserRoles(Long userId) {

        JSONObject params = new JSONObject();
        params.put("userId", userId);

        LiferayInvokerResult result = invoke(GET_USER_ROLES, params);

        return result;
    }

    public LiferayInvokerResult addUserRoles(Long userId,Long[] roleIds) {

        JSONObject params = new JSONObject();
        params.put("userId", userId);
        params.put("roleIds", roleIds);

        LiferayInvokerResult result = invoke(ADD_USER_ROLES, params);

        return result;
    }

    public LiferayInvokerResult unsetUserRoles(Long userId,Long[] roleIds) {

        JSONObject params = new JSONObject();
        params.put("userId", userId);
        params.put("roleIds", roleIds);

        LiferayInvokerResult result = invoke(UNSET_USER_ROLES, params);

        return result;
    }


}
