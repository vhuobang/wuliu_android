package com.arkui.transportation.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/25.
 */

public class CargoSearchListEntity {

    /**
     * loading_address : 北5京
     * unit : 1
     * surplus_num : 1000
     * logistical_status : 1
     * unloading_address : 上海
     * cargo_num : 1000
     * cargo_name : 铁
     * id : 29
     * avatar
     */

    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("unit")
    private String unit;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("logistical_status")
    private String logisticalStatus;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("id")
    private String id;
    @SerializedName("avatar")
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }

    public String getLogisticalStatus() {
        return logisticalStatus;
    }

    public void setLogisticalStatus(String logisticalStatus) {
        this.logisticalStatus = logisticalStatus;
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
}
