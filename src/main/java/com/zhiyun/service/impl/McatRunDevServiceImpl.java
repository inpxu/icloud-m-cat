package com.zhiyun.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.constants.AuditState;
import com.zhiyun.dao.IcloudOnicloudDao;
import com.zhiyun.dao.McatRunDevDao;
import com.zhiyun.dto.IcloudOnicloudDto;
import com.zhiyun.dto.McatRunDevDto;
import com.zhiyun.entity.IcloudOnicloud;
import com.zhiyun.entity.McatRunDev;
import com.zhiyun.mq.activemq.AmqProducerTemplate;
import com.zhiyun.service.IcloudOnicloudService;
import com.zhiyun.service.McatRunDevService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/14 10:47
 * @Description:
 */
@Service("mcatRunDevService")
public class McatRunDevServiceImpl extends BaseServiceImpl<McatRunDev, Long> implements McatRunDevService {

    @Resource
    private McatRunDevDao mcatRunDevDao;

    @Resource
    private IcloudOnicloudDao icloudOnicloudDao;

    @Resource
    private AmqProducerTemplate amqProducerTemplate;

    @Override
    protected BaseDao<McatRunDev, Long> getBaseDao() {
        return this.mcatRunDevDao;
    }

    @Override
    public DataGrid<McatRunDev> myEnterprisePage(McatRunDevDto mcatRunDevDto, Pager pager) {
        Params params = Params.create().add("entity",mcatRunDevDto);

        IcloudOnicloud icloudOnicloud = new IcloudOnicloud();
        icloudOnicloud.setCreateUserId(UserHolder.getId());
        List<IcloudOnicloud> icloudOniclouds = icloudOnicloudDao.find(icloudOnicloud);

        if(!CollectionUtils.isNotEmpty(icloudOniclouds)){
            return new DataGrid(null,0);
        }

        List<Long> companyIds = icloudOniclouds.stream().map(IcloudOnicloud::getOrganizationId).collect(Collectors.toList());
        params.add("companyIds",companyIds);

        return mcatRunDevDao.myPage(params,pager);
    }

    @Override
    public DataGrid<McatRunDev> myAgencyPage(McatRunDevDto mcatRunDevDto, Pager pager) {

        Params params = Params.create().add("entity",mcatRunDevDto);

        Params p = Params.create();
        p.add("status",AuditState.AUDITED);
        p.add("userId",UserHolder.getId());

        List<IcloudOnicloudDto> icloudOniclouds =
                icloudOnicloudDao.findByUserIdAndStatus(p);

        if(!CollectionUtils.isNotEmpty(icloudOniclouds)){
            return new DataGrid(null,0);
        }

        List<Long> companyIds = icloudOniclouds.stream().map(IcloudOnicloud::getOrganizationId).collect(Collectors.toList());
        params.add("companyIds",companyIds);

        return mcatRunDevDao.myPage(params,pager);

    }

    @Override
    public void sendInstruction(List<String> commcodes, Integer startTime, Integer endTime, Integer isClear) {

        if(!CollectionUtils.isNotEmpty(commcodes)){
            return;
        }

        List<Map<String,Object>> results = mcatRunDevDao.listByCommcodes(Params.create().add("commcodes",commcodes));
        if(!CollectionUtils.isNotEmpty(results)){
            return;
        }

        for(Map result:results){
            if((Long)result.get("nValue") == 1){
                throw new BusinessException("有设备上一条指令正在下达，请稍后操作");
            }
        }

        commcodes.forEach(commcode ->{
            JSONArray command=new JSONArray();
            JSONObject startTimeJ=new JSONObject();
            startTimeJ.put("key","900028");
            startTimeJ.put("value",startTime == null ? 0 : startTime);
            JSONObject endTimeJ=new JSONObject();
            endTimeJ.put("key","900029");
            endTimeJ.put("value",endTime == null ? 0 : endTime);
            JSONObject isClearJ=new JSONObject();
            isClearJ.put("key","90002A");
            isClearJ.put("value",isClear);
            command.add(startTimeJ);
            command.add(endTimeJ);
            command.add(isClearJ);
            JSONObject object=new JSONObject();
            object.put("devSerial",commcode);
            object.put("command",command);
            JSONObject sendCommand=new JSONObject();
            sendCommand.put("sendCommand",object);
            amqProducerTemplate.sendMessage("SEND_MQ",sendCommand.toJSONString(),AmqProducerTemplate.SendType.QUEUE);

            results.stream().filter(r->{
                return commcode.equals(new String((byte[]) r.get("commcode")));
            }).forEach(r->{
                List<Long> ids = new ArrayList<>();
                ids.add((long)r.get("id"));
                mcatRunDevDao.updateNvalueByCommcode(Params.create().add("ids",ids).add("status",1));
            });

        });

    }
}
