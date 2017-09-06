package com.arkui.transportation_shipper.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nmliz on 2017/9/5.
 */

public class OrderEntity implements Parcelable {

    /**
     * infomation_fee : 123456.00
     * order_number : 20170903615141504435805
     */

    private String infomation_fee;
    private String order_number;
    private String logistical_name;

    public String getLogistical_name() {
        return logistical_name;
    }

    public void setLogistical_name(String logistical_name) {
        this.logistical_name = logistical_name;
    }

    public String getInfomation_fee() {
        return infomation_fee;
    }

    public void setInfomation_fee(String infomation_fee) {
        this.infomation_fee = infomation_fee;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public OrderEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.infomation_fee);
        dest.writeString(this.order_number);
        dest.writeString(this.logistical_name);
    }

    protected OrderEntity(Parcel in) {
        this.infomation_fee = in.readString();
        this.order_number = in.readString();
        this.logistical_name = in.readString();
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel source) {
            return new OrderEntity(source);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };
}
