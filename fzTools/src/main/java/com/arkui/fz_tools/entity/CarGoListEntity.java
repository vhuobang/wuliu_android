package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/17.
 */

public class CarGoListEntity {

    /**
     * loading_address : 北京
     * surplus_num : 50
     * unit : 1
     * freight_price : 100.00
     * unloading_address : 上海
     * cargo_num : 100
     * cargo_name : 石油
     * id : 10
     * c_status : 1
     * settlement_time : 1
     */

    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("unit")
    private String unit;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("id")
    private String id;
    @SerializedName("c_status")
    private String cStatus;
    @SerializedName("settlement_time")
    private String settlementTime;

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCStatus() {
        return cStatus;
    }

    public void setCStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }
}
