package com.arkui.transportation_shipper.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/9/3.
 */

public class DriverOrderListEntity {


    /**
     * carrier_num : 50
     * loading_address : 北京-朝阳区 朝阳大悦城朝阳北路101号
     * truck_id : 5
     * license_plate : 京A666666
     * cargo_id : 20
     * unloading_address : 北京-海淀区 西二旗(地铁站)13号线;昌平线;(在建)昌平线南延线
     * cargo_name : Test
     * id : 8
     */

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("truck_id")
    private String truckId;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("cargo_id")
    private String cargoId;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("id")
    private String id;

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

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
