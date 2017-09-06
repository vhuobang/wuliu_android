package com.arkui.transportation_shipper.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.arkui.transportation_shipper.owner.entity.DriverDetailEntity;

import java.util.List;

/**
 * Created by nmliz on 2017/8/31.
 */

public class DriverListDetailEntity implements Parcelable {

    /**
     * name : 秦师傅
     * mobile : 18666666666
     * password : 123456
     * out_num : 0
     * id_photo_front : null
     * id_photo_back : null
     * id_photo_hold : null
     * drive_photo : null
     * driver_status : []
     */

    private String name;
    private String mobile;
    private String password;
    private String out_num;
    private String id_photo_front;
    private String id_photo_back;
    private String id_photo_hold;
    private String drive_photo;
    private String origin_password;

    public String getOrigin_password() {
        return origin_password;
    }

    public void setOrigin_password(String origin_password) {
        this.origin_password = origin_password;
    }

    private List<DriverDetailEntity> driver_status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOut_num() {
        return out_num;
    }

    public void setOut_num(String out_num) {
        this.out_num = out_num;
    }

    public String getId_photo_front() {
        return id_photo_front;
    }

    public void setId_photo_front(String id_photo_front) {
        this.id_photo_front = id_photo_front;
    }

    public String getId_photo_back() {
        return id_photo_back;
    }

    public void setId_photo_back(String id_photo_back) {
        this.id_photo_back = id_photo_back;
    }

    public String getId_photo_hold() {
        return id_photo_hold;
    }

    public void setId_photo_hold(String id_photo_hold) {
        this.id_photo_hold = id_photo_hold;
    }

    public String getDrive_photo() {
        return drive_photo;
    }

    public void setDrive_photo(String drive_photo) {
        this.drive_photo = drive_photo;
    }

    public List<DriverDetailEntity> getDriver_status() {
        return driver_status;
    }

    public void setDriver_status(List<DriverDetailEntity> driver_status) {
        this.driver_status = driver_status;
    }

    public DriverListDetailEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.mobile);
        dest.writeString(this.password);
        dest.writeString(this.out_num);
        dest.writeString(this.id_photo_front);
        dest.writeString(this.id_photo_back);
        dest.writeString(this.id_photo_hold);
        dest.writeString(this.drive_photo);
        dest.writeString(this.origin_password);
    }

    protected DriverListDetailEntity(Parcel in) {
        this.name = in.readString();
        this.mobile = in.readString();
        this.password = in.readString();
        this.out_num = in.readString();
        this.id_photo_front = in.readString();
        this.id_photo_back = in.readString();
        this.id_photo_hold = in.readString();
        this.drive_photo = in.readString();
        this.origin_password = in.readString();
    }

    public static final Creator<DriverListDetailEntity> CREATOR = new Creator<DriverListDetailEntity>() {
        @Override
        public DriverListDetailEntity createFromParcel(Parcel source) {
            return new DriverListDetailEntity(source);
        }

        @Override
        public DriverListDetailEntity[] newArray(int size) {
            return new DriverListDetailEntity[size];
        }
    };
}
