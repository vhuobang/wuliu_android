package com.arkui.fz_tools.entity;

/**
 * Created by 任少东 on 2016/07/15 14:56
 */
public class RBanner {
    private String url;

    public int getUrl2() {
        return url2;
    }

    public void setUrl2(int url2) {
        this.url2 = url2;
    }

    private int url2;
    private int type;
    private String params;

    public RBanner() {
    }
    public RBanner(String url) {
        this.url = url;
    }

    public RBanner(int url2) {
        this.url2 = url2;
    }

    public RBanner(String url, int type, String params) {
        this.url = url;
        this.type = type;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
