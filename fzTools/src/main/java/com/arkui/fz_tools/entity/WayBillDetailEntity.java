package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/16.
 */

public class WayBillDetailEntity {


    /**
     * carrier_num : 100
     * all_price :
     * owner_name : 车主2
     * cargo_id : 22
     * order_number : qweqwqweqe1
     * unloading_weight : 0.00
     * license_plate : 京F4W67X
     * unloading_photo :
     * freight_price : 100.00
     * star_rating : 0.00
     * loading_time : 2017-08-08 13:40:52
     * unloading_time : 0000-00-00 00:00:00
     * cargo_num : 1000
     * tel : 18735100071
     * loading_weight : 0.00
     * loading_photo :
     * owner_cargo_id : 11
     * unit
     * logistical_id 物流id
     */

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("all_price")
    private String allPrice;
    @SerializedName("owner_name")
    private String ownerName;
    @SerializedName("cargo_id")
    private String cargoId;
    @SerializedName("order_number")
    private String orderNumber;
    @SerializedName("unloading_weight")
    private String unloadingWeight;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("unloading_photo")
    private String unloadingPhoto;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("unloading_time")
    private String unloadingTime;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("tel")
    private String tel;
    @SerializedName("loading_weight")
    private String loadingWeight;
    @SerializedName("loading_photo")
    private String loadingPhoto;
    @SerializedName("owner_cargo_id")
    private String ownerCargoId;
    @SerializedName("unit")
    private String unit;
    @SerializedName("logistical_id") //物流id
    private String logisticalId;

    public String getLogisticalId() {
        return logisticalId;
    }

    public void setLogisticalId(String logisticalId) {
        this.logisticalId = logisticalId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(String carrierNum) {
        this.carrierNum = carrierNum;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUnloadingWeight() {
        return unloadingWeight;
    }

    public void setUnloadingWeight(String unloadingWeight) {
        this.unloadingWeight = unloadingWeight;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getUnloadingPhoto() {
        return unloadingPhoto;
    }

    public void setUnloadingPhoto(String unloadingPhoto) {
        this.unloadingPhoto = unloadingPhoto;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getUnloadingTime() {
        return unloadingTime;
    }

    public void setUnloadingTime(String unloadingTime) {
        this.unloadingTime = unloadingTime;
    }

    public String getCargoNum() {
        return cargoNum;
    }

    public void setCargoNum(String cargoNum) {
        this.cargoNum = cargoNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLoadingWeight() {
        return loadingWeight;
    }

    public void setLoadingWeight(String loadingWeight) {
        this.loadingWeight = loadingWeight;
    }

    public String getLoadingPhoto() {
        return loadingPhoto;
    }

    public void setLoadingPhoto(String loadingPhoto) {
        this.loadingPhoto = loadingPhoto;
    }

    public String getOwnerCargoId() {
        return ownerCargoId;
    }

    public void setOwnerCargoId(String ownerCargoId) {
        this.ownerCargoId = ownerCargoId;
    }
}
