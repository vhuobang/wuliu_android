package com.arkui.transportation_shipper.owner.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/9/1.
 */

public class TruckOwnerWaybillDetialEntity {

    /**
     * carrier_num : 20
     * truck_id : 5
     * cargo_id
     * logistical_id
     * user_id
     * unit : 1
     * unloading_photo :
     * license_plate : 京A666666
     * loading_time : 2017-08-14 10:33:32
     * unloading_time : 2017-08-14 10:33:45
     * name : 李师傅
     * mobile : 18647624075
     * loading_weight : 500.00
     * unloading_weight : 200.00
     * loading_photo :
     * order_num
     * total_money
     * lat
     * log
     * message_status
     *
     * owner_cargo_id
     *   "cargo_evaluate_status": "0",
     "log_evaluate_status": "0",
     "car_evaluate_status": "0",
     order_money
     */
    @SerializedName("cargo_evaluate_status")
    private String cargo_evaluate_status;
    @SerializedName("log_evaluate_status")
    private String log_evaluate_status;
    @SerializedName("car_evaluate_status")
    private String car_evaluate_status;

    public String getCargo_evaluate_status() {
        return cargo_evaluate_status;
    }

    public void setCargo_evaluate_status(String cargo_evaluate_status) {
        this.cargo_evaluate_status = cargo_evaluate_status;
    }

    public String getLog_evaluate_status() {
        return log_evaluate_status;
    }

    public void setLog_evaluate_status(String log_evaluate_status) {
        this.log_evaluate_status = log_evaluate_status;
    }

    public String getCar_evaluate_status() {
        return car_evaluate_status;
    }

    public void setCar_evaluate_status(String car_evaluate_status) {
        this.car_evaluate_status = car_evaluate_status;
    }

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("truck_id")
    private String truckId;
    @SerializedName("unit")
    private String unit;
    @SerializedName("unloading_photo")
    private String unloadingPhoto;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("unloading_time")
    private String unloadingTime;
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("loading_weight")
    private String loadingWeight;
    @SerializedName("unloading_weight")
    private String unloadingWeight;
    @SerializedName("loading_photo")
    private String loadingPhoto;
    @SerializedName("logistical_id")
    private String logisticalId; // 物流id
    @SerializedName("cargo_id")
    private String cargoId; //货源id
    @SerializedName("user_id") //货主id
    private String userId;
    @SerializedName("order_number") //运单号
    private String orderNum;
    @SerializedName("driver_id")
    private String driverId;
    @SerializedName("log")
    private String log;
    @SerializedName("lat")
    private String lat;

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    @SerializedName("order_money")
    private String orderMoney;

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    @SerializedName("message_status")
    private String messageStatus;

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

    public String getmTotalMoney() {
        return mTotalMoney;
    }

    public void setmTotalMoney(String mTotalMoney) {
        this.mTotalMoney = mTotalMoney;
    }

    @SerializedName("total_money")
    private String mTotalMoney;

    public String getmOwnerCargoId() {
        return mOwnerCargoId;
    }

    public void setmOwnerCargoId(String mOwnerCargoId) {
        this.mOwnerCargoId = mOwnerCargoId;
    }

    @SerializedName("owner_cargo_id") //运单号
    private String mOwnerCargoId;
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    public String getTotalMoney() {
        return mTotalMoney;
    }

    public void setTotalMoney(String mTotalMoney) {
        this.mTotalMoney = mTotalMoney;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(String carrierNum) {
        this.carrierNum = carrierNum;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnloadingPhoto() {
        return unloadingPhoto;
    }

    public void setUnloadingPhoto(String unloadingPhoto) {
        this.unloadingPhoto = unloadingPhoto;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLoadingTime() {
        return loadingTime==null?"":loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getUnloadingTime() {
        return unloadingTime==null?"":unloadingTime;
    }

    public void setUnloadingTime(String unloadingTime) {
        this.unloadingTime = unloadingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoadingWeight() {
        return loadingWeight;
    }

    public void setLoadingWeight(String loadingWeight) {
        this.loadingWeight = loadingWeight;
    }

    public String getUnloadingWeight() {
        return unloadingWeight;
    }

    public void setUnloadingWeight(String unloadingWeight) {
        this.unloadingWeight = unloadingWeight;
    }

    public String getLoadingPhoto() {
        return loadingPhoto;
    }

    public void setLoadingPhoto(String loadingPhoto) {
        this.loadingPhoto = loadingPhoto;
    }
}
