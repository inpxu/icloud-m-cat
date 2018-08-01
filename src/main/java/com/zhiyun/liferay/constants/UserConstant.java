package com.zhiyun.liferay.constants;

/**
 * 
 * UserConstant
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 上午9:20:21
 */
public interface UserConstant {

	// liferay company table
	Long COMPANYID = 20115L;

	boolean AUTOPASSWORD = false;

	String PASSWORD = "123456";

	boolean AUTOSCREENNAME = false;

	String LOCALE = "zh_CN";

	String MIDDLENAME = "欢迎";

	String LASTNAME = "欢迎";

	int PREFIXID = 0;

	int SUFFIXID = 0;

	boolean MALE = true;

	int BIRTHDAYMONTH = 1;

	int BIRTHDAYDAY = 1;

	int BIRTHDAYYEAR = 1970;

	long[] GROUPIDS = new long[] { 20142L };

	long ADMINISTRATOR_ROLE_ID = 20121L;

	long GUEST_ROLE_ID = 20122L;

	//todo 修改 普通用户
	long IC_ORDINARY_USER_ROLE_ID = 223738L;

	//todo
	long IC_RELATED_ENTERPRISE_USER_ROLE_ID = 223877L;

	//todo
	long IC_FACILITATOR_USER_ROLE_ID = 223735L;

	//todo
	long IC_ADVANCED_USER_ROLE_ID= 223737L;

	//todo
	long IC_VIP_USER_ID = 223736L;

	long OWNER_ROLE_ID = 20123L;

	long POWER_USER_ROLE_ID = 20124L;

	long USER_ROLE_ID = 20125L;

	long[] USERGROUPIDS = new long[] {};

	boolean SENDEMAIL = false;

	String JOBTITLE = "";

	long[] ADMINROLEIDS = new long[] { 20124L };

	Long FACEBOOK_ID = 0L;

	String OPEN_ID = "";

}
