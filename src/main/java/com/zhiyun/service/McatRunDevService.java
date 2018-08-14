package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.McatRunDevDto;
import com.zhiyun.entity.McatRunDev;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 10:46
 * @Description:
 */
public interface McatRunDevService  extends BaseService<McatRunDev, Long> {

    DataGrid<McatRunDev> myEnterprisePage(McatRunDevDto mcatRunDevDto, Pager pager);

    DataGrid<McatRunDev> myAgencyPage(McatRunDevDto mcatRunDevDto, Pager pager);

    void sendInstruction(List<String> commcodes,Integer startTime,Integer endTime,Integer isClear);

}
