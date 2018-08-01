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
 * @author 庄超
 * @version v1.0
 * @date
 */
public class CasUser extends BaseEntity<Long> {

	private static final long serialVersionUID = 6465522992195857599L;

	// ~~~~实体属性
	// 头像URL
	@Pattern(regexp="[\\S]{0,255}", message="头像URL字段过长")
	private String headImg;
	// 部门主键
	@Max(value=9223372036854775807L,message="部门主键字段过长")
	private Long orgId;
	// 登录账号
	@Pattern(regexp="[\\S]{0,20}", message="登录账号字段过长")
	private String account;
	// 姓名
	@Pattern(regexp="[\\S]{0,20}", message="姓名字段过长")
	private String name;
	// 电话
	@Pattern(regexp="[\\S]{0,20}", message="电话字段过长")
	private String phone;
	// 邮件
	@Pattern(regexp="[\\S]{0,255}", message="邮件字段过长")
	private String email;
	// 密码
	@Pattern(regexp="[\\S]{0,100}", message="密码字段过长")
	private String password;
	// 加密key
	@Pattern(regexp="[\\S]{0,255}", message="加密key字段过长")
	private String slat;
	// 职位
	@Pattern(regexp="[\\S]{0,20}", message="职位字段过长")
	private String position;
	// 是否启用
	private Boolean isAble;
	// 是否管理员
	private Boolean isAdmin;
	// 是否联系人
	private Boolean isContacts;
	// 企业标识
	@Max(value=9223372036854775807L,message="企业标识字段过长")
	private Long companyId;
	// 启用时间
	private java.util.Date ableTime;
	// 停用时间
	private java.util.Date disableTime;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	/**
	 * 头像URL
	 */
	public String getHeadImg() {
		return this.headImg;
	}

	/**
	 * 头像URL
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	/**
	 * 部门主键
	 */
	public Long getOrgId() {
		return this.orgId;
	}

	/**
	 * 部门主键
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 登录账号
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * 登录账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 姓名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 邮件
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 加密key
	 */
	public String getSlat() {
		return this.slat;
	}

	/**
	 * 加密key
	 */
	public void setSlat(String slat) {
		this.slat = slat;
	}
	
	/**
	 * 职位
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 * 职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 是否启用
	 */
	public Boolean getIsAble() {
		return this.isAble;
	}

	/**
	 * 是否启用
	 */
	public void setIsAble(Boolean isAble) {
		this.isAble = isAble;
	}
	
	/**
	 * 是否管理员
	 */
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	/**
	 * 是否管理员
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/**
	 * 是否联系人
	 */
	public Boolean getIsContacts() {
		return this.isContacts;
	}

	/**
	 * 是否联系人
	 */
	public void setIsContacts(Boolean isContacts) {
		this.isContacts = isContacts;
	}
	
	/**
	 * 企业标识
	 */
	public Long getCompanyId() {
		return this.companyId;
	}

	/**
	 * 企业标识
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	/**
	 * 启用时间
	 */
	public java.util.Date getAbleTime() {
		return this.ableTime;
	}

	/**
	 * 启用时间
	 */
	public void setAbleTime(java.util.Date ableTime) {
		this.ableTime = ableTime;
	}
	
	/**
	 * 停用时间
	 */
	public java.util.Date getDisableTime() {
		return this.disableTime;
	}

	/**
	 * 停用时间
	 */
	public void setDisableTime(java.util.Date disableTime) {
		this.disableTime = disableTime;
	}
}
