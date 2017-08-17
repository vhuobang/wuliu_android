package com.arkui.fz_tools.entity;

/**
 * Created by nmliz on 2017/8/15.
 */

public class LogisticsBusEntity {

    //区分搜索和列表的
    public static final int SEARCH_LOGISTICS=101;
    public static final int LOGISTICS=102;

    private int type;
    private int position;
    private String status;

    public LogisticsBusEntity(int type,int position, String status) {
        this.position = position;
        this.status = status;
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
