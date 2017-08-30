package com.arkui.transportation_shipper.common.entity;

/**
 * Created by nmliz on 2017/8/29.
 */

public class SupplyListEntity {

    /**
     * logistical_id : 1121
     * cargo_id : 4
     * log_settlement_time : null
     * loading_address : 北京-昌平区 龙泽(地铁站)13号线
     * unloading_address : 北京-西城区 朝阳公园朝阳公园南路1号
     * cargo_name : 测试货品
     * cargo_num : 200
     * cargo_price : 50.00
     * unit : 1
     */

    private String logistical_id;
    private String cargo_id;
    private String log_settlement_time;
    private String loading_address;
    private String unloading_address;
    private String cargo_name;
    private String cargo_num;
    private String cargo_price;
    private String unit;
   // private String unit;

    public String getLogistical_id() {
        return logistical_id;
    }

    public void setLogistical_id(String logistical_id) {
        this.logistical_id = logistical_id;
    }

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getLog_settlement_time() {
        return log_settlement_time;
    }

    public void setLog_settlement_time(String log_settlement_time) {
        this.log_settlement_time = log_settlement_time;
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

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getCargo_num() {
        return cargo_num;
    }

    public void setCargo_num(String cargo_num) {
        this.cargo_num = cargo_num;
    }

    public String getCargo_price() {
        return cargo_price;
    }

    public void setCargo_price(String cargo_price) {
        this.cargo_price = cargo_price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
