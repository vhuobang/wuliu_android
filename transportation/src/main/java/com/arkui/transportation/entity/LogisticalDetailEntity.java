package com.arkui.transportation.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/15.
 */

public class LogisticalDetailEntity {

    /**
     * cargo_density : 1
     * press_charges : 1000.00
     * created_at : 2017-08-08 15:13:12
     * payment_terms : 1
     * type : 2
     * settlement_time : 0
     * send_num : 0
     * truck_drawer : wyh
     * loading_time : 2017-08-11 08:00:00
     * star_rating : 0.00
     * unloading_address : 上海
     * cargo_num : 1000
     * tel : 18735111111
     * id : 23
     * loading_address : 北京
     * cargo_price : 1000.00
     * unloading_contact : www
     * register_year : 0
     * user_id : 13
     * freight_price : 100.00
     * unloading_tel : 18735555555
     * name : wyh
     * cargo_name : 铁
     * truck_tel : 18735111111
     * remarks : 注意安全
     * surplus_num
     */

    @SerializedName("cargo_density")
    private String cargoDensity;
    @SerializedName("press_charges")
    private String pressCharges;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("payment_terms")
    private String paymentTerms;
    @SerializedName("type")
    private String type;
    @SerializedName("settlement_time")
    private String settlementTime;
    @SerializedName("send_num")
    private String sendNum;
    @SerializedName("truck_drawer")
    private String truckDrawer;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("unloading_address")
    private String unloadingAddress;
    @SerializedName("cargo_num")
    private String cargoNum;
    @SerializedName("tel")
    private String tel;
    @SerializedName("id")
    private String id;
    @SerializedName("loading_address")
    private String loadingAddress;
    @SerializedName("cargo_price")
    private String cargoPrice;
    @SerializedName("unloading_contact")
    private String unloadingContact;
    @SerializedName("register_year")
    private String registerYear;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("unloading_tel")
    private String unloadingTel;
    @SerializedName("name")
    private String name;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("truck_tel")
    private String truckTel;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("surplus_num")
    private String surplusNum;
    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }





    public String getCargoDensity() {
        return cargoDensity;
    }

    public void setCargoDensity(String cargoDensity) {
        this.cargoDensity = cargoDensity;
    }

    public String getPressCharges() {
        return pressCharges;
    }

    public void setPressCharges(String pressCharges) {
        this.pressCharges = pressCharges;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getTruckDrawer() {
        return truckDrawer;
    }

    public void setTruckDrawer(String truckDrawer) {
        this.truckDrawer = truckDrawer;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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

    public String getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(String cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public String getUnloadingContact() {
        return unloadingContact;
    }

    public void setUnloadingContact(String unloadingContact) {
        this.unloadingContact = unloadingContact;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getUnloadingTel() {
        return unloadingTel;
    }

    public void setUnloadingTel(String unloadingTel) {
        this.unloadingTel = unloadingTel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
