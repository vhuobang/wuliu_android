package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/9.
 */

public class ShareCodeEntity {

    /**
     * friends_num : 2
     * rand_num : lJNPDaw
     */

    @SerializedName("friends_num")
    private String friendsNum;
    @SerializedName("rand_num")
    private String randNum;

    public String getFriendsNum() {
        return friendsNum;
    }

    public void setFriendsNum(String friendsNum) {
        this.friendsNum = friendsNum;
    }

    public String getRandNum() {
        return randNum;
    }

    public void setRandNum(String randNum) {
        this.randNum = randNum;
    }
}
