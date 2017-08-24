package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/22.
 */

public class IntegralDetailsEntity {

    /**
     * user_id : 11
     * integral : 1000.00
     * created_at : 2017-08-17 13:39:25
     * id : 2
     * status : 2
     */

    @SerializedName("user_id")
    private String userId;
    @SerializedName("integral")
    private String integral;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private String id;
    @SerializedName("status")
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
