package com.arkui.transportation_shipper.owner.entity;

/**
 * Created by nmliz on 2017/9/5.
 */

public class DriverDetailEntity {

    /**
     * cargo_id : 10
     * cargo_name : 大米
     * license_plate : 京A666666
     * loading_address : 北京-宣武区 北京站毛家湾胡同甲13号
     * logistical_id : 8
     * logistical_name : 个人测试号
     * truck_id : 5
     * unloading_address : 广东-广州 哈哈镜鸭脖子(人民北店)人民北路829-831号越富二期创兴广场1楼05-5号
     */

    private String cargo_id;
    private String cargo_name;
    private String license_plate;
    private String loading_address;
    private String logistical_id;
    private String logistical_name;
    private String truck_id;
    private String unloading_address;

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getLoading_address() {
        return loading_address;
    }

    public void setLoading_address(String loading_address) {
        this.loading_address = loading_address;
    }

    public String getLogistical_id() {
        return logistical_id;
    }

    public void setLogistical_id(String logistical_id) {
        this.logistical_id = logistical_id;
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

    public String getUnloading_address() {
        return unloading_address;
    }

    public void setUnloading_address(String unloading_address) {
        this.unloading_address = unloading_address;
    }
}
