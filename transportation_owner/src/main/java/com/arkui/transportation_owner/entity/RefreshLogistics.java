package com.arkui.transportation_owner.entity;

/**
 * Created by nmliz on 2017/8/17.
 */

public class RefreshLogistics {

    //刷新目标
    private int type;

    public RefreshLogistics(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
