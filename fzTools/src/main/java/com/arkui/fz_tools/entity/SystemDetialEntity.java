package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/14.
 */

public class SystemDetialEntity {

    /**
     * created_at : 2017-08-07 16:20:12
     * content : 好了没有
     */

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("content")
    private String content;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
