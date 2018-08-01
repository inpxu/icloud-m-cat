package com.zhiyun.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhiyun.base.entity.BaseEntity;

/**
 * 实体对象：
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcloudMarketentrydatashareurl extends BaseEntity<Long> {

	private static final long serialVersionUID = 1488065365796138464L;

	// ~~~~实体属性
	// 
	private String dataImageShareUrl;
	// 
	private Long icloudMarketEntryId;

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
	public String getDataImageShareUrl() {
		return this.dataImageShareUrl;
	}

	/**
	 * 
	 */
	public void setDataImageShareUrl(String dataImageShareUrl) {
		this.dataImageShareUrl = dataImageShareUrl;
	}
	
	/**
	 * 
	 */
	public Long getIcloudMarketEntryId() {
		return this.icloudMarketEntryId;
	}

	/**
	 * 
	 */
	public void setIcloudMarketEntryId(Long icloudMarketEntryId) {
		this.icloudMarketEntryId = icloudMarketEntryId;
	}
}
