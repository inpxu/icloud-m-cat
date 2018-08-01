package com.zhiyun.dao;

import java.util.List;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.IcloudMarketentrydatashareurl;

public interface IcloudMarketentrydatashareurlDao extends BaseDao<IcloudMarketentrydatashareurl, Long> {

	int deleteByMarketEntryId(long id);

	List<IcloudMarketentrydatashareurl> findByMarketEntryId(long id);
	
	// 通过id查询关联的证件图片
    List<IcloudMarketentrydatashareurl> findUrl(Long id);

}
