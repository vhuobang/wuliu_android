package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/10/23.
 */

public class UnionPayEntity {

    /**
     * orderSn : 586097bcffdd4cc512137d59d4e924cf
     */

    @SerializedName("orderSn")
    private String orderSn;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
}
