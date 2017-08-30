package com.arkui.transportation_shipper.common.entity;

/**
 * Created by nmliz on 2017/8/29.
 */

public class VehicleModelEntity {

    /**
     * id : 2
     * truck_type : 罐车
     * sort : 2
     * create_time : 2017-08-29 11:09:25
     */

    private String id;
    private String truck_type;
    private String sort;
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTruck_type() {
        return truck_type;
    }

    public void setTruck_type(String truck_type) {
        this.truck_type = truck_type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
