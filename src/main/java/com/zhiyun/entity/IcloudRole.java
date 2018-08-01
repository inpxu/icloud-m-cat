package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

/**
 * 实体对象：
 */
public class IcloudRole extends BaseEntity<Long> {

	private static final long serialVersionUID = 3156282393266016087L;

	// ~~~~实体属性
	// 
	private String name;
	// 
	private Integer state;

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
	public Integer getState() {
		return this.state;
	}

	/**
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}
}
