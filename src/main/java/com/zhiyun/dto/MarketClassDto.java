/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.dto;

/**
 * MarketClass
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月11日 下午2:05:47 
 */

public class MarketClassDto {

	private int id;

	private String name;

	public MarketClassDto(int id, String name) {
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
