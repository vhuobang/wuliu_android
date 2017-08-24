package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/22.
 */

public class InformationDetailEntity {

    /**
     * user_id : 13
     * order_number : 2017081032587632
     * infomation_fee : 100.00
     * created_at : 2017-08-10 10:24:42
     * id : 1
     */

    @SerializedName("user_id")
    private String userId;
    @SerializedName("order_number")
    private String orderNumber;
    @SerializedName("infomation_fee")
    private String infomationFee;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getInfomationFee() {
        return infomationFee;
    }

    public void setInfomationFee(String infomationFee) {
        this.infomationFee = infomationFee;
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
}
