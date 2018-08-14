/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.IcloudApplicationagencyDto;
import com.zhiyun.entity.IcloudApplicationagency;
import com.zhiyun.entity.IcloudOnicloud;

import java.util.List;

/**
 * Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface IcloudApplicationagencyService extends BaseService<IcloudApplicationagency, Long> {

    void save(IcloudApplicationagencyDto icloudApplicationagencyDto);

    IcloudApplicationagencyDto updateApplicationAgency(IcloudApplicationagencyDto icloudApplicationagencyDto);

    List<IcloudApplicationagencyDto> listApplicationAgencyDto();

    JSONArray getAgencyArea(Long exceptId, String agencyType);
    
    // 查询未发送的数据
 	List<IcloudApplicationagency> findBySended();
    
 	// 修改发送状态
 	int updateSended(Long id);

}
