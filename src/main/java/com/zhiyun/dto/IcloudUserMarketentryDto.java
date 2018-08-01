/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.dto;

import com.zhiyun.entity.IcloudUserMarketentry;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-7-4下午2:21:32
 */
public class IcloudUserMarketentryDto extends IcloudUserMarketentry {

	private static final long serialVersionUID = 5850411286563672384L;
	
	// 修改的marketentryId
	private Long newId;

	public Long getNewId() {
		return newId;
	}

	public void setNewId(Long newId) {
		this.newId = newId;
	}
	
	

}
