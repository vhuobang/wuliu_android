package com.arkui.transportation_shipper.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/30.
 */

public class CargoListDetailEntity {

    /**
     * infomation_fee : 688.00
     * cargo_id : 10
     * loading_address : 北京-宣武区 北京站毛家湾胡同甲13号
     * unloading_address : 广东-广州 哈哈镜鸭脖子(人民北店)人民北路829-831号越富二期创兴广场1楼05-5号
     * cargo_name : 大米
     * cargo_num : 10
     * cargo_density : 12
     * freight_price : 1545.00
     * cargo_price : 2366.00
     * loading_time : 0000-00-00 00:00:00
     * payment_terms : 1
     * settlement_time : 1
     * press_charges : 500.00
     * remarks :
     * avatar : Uploads/Avatar/2017-08-25/15036329691882.png
     * register_time : 2017-08-10 15:47:38
     * send_num : 0
     * star_rating : 0.00
     * mobile : 18612414195
     * register_year : 0.055
     * surplus_num : 10
     * unit
     * name
     * volume 发送次数
     *
     *  "log_contact_name": "徐一号",
     "log_contact_tel": "13681721927",
     */
    @SerializedName("log_contact_name")
    private String logContactName;
    @SerializedName("log_contact_tel")
    private String logContactTel;

    public String getLogContactName() {
        return logContactName;
    }

    public void setLogContactName(String logContactName) {
        this.logContactName = logContactName;
    }

    public String getLogContactTel() {
        return logContactTel;
    }

    public void setLogContactTel(String logContactTel) {
        this.logContactTel = logContactTel;
    }

    @SerializedName("infomation_fee")
    private String infomationFee;
    @SerializedName("cargo_id")
    private String cargoId;
    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("cargo_density")
    private String cargoDensity;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("cargo_price")
    private String cargoPrice;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("payment_terms")
    private String paymentTerms;
    @SerializedName("settlement_time")
    private String settlementTime;
    @SerializedName("press_charges")
    private String pressCharges;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("register_time")
    private String registerTime;
    @SerializedName("send_num")
    private String sendNum;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("register_year")
    private String registerYear;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("unit")
    private String unit;
    @SerializedName("name")
    private String name;

    @SerializedName("volume")
    private String volume;


    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInfomationFee() {
        return infomationFee;
    }

    public void setInfomationFee(String infomationFee) {
        this.infomationFee = infomationFee;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
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

    public String getCargoDensity() {
        return cargoDensity;
    }

    public void setCargoDensity(String cargoDensity) {
        this.cargoDensity = cargoDensity;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(String cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getPressCharges() {
        return pressCharges;
    }

    public void setPressCharges(String pressCharges) {
        this.pressCharges = pressCharges;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }
}
