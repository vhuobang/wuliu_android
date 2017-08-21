package com.arkui.transportation.entity;

/**
 * Created by nmliz on 2017/8/17.
 */

public class RefreshWaybill {

    //刷新目标
    private int type;

    public RefreshWaybill(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
