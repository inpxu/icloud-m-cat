package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Page;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.entity.IcloudUserPersonalauth;
import com.zhiyun.entity.McatRunDev;

import java.util.List;
import java.util.Map;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 10:38
 * @Description:
 */
public interface McatRunDevDao  extends BaseDao<McatRunDev, Long> {

    DataGrid<McatRunDev> myPage(Params params, Pager pager);

    List<Map<String,Object>> listByCommcodes(Params params);

    int updateNvalueByCommcode(Params params);

}
