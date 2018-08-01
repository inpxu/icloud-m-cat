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
public class IcloudHtmlconfig extends BaseEntity<Long> {

	private static final long serialVersionUID = 3963719956874477965L;

	// ~~~~实体属性
	// 
	@Pattern(regexp="[\\S]{0,75}", message="字段过长")
	private String namespace;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long groupid;
	// 
	private java.util.Date createdate;
	// 
	private java.util.Date modifieddate;
	// 
	@Pattern(regexp="[\\S]{0,4294967295}", message="字段过长")
	private String html;
	// 
	@Pattern(regexp="[\\S]{0,4294967295}", message="字段过长")
	private String css;
	// 
	@Pattern(regexp="[\\S]{0,4294967295}", message="字段过长")
	private String js;

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
	public String getNamespace() {
		return this.namespace;
	}

	/**
	 * 
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	/**
	 * 
	 */
	public Long getGroupid() {
		return this.groupid;
	}

	/**
	 * 
	 */
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
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
	public String getHtml() {
		return this.html;
	}

	/**
	 * 
	 */
	public void setHtml(String html) {
		this.html = html;
	}
	
	/**
	 * 
	 */
	public String getCss() {
		return this.css;
	}

	/**
	 * 
	 */
	public void setCss(String css) {
		this.css = css;
	}
	
	/**
	 * 
	 */
	public String getJs() {
		return this.js;
	}

	/**
	 * 
	 */
	public void setJs(String js) {
		this.js = js;
	}
}
