package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/10.
 */

public class InviteFriendEntity {

    /**
     * mobile : 18735100047
     * nickname : qwesss
     * id : 2
     * avatar : /Public/images/defult.jpg
     * type : 2
     */

    @SerializedName("mobile")
    private String mobile;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("id")
    private String id;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("type")
    private String type;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
