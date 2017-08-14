package com.arkui.transportation_owner.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nmliz on 2017/8/11.
 */

public class LogisticalListEntity {

    /**
     * id : 2
     * name : 测试公司
     * logo : /Public/images/defult.jpg
     * register_time : 2017-08-11 15:41:07
     * volume : 0
     * star_rating : 0.00
     * register_year : 0
     * status : 0
     */

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("register_time")
    private String registerTime;
    @SerializedName("volume")
    private String volume;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("register_year")
    private String registerYear;
    @SerializedName("status")
    private String status;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
