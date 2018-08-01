/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.dto;

import com.zhiyun.entity.User;

/**
 * @author xufei
 * @version v1.0
 * @date 2018-6-11下午1:31:44
 */
public class UserDto extends User {

	private static final long serialVersionUID = 6931791990259539380L;
	
	private String phone;
	private String safeName;
	private Integer safeNum;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSafeName() {
		return safeName;
	}
	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}
	public Integer getSafeNum() {
		return safeNum;
	}
	public void setSafeNum(Integer safeNum) {
		this.safeNum = safeNum;
	}
	

}
