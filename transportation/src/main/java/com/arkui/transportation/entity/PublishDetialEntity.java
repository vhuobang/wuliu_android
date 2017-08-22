package com.arkui.transportation.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 84658 on 2017/8/22.
 */

public class PublishDetialEntity {

    /**
     * loading_address : 北京
     * surplus_num : 100
     * unit : 1
     * unloading_address : 上海
     * cargo_num : 1000
     * carrier_info : [{"carrier_num":"100","license_plate":"京F4W67X","logistical_status":"1","id":"1","logistical":"待装货"}]
     * cargo_name : 铁
     * id : 23
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
    @SerializedName("carrier_info")
    private List<CarrierInfoBean> carrierInfo;

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

    public List<CarrierInfoBean> getCarrierInfo() {
        return carrierInfo;
    }

    public void setCarrierInfo(List<CarrierInfoBean> carrierInfo) {
        this.carrierInfo = carrierInfo;
    }

    public static class CarrierInfoBean {
        /**
         * carrier_num : 100
         * license_plate : 京F4W67X
         * logistical_status : 1
         * id : 1
         * logistical : 待装货
         */

        @SerializedName("carrier_num")
        private String carrierNum;
        @SerializedName("license_plate")
        private String licensePlate;
        @SerializedName("logistical_status")
        private String logisticalStatus;
        @SerializedName("id")
        private String id;
        @SerializedName("logistical")
        private String logistical;

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
    }
}
