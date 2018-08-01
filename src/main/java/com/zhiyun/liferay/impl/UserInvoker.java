package com.zhiyun.liferay.impl;

import com.zhiyun.internal.server.icloud.entity.IcloudUser;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.zhiyun.liferay.BaseLiferayInvoker;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.model.LiferayInvokerResult;

import java.util.ArrayList;

/**
 * 
 * UserInvoker
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 上午9:11:54
 */
@Repository
public class UserInvoker extends BaseLiferayInvoker {

	private static final String MODULE_PATH = "user";

	private static final String GET_USER_BY_SCREEN_NAME_METHOD = "get-user-by-screen-name";

	private static final String UPDATE_ORGANIZATIONS = "update-organizations";
	
	private static final String UPDATE_PASSWORD = "update-password";

	private static final String ADD_USER_METHOD = "add-user";

	private static final String UPDATE_USER = "update-user";

	private static final String UPDATE_STATUS = "update-status";

	private static final String ADD_ROLE_USERS = "add-role-users";

	private static final String UNSET_ROLE_USERS = "unset-role-users";

	private static final String GET_USER_ID_BY_EMAIL_ADDRESS = "get-user-id-by-email-address";

	private static final String UPDATE_REMINDER_QUERY = "update-reminder-query";

	private static final String UNSET_ORGANIZATION_USERS = "unset-organization-users";

	private static final String GET_USER_BY_ID = "get-user-by-id";

	@Override
	public String getSubPath() {
		return MODULE_PATH;
	}

	public LiferayInvokerResult unsetOrganizationUsers(Long organizationId,Long[] userIds){

		JSONObject params = new JSONObject();
		params.put("organizationId", organizationId);
		params.put("userIds", userIds);

		LiferayInvokerResult result = invoke(UNSET_ORGANIZATION_USERS, params);

		return result;

	}

	public LiferayInvokerResult updateReminderQuery(Long userId,String question,String answer){

		JSONObject params = new JSONObject();
		params.put("userId", userId);
		params.put("question", question);
		params.put("answer", answer);

		LiferayInvokerResult result = invoke(UPDATE_REMINDER_QUERY, params);

		return result;

	}

	public LiferayInvokerResult getUserByScreenName(String screenName) {

		JSONObject params = new JSONObject();
		params.put("companyId", UserConstant.COMPANYID);
		params.put("screenName", screenName);

		LiferayInvokerResult result = invoke(GET_USER_BY_SCREEN_NAME_METHOD, params);

		return result;
	}

	public LiferayInvokerResult getUserIdByEmailAddress(String emailAddress) {

		JSONObject params = new JSONObject();
		params.put("companyId", UserConstant.COMPANYID);
		params.put("emailAddress", emailAddress);

		LiferayInvokerResult result = invoke(GET_USER_ID_BY_EMAIL_ADDRESS, params);

		return result;
	}

	public LiferayInvokerResult updateOrganizations(long userId, long[] organizationIds) {

		JSONObject params = new JSONObject();
		params.put("userId", userId);
		params.put("organizationIds", organizationIds);

		LiferayInvokerResult result = invoke(UPDATE_ORGANIZATIONS, params);

		return result;
	}
	
	public LiferayInvokerResult updatePassword(Long userId, String password1, String password2) {
		JSONObject params = new JSONObject();
		params.put("userId", userId);
		params.put("password1", password1);
		params.put("password2", password2);
		params.put("passwordReset", Boolean.FALSE);
		LiferayInvokerResult result = invoke(UPDATE_PASSWORD, params);
		return result;
	}

	public LiferayInvokerResult addUser(long[] orgIds, String screenName, String emailAddress, String firstName, String password,
										long[] roleIds){
		JSONObject params = new JSONObject();

		String lastName = UserConstant.MIDDLENAME;
		if (firstName != null && firstName.length() >= 2) {
			lastName = firstName.substring(0, 1);
			firstName = firstName.substring(1);
		}

		params.put("companyId", UserConstant.COMPANYID);
		params.put("autoPassword", UserConstant.AUTOPASSWORD);
		params.put("password1", password == null ? UserConstant.PASSWORD : password);
		params.put("password2", password == null ? UserConstant.PASSWORD : password);
		params.put("autoScreenName", UserConstant.AUTOSCREENNAME);
		params.put("screenName", screenName);
		params.put("emailAddress", emailAddress);
		params.put("facebookId", UserConstant.FACEBOOK_ID);
		params.put("openId", UserConstant.OPEN_ID);
		params.put("locale", UserConstant.LOCALE);
		params.put("firstName", firstName);
		params.put("middleName", UserConstant.MIDDLENAME);
		params.put("lastName", lastName);
		params.put("prefixId", UserConstant.PREFIXID);
		params.put("suffixId", UserConstant.SUFFIXID);
		params.put("male", UserConstant.MALE);
		params.put("birthdayMonth", UserConstant.BIRTHDAYMONTH);
		params.put("birthdayDay", UserConstant.BIRTHDAYDAY);
		params.put("birthdayYear", UserConstant.BIRTHDAYYEAR);
		params.put("jobTitle", UserConstant.JOBTITLE);
		params.put("groupIds", UserConstant.GROUPIDS);
		params.put("organizationIds", orgIds == null ? new long[0] : orgIds);
		params.put("roleIds", roleIds == null ? new long[0] : roleIds);
		params.put("userGroupIds", UserConstant.USERGROUPIDS);
		params.put("sendEmail", UserConstant.SENDEMAIL);

		LiferayInvokerResult result = invoke(ADD_USER_METHOD,params);

		return result;
	}

