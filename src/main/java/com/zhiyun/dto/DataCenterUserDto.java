/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

/**
 * DataCenterUserDto
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月10日 下午12:38:52 
 */

public class DataCenterUserDto {

	private String companyName;

	private long companyId;

	private long userId;

	private String phone;

	private String name;

	private String email;

	private String companyType;

	private String account;

	public DataCenterUserDto(String companyName, long companyId, long userId, String phone, String name, String email,
			String companyType, String account) {
		this.companyName = companyName;
		this.companyId = companyId;
		this.userId = userId;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.companyType = companyType;
		this.account = account;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
