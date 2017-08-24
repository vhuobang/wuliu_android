package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/24.
 */

public class CargoOwnerInfoEntity  {

    /**
     * register_year : 0
     * send_num : 4
     * unloading_contact : www
     * truck_drawer : wyh
     * unloading_tel : 18735555555
     * star_rating : 0.00
     * name : wyh
     * tel : 18735100046
     * truck_tel : 18735111111
     */

    @SerializedName("register_year")
    private String registerYear;
    @SerializedName("send_num")
    private String sendNum;
    @SerializedName("unloading_contact")
    private String unloadingContact;
    @SerializedName("truck_drawer")
    private String truckDrawer;
    @SerializedName("unloading_tel")
    private String unloadingTel;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("name")
    private String name;
    @SerializedName("tel")
    private String tel;
    @SerializedName("truck_tel")
    private String truckTel;

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getUnloadingContact() {
        return unloadingContact;
    }

    public void setUnloadingContact(String unloadingContact) {
        this.unloadingContact = unloadingContact;
    }

    public String getTruckDrawer() {
        return truckDrawer;
    }

    public void setTruckDrawer(String truckDrawer) {
        this.truckDrawer = truckDrawer;
    }

    public String getUnloadingTel() {
        return unloadingTel;
    }

    public void setUnloadingTel(String unloadingTel) {
        this.unloadingTel = unloadingTel;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTruckTel() {
        return truckTel;
    }

    public void setTruckTel(String truckTel) {
        this.truckTel = truckTel;
    }
}
