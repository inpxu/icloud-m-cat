package com.zhiyun.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: sunyuntao
 * @Date: 2018/07/11 09:39
 * @Description:
 */
public class EmailAddressConventer {

    private static final String EMAIL_ADDRESS_SUFFIX = "@jb.icloud";

    public static String convert(String emailAddress,String phone){
        if(StringUtils.isNotEmpty(emailAddress)){
            return emailAddress;
        }

        return phone + EMAIL_ADDRESS_SUFFIX;
    }

}
