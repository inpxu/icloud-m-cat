/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.util;

import java.util.Random;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-11下午6:48:09
 */
public class MessageCodeUtil {

	 private final static Random random  = new Random(System.currentTimeMillis());

	    private final static int MAX = 1000_000;

	    private final static int MIN = 100_000;

	    public static String generateMsgCode(){
	        int code = random.nextInt(MAX) % (MAX - MIN) + MIN;
	        return String.valueOf(code);
	    }
}
