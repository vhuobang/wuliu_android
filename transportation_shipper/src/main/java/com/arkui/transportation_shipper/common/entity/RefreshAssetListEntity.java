package com.arkui.transportation_shipper.common.entity;

/**
 * Created by nmliz on 2017/8/28.
 */

public class RefreshAssetListEntity {
    //type 1 刷新车辆 2 刷新司机 列表
    private int type;

    public RefreshAssetListEntity(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
