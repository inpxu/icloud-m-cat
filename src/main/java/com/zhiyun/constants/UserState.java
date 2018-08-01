/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.constants;

/**
 * UserState
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月8日 下午5:35:22 
 */

public interface UserState {

	/**
	 * 未认证
	 */
	int UN_AUTHORIZED_STATE = 0;

	/**
	 * 个人认证
	 */
	int PERSONAL_AUTHORIZED_STATE = 1;

	/**
	 * 企业认证
	 */
	int ENTERPRISE_AUTHORIZED_STATE = 2;

	/**
	 * 入驻
	 */
	int APPLICATION_ENTRY_STATE = 3;

	/**
	 * 入市
	 */
	int MARKET_ENTRY_STATE = 4;

}
