/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 4311658539556532131L;

    // ~~~~实体属性
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long mvccversion;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String uuid;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long userid;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long companyid;
    //
    private java.util.Date createdate;
    //
    private java.util.Date modifieddate;
    //
    private boolean defaultuser;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long contactid;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String password;
    //
    private boolean passwordencrypted;
    //
    private boolean passwordreset;
    //
    private java.util.Date passwordmodifieddate;
    //
    @Pattern(regexp = "[\\S]{0,255}", message = "字段过长")
    private String digest;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String reminderqueryquestion;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String reminderqueryanswer;
    //
    @Max(value = 99999999999L, message = "字段过长")
    private Integer gracelogincount;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String screenname;
    //
    @Pattern(regexp = "[\\S]{0,254}", message = "字段过长")
    private String emailaddress;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long facebookid;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String googleuserid;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long ldapserverid;
    //
    @Pattern(regexp = "[\\S]{0,1024}", message = "字段过长")
    private String openid;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long portraitid;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String languageid;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String timezoneid;
    //
    @Pattern(regexp = "[\\S]{0,255}", message = "字段过长")
    private String greeting;
    //
    @Pattern(regexp = "[\\S]{0,4294967295}", message = "字段过长")
    private String comments;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String firstname;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String middlename;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String lastname;
    //
    @Pattern(regexp = "[\\S]{0,100}", message = "字段过长")
    private String jobtitle;
    //
    private java.util.Date logindate;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String loginip;
    //
    private java.util.Date lastlogindate;
    //
    @Pattern(regexp = "[\\S]{0,75}", message = "字段过长")
    private String lastloginip;
    //
    private java.util.Date lastfailedlogindate;
    //
    @Max(value = 99999999999L, message = "字段过长")
    private Integer failedloginattempts;
    //
    private boolean lockout;
    //
    private java.util.Date lockoutdate;
    //
    private boolean agreedtotermsofuse;
    //
    private boolean emailaddressverified;
    //
    @Max(value = 99999999999L, message = "字段过长")
    private Integer status;

    private IcloudApplicationentry icloudApplicationentry;

    private IcloudMarketentry icloudMarketentry;

    private IcloudEnterpriseauth icloudEnterpriseauth;

    private IcloudPersonalauth icloudPersonalauth;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    /**
     *
     */
    public Long getMvccversion() {
        return this.mvccversion;
    }

    /**
     *
     */
    public void setMvccversion(Long mvccversion) {
        this.mvccversion = mvccversion;
    }

    /**
     *
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     *
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     */
    public Long getUserid() {
        return this.userid;
    }

    /**
     *
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     *
     */
    public Long getCompanyid() {
        return this.companyid;
    }

    /**
     *
     */
    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    /**
     *
     */
    public java.util.Date getCreatedate() {
        return this.createdate;
    }

    /**
     *
     */
    public void setCreatedate(java.util.Date createdate) {
        this.createdate = createdate;
    }

    /**
     *
     */
    public java.util.Date getModifieddate() {
        return this.modifieddate;
    }

    /**
     *
     */
    public void setModifieddate(java.util.Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    /**
     *
     */
    public boolean getDefaultuser() {
        return this.defaultuser;
    }

    /**
     *
     */
    public void setDefaultuser(boolean defaultuser) {
        this.defaultuser = defaultuser;
    }

    /**
     *
     */
    public Long getContactid() {
        return this.contactid;
    }

    /**
     *
     */
    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

    /**
     *
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     */
    public boolean getPasswordencrypted() {
        return this.passwordencrypted;
    }

    /**
     *
     */
    public void setPasswordencrypted(boolean passwordencrypted) {
        this.passwordencrypted = passwordencrypted;
    }

    /**
     *
     */
    public boolean getPasswordreset() {
        return this.passwordreset;
    }

    /**
     *
     */
    public void setPasswordreset(boolean passwordreset) {
        this.passwordreset = passwordreset;
    }

    /**
     *
     */
    public java.util.Date getPasswordmodifieddate() {
        return this.passwordmodifieddate;
    }

    /**
     *
     */
    public void setPasswordmodifieddate(java.util.Date passwordmodifieddate) {
        this.passwordmodifieddate = passwordmodifieddate;
    }

    /**
     *
     */
    public String getDigest() {
        return this.digest;
    }

    /**
     *
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     *
     */
    public String getReminderqueryquestion() {
        return this.reminderqueryquestion;
    }

    /**
     *
     */
    public void setReminderqueryquestion(String reminderqueryquestion) {
        this.reminderqueryquestion = reminderqueryquestion;
    }

    /**
     *
     */
    public String getReminderqueryanswer() {
        return this.reminderqueryanswer;
    }

    /**
     *
     */
    public void setReminderqueryanswer(String reminderqueryanswer) {
        this.reminderqueryanswer = reminderqueryanswer;
    }

    /**
     *
     */
    public Integer getGracelogincount() {
        return this.gracelogincount;
    }

    /**
     *
     */
    public void setGracelogincount(Integer gracelogincount) {
        this.gracelogincount = gracelogincount;
    }

    /**
     *
     */
    public String getScreenname() {
        return this.screenname;
    }

    /**
     *
     */
    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    /**
     *
     */
    public String getEmailaddress() {
        return this.emailaddress;
    }

    /**
     *
     */
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    /**
     *
     */
    public Long getFacebookid() {
        return this.facebookid;
    }

    /**
     *
     */
    public void setFacebookid(Long facebookid) {
        this.facebookid = facebookid;
    }

    /**
     *
     */
    public String getGoogleuserid() {
        return this.googleuserid;
    }

    /**
     *
     */
    public void setGoogleuserid(String googleuserid) {
        this.googleuserid = googleuserid;
    }

    /**
     *
     */
    public Long getLdapserverid() {
        return this.ldapserverid;
    }

    /**
     *
     */
    public void setLdapserverid(Long ldapserverid) {
        this.ldapserverid = ldapserverid;
    }

    /**
     *
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     *
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     *
     */
    public Long getPortraitid() {
        return this.portraitid;
    }

    /**
     *
     */
    public void setPortraitid(Long portraitid) {
        this.portraitid = portraitid;
    }

    /**
     *
     */
    public String getLanguageid() {
        return this.languageid;
    }

    /**
     *
     */
    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    /**
     *
     */
    public String getTimezoneid() {
        return this.timezoneid;
    }

    /**
     *
     */
    public void setTimezoneid(String timezoneid) {
        this.timezoneid = timezoneid;
    }

    /**
     *
     */
    public String getGreeting() {
        return this.greeting;
    }

    /**
     *
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    /**
     *
     */
    public String getComments() {
        return this.comments;
    }

    /**
     *
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     *
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     */
    public String getMiddlename() {
        return this.middlename;
    }

    /**
     *
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     *
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     *
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     */
    public String getJobtitle() {
        return this.jobtitle;
    }

    /**
     *
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
     *
     */
    public java.util.Date getLogindate() {
        return this.logindate;
    }

    /**
     *
     */
    public void setLogindate(java.util.Date logindate) {
        this.logindate = logindate;
    }

    /**
     *
     */
    public String getLoginip() {
        return this.loginip;
    }

    /**
     *
     */
    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    /**
     *
     */
    public java.util.Date getLastlogindate() {
        return this.lastlogindate;
    }

    /**
     *
     */
    public void setLastlogindate(java.util.Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    /**
     *
     */
    public String getLastloginip() {
        return this.lastloginip;
    }

    /**
     *
     */
    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    /**
     *
     */
    public java.util.Date getLastfailedlogindate() {
        return this.lastfailedlogindate;
    }

    /**
     *
     */
    public void setLastfailedlogindate(java.util.Date lastfailedlogindate) {
        this.lastfailedlogindate = lastfailedlogindate;
    }

    /**
     *
     */
    public Integer getFailedloginattempts() {
        return this.failedloginattempts;
    }

    /**
     *
     */
    public void setFailedloginattempts(Integer failedloginattempts) {
        this.failedloginattempts = failedloginattempts;
    }

    /**
     *
     */
    public boolean getLockout() {
        return this.lockout;
    }

    /**
     *
     */
    public void setLockout(boolean lockout) {
        this.lockout = lockout;
    }

    /**
     *
     */
    public java.util.Date getLockoutdate() {
        return this.lockoutdate;
    }

    /**
     *
     */
    public void setLockoutdate(java.util.Date lockoutdate) {
        this.lockoutdate = lockoutdate;
    }

    /**
     *
     */
    public boolean getAgreedtotermsofuse() {
        return this.agreedtotermsofuse;
    }

    /**
     *
     */
    public void setAgreedtotermsofuse(boolean agreedtotermsofuse) {
        this.agreedtotermsofuse = agreedtotermsofuse;
    }

    /**
     *
     */
    public boolean getEmailaddressverified() {
        return this.emailaddressverified;
    }

    /**
     *
     */
    public void setEmailaddressverified(boolean emailaddressverified) {
        this.emailaddressverified = emailaddressverified;
    }

    /**
     *
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public IcloudApplicationentry getIcloudApplicationentry() {
        return icloudApplicationentry;
    }

    public void setIcloudApplicationentry(IcloudApplicationentry icloudApplicationentry) {
        this.icloudApplicationentry = icloudApplicationentry;
    }

    public IcloudMarketentry getIcloudMarketentry() {
        return icloudMarketentry;
    }

    public void setIcloudMarketentry(IcloudMarketentry icloudMarketentry) {
        this.icloudMarketentry = icloudMarketentry;
    }

    public IcloudEnterpriseauth getIcloudEnterpriseauth() {
        return icloudEnterpriseauth;
    }

    public void setIcloudEnterpriseauth(IcloudEnterpriseauth icloudEnterpriseauth) {
        this.icloudEnterpriseauth = icloudEnterpriseauth;
    }

    public IcloudPersonalauth getIcloudPersonalauth() {
        return icloudPersonalauth;
    }

    public void setIcloudPersonalauth(IcloudPersonalauth icloudPersonalauth) {
        this.icloudPersonalauth = icloudPersonalauth;
    }

}
