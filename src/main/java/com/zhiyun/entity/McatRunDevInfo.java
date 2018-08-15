package com.zhiyun.entity;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/15 09:53
 * @Description:
 */
public class McatRunDevInfo{

    private Long id;

    private Long modelId;

    private String modelName;

    private Long sumDevs;

    private Long onLineDevs;

    private Long faultDevs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getSumDevs() {
        return sumDevs;
    }

    public void setSumDevs(Long sumDevs) {
        this.sumDevs = sumDevs;
    }

    public Long getOnLineDevs() {
        return onLineDevs;
    }

    public void setOnLineDevs(Long onLineDevs) {
        this.onLineDevs = onLineDevs;
    }

    public Long getFaultDevs() {
        return faultDevs;
    }

    public void setFaultDevs(Long faultDevs) {
        this.faultDevs = faultDevs;
    }
}
