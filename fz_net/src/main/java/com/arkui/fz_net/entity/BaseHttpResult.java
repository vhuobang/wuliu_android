package com.arkui.fz_net.entity;

import java.io.Serializable;

/**
 * Created by wjn on 2016/7/4.
 * 请求数据结果基类
 */
public class BaseHttpResult<T> implements Serializable {


    private int code;//错误状态

    private T data;//数据模板data

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;//错误消息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("code=" + code);
        if (null != data) {
            sb.append(" data:" + data.toString());
        }
        return sb.toString();
    }
}
