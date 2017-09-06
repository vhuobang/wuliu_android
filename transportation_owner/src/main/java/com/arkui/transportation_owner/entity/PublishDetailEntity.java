package com.arkui.transportation_owner.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nmliz on 2017/8/22.
 */

public class PublishDetailEntity {


    /**
     * id : 1
     * loading_address : 北京-西城区 天安门东长安街
     * unloading_address : 北京-海淀区 上地(地铁站)13号线
     * cargo_name : 测试货品
     * cargo_num : 12
     * unit : 1
     * surplus_num : 10
     * carrier_info : [{"name":"测试公司","logistical_id":"2","cargo_id":"1","quantity_shipment":"2","unit":"1","car_number":"1","carrier_num":"2"},{"name":"方舟公司","logistical_id":"1","cargo_id":"1","quantity_shipment":"2","unit":"1","car_number":"0","carrier_num":0},{"name":"个人测试号","logistical_id":"11","cargo_id":"1","quantity_shipment":"2","unit":"1","car_number":"0","carrier_num":0}]
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
         * name : 测试公司
         * logistical_id : 2
         * cargo_id : 1
         * quantity_shipment : 2
         * unit : 1
         * car_number : 1
         * carrier_num : 2
         */

        @SerializedName("name")
        private String name;
        @SerializedName("logistical_id")
        private String logisticalId;
        @SerializedName("cargo_id")
        private String cargoId;
        @SerializedName("quantity_shipment")
        private String quantityShipment;
        @SerializedName("unit")
        private String unit;
        @SerializedName("car_number")
        private String carNumber;
        @SerializedName("carrier_num")
        private String carrierNum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogisticalId() {
            return logisticalId;
        }

        public void setLogisticalId(String logisticalId) {
            this.logisticalId = logisticalId;
        }

        public String getCargoId() {
            return cargoId;
        }

        public void setCargoId(String cargoId) {
            this.cargoId = cargoId;
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

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getCarrierNum() {
            return carrierNum;
        }

        public void setCarrierNum(String carrierNum) {
            this.carrierNum = carrierNum;
        }
    }
}
