package com.zhiyun.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhiyun.base.entity.BaseEntity;

/**
 * 实体对象：
 */
public class IcloudSidebar extends BaseEntity<Long> {

	private static final long serialVersionUID = 4855184561303419809L;

	// ~~~~实体属性
	// 
	private String name;
	// 
	private String url;
	// 序列号 重复需要根据type判断
	private Integer sequence;
	// 0 为根 
	@JsonProperty( "parentId")
	private Long parentid;

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
	public String getUrl() {
		return this.url;
	}

	/**
	 * 
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 序列号 重复需要根据type判断
	 */
	public Integer getSequence() {
		return this.sequence;
	}

	/**
	 * 序列号 重复需要根据type判断
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * 0 为根 
	 */
	public Long getParentid() {
		return this.parentid;
	}

	/**
	 * 0 为根 
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
}
