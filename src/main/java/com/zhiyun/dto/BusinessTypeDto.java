/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

/**
 * BusinessType
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月11日 下午2:04:45 
 */

public class BusinessTypeDto {

	private int id;

	private String name;

	public BusinessTypeDto(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
