package com.arkui.fz_tools.entity;

/**
 * Created by nmliz on 2016/11/10.
 */

public class SelectPicEntity {


    public SelectPicEntity() {
    }
    public SelectPicEntity(String url) {
        Url = url;
    }

    private  String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
