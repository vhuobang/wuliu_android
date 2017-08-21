package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 84658 on 2017/8/17.
 */

public class ReleaseDetailsEntity implements Serializable{

    /**
     * loading_address : 北京
     * cargo_price : 1000.00
     * cargo_density : 1
     * press_charges : 1000.00
     * unloading_contact : www
     * payment_terms : 1
     * settlement_time : 2017-08-20 08:00:00
     * unit : 1
     * truck_drawer : wyh
     * freight_price : 100.00
     * unloading_tel : 18735555555
     * loading_time : 2017-08-11 08:00:00
     * unloading_address : 上海
     * cargo_num : 100
     * cargo_name : 石油
     * truck_tel : 18735111111
     * id : 10
     * remarks : 注意安全
     *
     *
     *
     * // 新加的字段
     * owner_name  货主名字
     * owner_tel 货主电话
     * infomation_fee 信息费用
     * log_settlement_time 物流截至时间
     *log_contact_name 物流联系人
     * log_contact_tel//物流联系人电话
     * type
     */
//
//    * // 新加的字段
//            * owner_name  货主名字
//    * owner_tel 货主电话
//    * infomation_fee 信息费用
//    * log_settlement_time 物流截至时间
//    *log_contact_name 物流联系人
//    * log_contact_tel//物流联系人电话
    private String owner_name;
    private String owner_tel;
    private String infomation_fee;
    private String log_settlement_time;
    private String log_contact_name;
    private String log_contact_tel;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_tel() {
        return owner_tel;
    }

    public void setOwner_tel(String owner_tel) {
        this.owner_tel = owner_tel;
    }

    public String getInfomation_fee() {
        return infomation_fee;
    }

    public void setInfomation_fee(String infomation_fee) {
        this.infomation_fee = infomation_fee;
    }

    public String getLog_settlement_time() {
        return log_settlement_time;
    }

    public void setLog_settlement_time(String log_settlement_time) {
        this.log_settlement_time = log_settlement_time;
    }

    public String getLog_contact_name() {
        return log_contact_name;
    }

    public void setLog_contact_name(String log_contact_name) {
        this.log_contact_name = log_contact_name;
    }

    public String getLog_contact_tel() {
        return log_contact_tel;
    }

    public void setLog_contact_tel(String log_contact_tel) {
        this.log_contact_tel = log_contact_tel;
    }

    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("cargo_price")
    private String cargoPrice;
    @SerializedName("cargo_density")
    private String cargoDensity;
    @SerializedName("press_charges")
    private String pressCharges;
    @SerializedName("unloading_contact")
    private String unloadingContact;
    @SerializedName("payment_terms")
    private String paymentTerms;
    @SerializedName("settlement_time")
    private String settlementTime;
    @SerializedName("unit")
    private String unit;
    @SerializedName("truck_drawer")
    private String truckDrawer;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("unloading_tel")
    private String unloadingTel;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("truck_tel")
    private String truckTel;
    @SerializedName("id")
    private String id;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public String getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(String cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public String getCargoDensity() {
        return cargoDensity;
    }

    public void setCargoDensity(String cargoDensity) {
        this.cargoDensity = cargoDensity;
    }

    public String getPressCharges() {
        return pressCharges;
    }

    public void setPressCharges(String pressCharges) {
        this.pressCharges = pressCharges;
    }

    public String getUnloadingContact() {
        return unloadingContact;
    }

    public void setUnloadingContact(String unloadingContact) {
        this.unloadingContact = unloadingContact;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTruckDrawer() {
        return truckDrawer;
    }

    public void setTruckDrawer(String truckDrawer) {
        this.truckDrawer = truckDrawer;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getUnloadingTel() {
        return unloadingTel;
    }

    public void setUnloadingTel(String unloadingTel) {
        this.unloadingTel = unloadingTel;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(String unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    public String getCargoNum() {
        return cargoNum;
    }

    public void setCargoNum(String cargoNum) {
        this.cargoNum = cargoNum;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getTruckTel() {
        return truckTel;
    }

    public void setTruckTel(String truckTel) {
        this.truckTel = truckTel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
