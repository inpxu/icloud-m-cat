package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.McatRunDevDao;
import com.zhiyun.entity.McatRunDev;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 10:38
 * @Description:
 */
@Repository("mcatRunDevDao")
public class McatRunDevDaoImpl  extends BaseDaoImpl<McatRunDev, Long> implements McatRunDevDao {

    @Override
    public DataGrid<McatRunDev> myPage(Params params, Pager pager) {
        return this.selectPage(getMethodName(),params,pager);
    }

    @Override
    public List<Map<String,Object>> listByCommcodes(Params params) {
        return this.selectList(getMethodName(),params);
    }

    @Override
    public int updateNvalueByCommcode(Params params) {
        return this.update(getMethodName(),params);
    }
}
