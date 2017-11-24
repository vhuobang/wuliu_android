package com.arkui.transportation_shipper.common.entity;

/**
 * Created by nmliz on 2017/9/5.
 * 车辆现状
 */

public class TruckStatusEntity {

    /**
     * cargo_name : Test
     * loading_address : 北京-朝阳区 朝阳大悦城朝阳北路101号
     * name : 个人测试号
     * unloading_address : 北京-海淀区 西二旗(地铁站)13号线;昌平线;(在建)昌平线南延线
     */
    private String license_plate;
    private String cargo_name;
    private String loading_address;
    private String logistical_name;
    private String unloading_address;
    private String   truck_id ;
    private String truck_status;

    public String getTruck_status() {
        return truck_status;
    }

    public void setTruck_status(String truck_status) {
        this.truck_status = truck_status;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getLogistical_name() {
        return logistical_name;
    }

    public void setLogistical_name(String logistical_name) {
        this.logistical_name = logistical_name;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getLoading_address() {
        return loading_address;
    }

    public void setLoading_address(String loading_address) {
        this.loading_address = loading_address;
    }



    public String getUnloading_address() {
        return unloading_address;
    }

    public void setUnloading_address(String unloading_address) {
        this.unloading_address = unloading_address;
    }
}
