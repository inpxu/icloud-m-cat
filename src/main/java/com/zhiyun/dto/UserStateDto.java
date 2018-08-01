/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

/**
 * UserDto
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 下午4:08:02
 */

public class UserStateDto {

	private String name;

	private String lastLoginDate;

	private int authStatus;

	private int authType;

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}

}
