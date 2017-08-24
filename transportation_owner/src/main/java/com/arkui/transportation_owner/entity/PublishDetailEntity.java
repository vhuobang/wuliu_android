package com.arkui.transportation_owner.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nmliz on 2017/8/22.
 */

public class PublishDetailEntity {

    /**
     * id : 5
     * loading_address : 北京-朝阳区 西单大悦城西单北大街131号
     * unloading_address : 北京-海淀区 上地(地铁站)13号线
     * cargo_name : 测试货品
     * cargo_num : 500
     * unit : 1
     * surplus_num : 500
     * carrier_info : [{"name":null,"assign_vehicles":"0","quantity_shipment":"0","unit":"1"}]
     */

    @SerializedName("id")
    private String id;
    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("unit")
    private String unit;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("carrier_info")
    private List<CarrierInfoBean> carrierInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoNum() {
        return cargoNum;
    }

    public void setCargoNum(String cargoNum) {
        this.cargoNum = cargoNum;
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

    public List<CarrierInfoBean> getCarrierInfo() {
        return carrierInfo;
    }

    public void setCarrierInfo(List<CarrierInfoBean> carrierInfo) {
        this.carrierInfo = carrierInfo;
    }

    public static class CarrierInfoBean {
        /**
         * name : null
         * assign_vehicles : 0
         * quantity_shipment : 0
         * unit : 1
         */

        @SerializedName("name")
        private String name;
        @SerializedName("assign_vehicles")
        private String assignVehicles;
        @SerializedName("quantity_shipment")
        private String quantityShipment;
        @SerializedName("unit")
        private String unit;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAssignVehicles() {
            return assignVehicles;
        }

        public void setAssignVehicles(String assignVehicles) {
            this.assignVehicles = assignVehicles;
        }

        public String getQuantityShipment() {
            return quantityShipment;
        }

        public void setQuantityShipment(String quantityShipment) {
            this.quantityShipment = quantityShipment;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