	public LiferayInvokerResult updateUser(String screenName, String emailAddress, String firstName,long [] organizationIds,
								   long[] roleIds,Long userId) {

		JSONObject params = new JSONObject();

		String lastName = UserConstant.MIDDLENAME;
		if (firstName != null && firstName.length() >= 2) {
			lastName = firstName.substring(0, 1);
			firstName = firstName.substring(1);
		}

		params.put("userId", userId);
		params.put("oldPassword", "");
		params.put("newPassword1", "");
		params.put("newPassword2", "");
		params.put("passwordReset", Boolean.FALSE);
		params.put("reminderQueryQuestion", System.currentTimeMillis());
		params.put("reminderQueryAnswer", System.currentTimeMillis());
		params.put("screenName", screenName);
		params.put("emailAddress", emailAddress);
		params.put("facebookId",  0L);
		params.put("openId",  "");
		params.put("portrait",  Boolean.TRUE);
		params.put("portraitBytes",  new byte[]{});
		params.put("languageId",  "zh_CN");
		params.put("timeZoneId", "UTC");
		params.put("greeting", "Welcome "+lastName +" 欢迎 "+firstName +"!");
		params.put("comments", "");
		params.put("firstName", firstName);
		params.put("middleName", UserConstant.MIDDLENAME);
		params.put("lastName", lastName);
		params.put("prefixId", UserConstant.PREFIXID);
		params.put("suffixId", UserConstant.SUFFIXID);
		params.put("male", UserConstant.MALE);
		params.put("birthdayMonth", UserConstant.BIRTHDAYMONTH);
		params.put("birthdayDay", UserConstant.BIRTHDAYDAY);
		params.put("birthdayYear", UserConstant.BIRTHDAYYEAR);
		params.put("smsSn", "");
		params.put("facebookSn", "");
		params.put("jabberSn", "");
		params.put("skypeSn", "");
		params.put("twitterSn", "");
		params.put("jobTitle", "");
		params.put("groupIds", new long[0]);
		params.put("organizationIds", organizationIds);
		params.put("roleIds",roleIds == null ? new long[0] : roleIds);
		params.put("userGroupRoles", new ArrayList<>());
		params.put("userGroupIds", new long[0]);
		params.put("addresses", new ArrayList<>());
		params.put("emailAddresses", new ArrayList<>());
		params.put("phones", new ArrayList<>());
		params.put("websites", new ArrayList<>());
		params.put("announcementsDelivers",new ArrayList<>());
		params.put("serviceContext", null);

		LiferayInvokerResult result = invoke(UPDATE_USER,params);


		return result;

	}

	public LiferayInvokerResult updateStatus(Long userId,Long status) {

		JSONObject params = new JSONObject();
		params.put("userId", userId);
		params.put("status", status);

		LiferayInvokerResult result = invoke(UPDATE_STATUS, params);

		return result;
	}

	public LiferayInvokerResult addRoleUsers(Long roleId,Long[] userIds) {

		JSONObject params = new JSONObject();
		params.put("roleId", roleId);
		params.put("userIds", userIds);

		LiferayInvokerResult result = invoke(ADD_ROLE_USERS, params);

		return result;
	}

	public LiferayInvokerResult unsetRoleUsers(Long roleId,Long[] userIds) {

		JSONObject params = new JSONObject();
		params.put("roleId", roleId);
		params.put("userIds", userIds);

		LiferayInvokerResult result = invoke(UNSET_ROLE_USERS, params);

		return result;
	}

	public LiferayInvokerResult getUserById(Long userId) {

		JSONObject params = new JSONObject();
		params.put("userId", userId);

		LiferayInvokerResult result = invoke(GET_USER_BY_ID, params);

		return result;
	}

}
