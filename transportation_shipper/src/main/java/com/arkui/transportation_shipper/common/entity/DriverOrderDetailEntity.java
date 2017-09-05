package com.arkui.transportation_shipper.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/9/3.
 */

public class DriverOrderDetailEntity {

    /**
     * carrier_num : 50
     * unit : 1
     * cargo_density : 20
     * license_plate : 京A666666
     * unloading_contact : Test2
     * truck_drawer : Test
     * unloading_tel : 18647624075
     * loading_time : 2017-08-30 08:00:00
     * cargo_num : 100
     * cargo_name : Test
     * truck_tel : 18811595636
     * remarks :
     * loading_address
     * unloading_address
     */

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("unit")
    private String unit;
    @SerializedName("cargo_density")
    private String cargoDensity;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("unloading_contact")
    private String unloadingContact;
    @SerializedName("truck_drawer")
    private String truckDrawer;
    @SerializedName("unloading_tel")
    private String unloadingTel;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("truck_tel")
    private String truckTel;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("unloading_address")
    private String unloadingAddress;

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public String getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(String unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    public String getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(String carrierNum) {
        this.carrierNum = carrierNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCargoDensity() {
        return cargoDensity;
    }

    public void setCargoDensity(String cargoDensity) {
        this.cargoDensity = cargoDensity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getUnloadingContact() {
        return unloadingContact;
    }

    public void setUnloadingContact(String unloadingContact) {
        this.unloadingContact = unloadingContact;
    }

    public String getTruckDrawer() {
        return truckDrawer;
    }

    public void setTruckDrawer(String truckDrawer) {
        this.truckDrawer = truckDrawer;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
