package com.arkui.transportation.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/16.
 */

public class CargoCarrierListEntity {

    /**
     * carrier_num : 300
     * license_plate : 京F4W67Q
     * logistical_status : 3
     * id : 3
     * logistical : 待付款
     */

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("log_status")
    private String logisticalStatus;
    @SerializedName("id")
    private String id;
    @SerializedName("logistical")
    private String logistical;
    /**
     * loading_address : 北2京
     * unit : 1
     * unloading_address : 上2海
     * cargo_name : 铁2
     */

    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("unit")
    private String unit;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_name")
    private String cargoName;

    public String getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(String carrierNum) {
        this.carrierNum = carrierNum;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLogisticalStatus() {
        return logisticalStatus;
    }

    public void setLogisticalStatus(String logisticalStatus) {
        this.logisticalStatus = logisticalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogistical() {
        return logistical;
    }

    public void setLogistical(String logistical) {
        this.logistical = logistical;
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

    public String getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(String unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
}
