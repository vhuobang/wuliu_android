package com.arkui.fz_tools.entity;

/**
 * Created by nmliz on 2017/8/17.
 * 发布的参数 用来多页面传递
 */

public class PublishEntity {

    private String user_id;
    private int op_status;
    private String logistical_ids;
    private String loading_address;
    private String unloading_address;
    private String cargo_name;
    private String cargo_num;
    private String cargo_density;
    private String freight_price;
    private String cargo_price;
    private String loading_time;
    private int payment_terms;
    private int settlement_time;
    private String press_charges;
    private String truck_drawer;
    private String truck_tel;
    private String unloading_contact;

    public String getUnloading_tel() {
        return unloading_tel;
    }

    public void setUnloading_tel(String unloading_tel) {
        this.unloading_tel = unloading_tel;
    }

    private String unloading_tel;
    private int type;
    private String remarks;
    private int unit;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getOp_status() {
        return op_status;
    }

    public void setOp_status(int op_status) {
        this.op_status = op_status;
    }

    public String getLogistical_ids() {
        return logistical_ids;
    }

    public void setLogistical_ids(String logistical_ids) {
        this.logistical_ids = logistical_ids;
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

    public String getCargo_density() {
        return cargo_density;
    }

    public void setCargo_density(String cargo_density) {
        this.cargo_density = cargo_density;
    }

    public String getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(String freight_price) {
        this.freight_price = freight_price;
    }

    public String getCargo_price() {
        return cargo_price;
    }

    public void setCargo_price(String cargo_price) {
        this.cargo_price = cargo_price;
    }

    public String getLoading_time() {
        return loading_time;
    }

    public void setLoading_time(String loading_time) {
        this.loading_time = loading_time;
    }

    public int getPayment_terms() {
        return payment_terms;
    }

    public void setPayment_terms(int payment_terms) {
        this.payment_terms = payment_terms;
    }

    public int getSettlement_time() {
        return settlement_time;
    }

    public void setSettlement_time(int settlement_time) {
        this.settlement_time = settlement_time;
    }

    public String getPress_charges() {
        return press_charges;
    }

    public void setPress_charges(String press_charges) {
        this.press_charges = press_charges;
    }

    public String getTruck_drawer() {
        return truck_drawer;
    }

    public void setTruck_drawer(String truck_drawer) {
        this.truck_drawer = truck_drawer;
    }

    public String getTruck_tel() {
        return truck_tel;
    }

    public void setTruck_tel(String truck_tel) {
        this.truck_tel = truck_tel;
    }

    public String getUnloading_contact() {
        return unloading_contact;
    }

    public void setUnloading_contact(String unloading_contact) {
        this.unloading_contact = unloading_contact;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
