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
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = 2977803646002413965L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long mvccversion;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String uuid;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long roleid;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long companyid;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long userid;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String username;
	// 
	private java.util.Date createdate;
	// 
	private java.util.Date modifieddate;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long classnameid;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long classpk;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String name;
	// 
	@Pattern(regexp="[\\s\\S]{0,4294967295}", message="字段过长")
	private String title;
	// 
	@Pattern(regexp="[\\s\\S]{0,4294967295}", message="字段过长")
	private String description;
	// 
	@Max(value=99999999999L,message="字段过长")
	private Integer type;
	// 
	@Pattern(regexp="[\\s\\S]{0,75}", message="字段过长")
	private String subtype;

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
	public Long getRoleid() {
		return this.roleid;
	}

	/**
	 * 
	 */
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
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
	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 */
	public void setUsername(String username) {
		this.username = username;
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
	public Long getClassnameid() {
		return this.classnameid;
	}

	/**
	 * 
	 */
	public void setClassnameid(Long classnameid) {
		this.classnameid = classnameid;
	}
	
	/**
	 * 
	 */
	public Long getClasspk() {
		return this.classpk;
	}

	/**
	 * 
	 */
	public void setClasspk(Long classpk) {
		this.classpk = classpk;
	}
	
	/**
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 
	 */
	public String getSubtype() {
		return this.subtype;
	}

	/**
	 * 
	 */
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
}
