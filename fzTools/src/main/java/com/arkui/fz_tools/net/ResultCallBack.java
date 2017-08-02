package com.arkui.fz_tools.net;

import org.json.JSONObject;

/**
 * 类描述 网络请求回调接口
 * 创建者 任少东
 * 创建时间 2016/5/27 0027 11:23
 */
public abstract class ResultCallBack {
    public void onFinish() {

    }

    public void onFailed() {

    }

    //Code 0的回调
    public void onError(String str) {

    }

    public void onPay(String str){

    }

    public void onLoading(long total, long current, boolean isUploading) {
    }

    public void onSuccess(JSONObject object) {
    }

    public void onSuccess(String string) {
    }

    public abstract void onSuccess(JsonData data);
}
