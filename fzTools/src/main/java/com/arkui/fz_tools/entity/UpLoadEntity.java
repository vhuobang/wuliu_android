package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nmliz on 2017/4/7.
 */

public class UpLoadEntity {

    /**
     * cut_img : Uploads/avatar/2017-03-31/thumb_14909573933963.png
     * ori_img : Uploads/avatar/2017-03-31/14909573933963.png
     * ori_img_url : http://www.deweiyi.me/Uploads/avatar/2017-03-31/14909573933963.png
     * cut_img_url : http://www.deweiyi.me/Uploads/avatar/2017-03-31/thumb_14909573933963.png
     */

    @SerializedName("cut_img")
    private String cutImg;
    @SerializedName("ori_img")
    private String oriImg;
    @SerializedName("ori_img_url")
    private String oriImgUrl;
    @SerializedName("cut_img_url")
    private String cutImgUrl;

    public String getCutImg() {
        return cutImg;
    }

    public void setCutImg(String cutImg) {
        this.cutImg = cutImg;
    }

    public String getOriImg() {
        return oriImg;
    }

    public void setOriImg(String oriImg) {
        this.oriImg = oriImg;
    }

    public String getOriImgUrl() {
        return oriImgUrl;
    }

    public void setOriImgUrl(String oriImgUrl) {
        this.oriImgUrl = oriImgUrl;
    }

    public String getCutImgUrl() {
        return cutImgUrl;
    }

    public void setCutImgUrl(String cutImgUrl) {
        this.cutImgUrl = cutImgUrl;
    }
}
