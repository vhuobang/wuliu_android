package com.arkui.transportation.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/24.
 */

public class SliderMessageEntity {

    /**
     * create_time : 2017-08-17 16:18:12
     * id : 6
     * sort : 6
     * content : 岁末回馈盛会圣诞大欢送
     */

    @SerializedName("create_time")
    private String createTime;
    @SerializedName("id")
    private String id;
    @SerializedName("sort")
    private String sort;
    @SerializedName("content")
    private String content;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
