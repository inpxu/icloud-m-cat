package com.zhiyun.service;

import java.util.List;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.IcloudMarketentrydatashareurl;

/**
 * 服务接口。
 */
public interface IcloudMarketentrydatashareurlService extends BaseService<IcloudMarketentrydatashareurl, Long> {

	List<IcloudMarketentrydatashareurl> findByMarketEntryId(long marketEntryId);

}
