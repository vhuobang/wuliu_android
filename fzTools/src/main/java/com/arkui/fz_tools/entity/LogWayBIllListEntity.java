package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/23.
 */

public class LogWayBIllListEntity {

    /**
     * carrier_num : 300
     * loading_address : 北2京
     * all_price : 100000
     * cargo_status : 3
     * log_name : 北京华美国际物流有限公司
     * truck_status : 3
     * unit : 1
     * license_plate : 京F4W67Q
     * log_status : 3
     * freight_price : 100.00
     * unloading_address : 上2海
     * cargo_num : 1000
     * owner_status : 4
     * cargo_name : 铁2
     * id : 3
     * truck_name : 司机2
     *
     *log 经度
     * lat 纬度
     */

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("all_price")
    private String allPrice;
    @SerializedName("cargo_status")
    private String cargoStatus;
    @SerializedName("log_name")
    private String logName;
    @SerializedName("truck_status")
    private String truckStatus;
    @SerializedName("unit")
    private String unit;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("log_status")
    private String logStatus;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("owner_status")
    private String ownerStatus;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("id")
    private String id;
    @SerializedName("truck_name")
    private String truckName;
    @SerializedName("log")
    private String  log;
    @SerializedName("lat")
    private String lat;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(String carrierNum) {
        this.carrierNum = carrierNum;
    }

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(String cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getTruckStatus() {
        return truckStatus;
    }

    public void setTruckStatus(String truckStatus) {
        this.truckStatus = truckStatus;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
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

    public String getOwnerStatus() {
        return ownerStatus;
    }

    public void setOwnerStatus(String ownerStatus) {
        this.ownerStatus = ownerStatus;
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

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }
}
