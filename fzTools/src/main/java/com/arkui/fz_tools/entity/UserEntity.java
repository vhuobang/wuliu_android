package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nmliz on 2017/8/7.
 */

public class UserEntity implements Serializable{

    /**
     * id : 1
     * type : 1
     * mobile : 18647624075
     * nickname : 18647****75
     * avatar : /Public/images/defult.jpg
     * qq : null
     * integral : 0.00
     * balance : 0.00
     * send_num : 0
     * star_rating : 0.00
     * status : 0
     * invitation_code : null
     * is_user_certificate 个人认证
     * is_company_certificate // 公司认证
     * register_time : 2017-08-07 15:40:55
     */

    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("qq")
    private String qq;
    @SerializedName("integral")
    private String integral;
    @SerializedName("balance")
    private String balance;
    @SerializedName("send_num")
    private String sendNum;
    @SerializedName("star_rating")
    private String starRating;
    @SerializedName("status")
    private String status;
    @SerializedName("invitation_code")
    private String invitationCode;
    @SerializedName("register_time")
    private String registerTime;
    //is_user_certificate 个人认证
    @SerializedName("is_user_certificate")
    private String isUserCertificate;
    // 公司认证  is_company_certificate
    @SerializedName("is_company_certificate")
    private String isCompanyCertificate;

    public String getIsUserCertificate() {
        return isUserCertificate;
    }

    public void setIsUserCertificate(String isUserCertificate) {
        this.isUserCertificate = isUserCertificate;
    }

    public String getIsCompanyCertificate() {
        return isCompanyCertificate;
    }

    public void setIsCompanyCertificate(String isCompanyCertificate) {
        this.isCompanyCertificate = isCompanyCertificate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
