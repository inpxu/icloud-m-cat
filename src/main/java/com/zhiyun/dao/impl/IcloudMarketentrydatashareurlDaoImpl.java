package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.IcloudMarketentrydatashareurlDao;
import com.zhiyun.entity.IcloudMarketentrydatashareurl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("icloudMarketentrydatashareurlDao")
public class IcloudMarketentrydatashareurlDaoImpl extends BaseDaoImpl<IcloudMarketentrydatashareurl, Long>
		implements IcloudMarketentrydatashareurlDao {

	@Override
	public int deleteByMarketEntryId(long marketEntryId) {
		Params params = Params.create().add("marketEntryId", marketEntryId);
		params.add("deleted","T");
		return this.update(getMethodName(), params);
	}

	@Override
	public List<IcloudMarketentrydatashareurl> findByMarketEntryId(long marketEntryId) {
		Params params = Params.create().add("marketEntryId", marketEntryId);
		return this.selectList(getMethodName(), params);
	}

	@Override
	public List<IcloudMarketentrydatashareurl> findUrl(Long id) {
		Params params = Params.create().add("id", id);
		return this.selectList(getMethodName(),params);
	}

}
