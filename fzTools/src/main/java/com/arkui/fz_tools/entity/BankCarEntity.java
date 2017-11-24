package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 84658 on 2017/8/22.
 */

public class BankCarEntity implements Serializable{

    /**
     * number : 6212260200138811936
     * note : 工商银行-牡丹卡普卡-借记卡
     * bank : icbc
     * user_id : 11
     * name : wyh
     * created_at : 2017-08-17 11:59:31
     * tel : 18734881922
     * id : 4
     * is_default : 0
     * icon
     */

    @SerializedName("number")
    private String number;
    @SerializedName("note")
    private String note;
    @SerializedName("bank")
    private String bank;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("tel")
    private String tel;
    @SerializedName("id")
    private String id;
    @SerializedName("is_default")
    private String isDefault;
    @SerializedName("icon")
    private String icon;
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
