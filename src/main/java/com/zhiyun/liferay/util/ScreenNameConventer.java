package com.zhiyun.liferay.util;

import org.apache.commons.lang3.StringUtils;

public class ScreenNameConventer {

	private static final String P_SYMBOL = "p";

	public static String screenNamePlusP(String name) {

		if (!StringUtils.isNotEmpty(name) || name.indexOf("@") > 0) {
			return name;
		}

		return P_SYMBOL + name;
	}

	public static String screenNameMinusP(String name) {

		if (!StringUtils.isNotEmpty(name) || name.indexOf("@") > 0 || name.indexOf(P_SYMBOL) < 0) {
			return name;
		}

		return name.substring(P_SYMBOL.length(), name.length());
	}

	public static void main(String[] args) {
		System.out.println(screenNamePlusP("457196697@qq.com"));
		System.out.println(screenNamePlusP(""));
		System.out.println(screenNamePlusP("13582202525"));
		System.out.println(screenNameMinusP("457196697@qq.com"));
		System.out.println(screenNameMinusP(""));
		System.out.println(screenNameMinusP("P13582202525"));

	}

}
