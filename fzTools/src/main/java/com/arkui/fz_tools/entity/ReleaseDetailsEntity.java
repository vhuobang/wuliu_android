package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/17.
 */

public class ReleaseDetailsEntity {

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
     */

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
