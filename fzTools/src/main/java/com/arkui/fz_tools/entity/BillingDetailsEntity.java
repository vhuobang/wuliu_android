package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/22.
 */

public class BillingDetailsEntity {

    /**
     * amount_paid : 1000.00
     * unit : +
     * user_id : 11
     * change_balance : 1160.00
     * detail_type : recharge
     * created_at : 2017-08-17 13:25:50
     * id : 4
     * cost_name : 充值
     */

    @SerializedName("amount_paid")
    private String amountPaid;
    @SerializedName("unit")
    private String unit;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("change_balance")
    private String changeBalance;
    @SerializedName("detail_type")
    private String detailType;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private String id;
    @SerializedName("cost_name")
    private String costName;

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChangeBalance() {
        return changeBalance;
    }

    public void setChangeBalance(String changeBalance) {
        this.changeBalance = changeBalance;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
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

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }
}
