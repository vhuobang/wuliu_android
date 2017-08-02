package com.arkui.fz_tools.net;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by 任少东 on 2016/5/26 0026.
 */
public class JsonData {
    private String data;
    private String data1;
    private String error;
    private int code;

    public JsonData(String string) {
        try {
            JSONObject object = new JSONObject(string);
            code = object.optInt("code");
            data = object.optString("data");
            data1 = object.optString("data1");
            error= object.optString("error");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDataString() {
        return data;
    }

    public boolean isOk() {
        return "ok".equals(data);
    }

    public int getCode() {
        return code;
    }

    public JSONObject getData() {
        try {
            return new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getBean(Class<T> c) {
        try {
            return JSON.parseObject(data, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getList(Class<T> c) {
        try {
            return JSON.parseArray(data, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getBean(Class<T> c,String d){
        try {
            return JSON.parseObject(d, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getError() {
        return error;
    }
}
