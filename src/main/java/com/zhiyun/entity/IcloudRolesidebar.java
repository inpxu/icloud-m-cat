package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

/**
 * 实体对象：
 * 侧边栏
 */
public class IcloudRolesidebar extends BaseEntity<Long> {

	private static final long serialVersionUID = 5079620831397115663L;

	// ~~~~实体属性
	// 
	private Long icloudroleid;
	// 
	private Long icloudsidebarid;

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
	public Long getIcloudroleid() {
		return this.icloudroleid;
	}

	/**
	 * 
	 */
	public void setIcloudroleid(Long icloudroleid) {
		this.icloudroleid = icloudroleid;
	}
	
	/**
	 * 
	 */
	public Long getIcloudsidebarid() {
		return this.icloudsidebarid;
	}

	/**
	 * 
	 */
	public void setIcloudsidebarid(Long icloudsidebarid) {
		this.icloudsidebarid = icloudsidebarid;
	}
}
