/*
* 嘉兴飞戎智云软件有限公司版权所有
* Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
*/

package com.zhiyun.constants;

/**
 * AuthorizationState
 *
 * @author 孙云涛
 * @version v1.0
 * @date 2018年6月7日 下午4:39:55
 */

public class AuditState {

	// 没有审核过
	public static int UNDOAUDIT = 0;
	// 审核中
	public static int AUDITING = 1;

	// 审核通过
	public static int AUDITED = 2;

	// 审核未通过
	public static int UNAUDITED = 3;

	public static String getStateDesc(int state) {
		if (state == UNDOAUDIT) {
			return "未提交审核";
		} else if (state == AUDITING) {
			return "审核中";
		} else if (state == AUDITED) {
			return "审核完成";
		} else if (state == UNAUDITED) {
			return "审核未通过";
		}
		return "未提交审核";
	}


}
