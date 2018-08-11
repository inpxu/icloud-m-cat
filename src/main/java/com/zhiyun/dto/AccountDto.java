package com.zhiyun.dto;

import java.util.List;

/**
 * 账户信息
 * @Auther: sunyuntao
 * @Date: 2018/08/11 10:34
 * @Description:
 */
public class AccountDto {

    private Long userId;

    private String username;

    private String emailAddress;

    private String phone;

    // 0 未认证 1 认证中 2 已认证 3 认证失败
    private int authState;

    // 0 未认证 1 个人认证 2 企业认证
    private int authType;

    private String avatar;

    private List<String> roleNames;

    // 密保等级
    private Integer pwdRank;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAuthState() {
        return authState;
    }

    public void setAuthState(int authState) {
        this.authState = authState;
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Integer getPwdRank() {
        return pwdRank;
    }

    public void setPwdRank(Integer pwdRank) {
        this.pwdRank = pwdRank;
    }
}
