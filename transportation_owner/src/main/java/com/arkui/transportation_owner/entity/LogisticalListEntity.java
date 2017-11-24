package com.arkui.transportation_owner.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nmliz on 2017/8/11.
 */

public class LogisticalListEntity {

    /**
     * id : 11
     * name : 秦
     * short_name :
     * logo : Uploads/Avatar/2017-09-22/15060478741009.png
     * address : 北京-东城区
     * tel : 18612414195
     * handler_name : 秦
     * register_time : 2017-09-21 17:31:32
     * volume : 0
     * star_rating : 5.00
     * register_year : 0.010516679350583
     * auth_status : 1
     * status : 1
     * log_contact_name : null
     * log_contact_tel : null
     */

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("logo")
    private String logo;
    @SerializedName("address")
    private String address;
    @SerializedName("tel")
    private String tel;
    @SerializedName("handler_name")
    private String handlerName;
    @SerializedName("register_time")
    private String registerTime;
    @SerializedName("volume")
    private String volume;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("register_year")
    private String registerYear;
    @SerializedName("auth_status")
    private String authStatus;
    @SerializedName("status")
    private String status;
    @SerializedName("log_contact_name")
    private String logContactName;
    @SerializedName("log_contact_tel")
    private String logContactTel;
    private  boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
}
