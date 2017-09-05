package com.arkui.transportation_shipper.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/9/3.
 */

public class PoundListDetail {


    /**
     * unloading_photo : 4134113
     * loading_time : 0000-00-00 00:00:00
     * unloading_time : null
     * loading_weight : 0.00
     * unloading_weight : null
     * loading_photo : 12343241324
     */

    @SerializedName("unloading_photo")
    private String unloadingPhoto;
    @SerializedName("loading_time")
    private String loadingTime;
    @SerializedName("unloading_time")
    private String unloadingTime;
    @SerializedName("loading_weight")
    private String loadingWeight;
    @SerializedName("unloading_weight")
    private String unloadingWeight;
    @SerializedName("loading_photo")
    private String loadingPhoto;

    public String getUnloadingPhoto() {
        return unloadingPhoto;
    }

    public void setUnloadingPhoto(String unloadingPhoto) {
        this.unloadingPhoto = unloadingPhoto;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getUnloadingTime() {
        return unloadingTime;
    }

    public void setUnloadingTime(String unloadingTime) {
        this.unloadingTime = unloadingTime;
    }

    public String getLoadingWeight() {
        return loadingWeight;
    }

    public void setLoadingWeight(String loadingWeight) {
        this.loadingWeight = loadingWeight;
    }

    public String getUnloadingWeight() {
        return unloadingWeight;
    }

    public void setUnloadingWeight(String unloadingWeight) {
        this.unloadingWeight = unloadingWeight;
    }

    public String getLoadingPhoto() {
        return loadingPhoto;
    }

    public void setLoadingPhoto(String loadingPhoto) {
        this.loadingPhoto = loadingPhoto;
    }
}
