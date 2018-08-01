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
public class IcloudSchedule extends BaseEntity<Long> {

	private static final long serialVersionUID = 4576714833934224820L;

	// ~~~~实体属性
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long userid;
	// 
	@Max(value=9223372036854775807L,message="字段过长")
	private Long groupid;
	// 
	private java.util.Date createdate;
	// 
	private java.util.Date modifieddate;
	// 
	@Pattern(regexp="[\\S]{0,75}", message="字段过长")
	private String title;
	// 
	private java.util.Date start;
	// 
	private java.util.Date end;
	// 
	private boolean allday;
	// 
	@Pattern(regexp="[\\S]{0,75}", message="字段过长")
	private String color;

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
	public java.util.Date getStart() {
		return this.start;
	}

	/**
	 * 
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
	}
	
	/**
	 * 
	 */
	public java.util.Date getEnd() {
		return this.end;
	}

	/**
	 * 
	 */
	public void setEnd(java.util.Date end) {
		this.end = end;
	}
	
	/**
	 * 
	 */
	public boolean getAllday() {
		return this.allday;
	}

	/**
	 * 
	 */
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
	
	/**
	 * 
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * 
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
