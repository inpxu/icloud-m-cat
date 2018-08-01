/**
 *  嘉兴飞戎智云软件有限公司版权所有
 *  Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.liferay.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author 徐飞
 * @version v1.0
 * @date 2018-6-11下午6:52:57
 */
public class Message {

    private static final String DEFALUT_TEMPLATE_ID = "1";

    private List<String> phoneList;

    private String templateId ;

    private DefalutMessage message;

    public Message(String phone, String code) {
        phoneList = Arrays.asList(phone);
        templateId = DEFALUT_TEMPLATE_ID;
        message = new DefalutMessage(phone, code);
    }

    public static String getDefalutTemplateId() {
        return DEFALUT_TEMPLATE_ID;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public DefalutMessage getMessage() {
        return message;
    }

    public void setMessage(DefalutMessage message) {
        this.message = message;
    }

    public static class DefalutMessage {

        private String userName;

        private String verifyCode;

        public DefalutMessage(String userName, String verifyCode) {
            this.userName = userName;
            this.verifyCode = verifyCode;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }
    }

}
