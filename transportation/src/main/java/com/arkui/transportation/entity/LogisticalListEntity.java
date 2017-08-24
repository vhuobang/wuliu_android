package com.arkui.transportation.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/15.
 */

public class LogisticalListEntity {


    /**
     * loading_address : 北京
     * surplus_num : 100
     * unit : 1
     * unloading_address : 上海
     * cargo_num : 1000
     * cargo_name : 铁
     * id : 22
     */

    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("unit")
    private String unit;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("id")
    private String id;

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
