package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/9/25.
 */

public class RemarkEntity {

    /**
     * create_time : 2017-08-17 15:25:03
     * id : 1
     * content : 我是备注
     */

    @SerializedName("create_time")
    private String createTime;
    @SerializedName("id")
    private String id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
