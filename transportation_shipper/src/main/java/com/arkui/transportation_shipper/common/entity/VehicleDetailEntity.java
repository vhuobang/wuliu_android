package com.arkui.transportation_shipper.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by nmliz on 2017/8/30.
 */

public class VehicleDetailEntity {

    /**
     * truck_detail : {"id":"3","user_id":"14","license_plate":"京B666666","type":"0","singular_num":"0","truck_poto":"Uploads/Avatar/2017-08-28/15039124235153.png","driving_license_photo":"Uploads/Avatar/2017-08-28/15039124382406.png","created_at":null}
     * truck_status : []
     */

    private TruckDetailBean truck_detail;
    private List<TruckStatusEntity> truck_status;

    public TruckDetailBean getTruck_detail() {
        return truck_detail;
    }

    public void setTruck_detail(TruckDetailBean truck_detail) {
        this.truck_detail = truck_detail;
    }

    public List<TruckStatusEntity> getTruck_status() {
        return truck_status;
    }

    public void setTruck_status(List<TruckStatusEntity> truck_status) {
        this.truck_status = truck_status;
    }

    public static class TruckDetailBean implements Parcelable {
        /**
         * id : 3
         * user_id : 14
         * license_plate : 京B666666
         * type : 0
         * singular_num : 0
         * truck_poto : Uploads/Avatar/2017-08-28/15039124235153.png
         * driving_license_photo : Uploads/Avatar/2017-08-28/15039124382406.png
         * created_at : null
         */

        private String id;
        private String user_id;
        private String license_plate;
        private String type;
        private String singular_num;
        private String truck_poto;
        private String driving_license_photo;
        private String created_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLicense_plate() {
            return license_plate;
        }

        public void setLicense_plate(String license_plate) {
            this.license_plate = license_plate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSingular_num() {
            return singular_num;
        }

        public void setSingular_num(String singular_num) {
            this.singular_num = singular_num;
        }

        public String getTruck_poto() {
            return truck_poto;
        }

        public void setTruck_poto(String truck_poto) {
            this.truck_poto = truck_poto;
        }

        public String getDriving_license_photo() {
            return driving_license_photo;
        }

        public void setDriving_license_photo(String driving_license_photo) {
            this.driving_license_photo = driving_license_photo;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.user_id);
            dest.writeString(this.license_plate);
            dest.writeString(this.type);
            dest.writeString(this.singular_num);
            dest.writeString(this.truck_poto);
            dest.writeString(this.driving_license_photo);
            dest.writeString(this.created_at);
        }

        public TruckDetailBean() {
        }

        protected TruckDetailBean(Parcel in) {
            this.id = in.readString();
            this.user_id = in.readString();
            this.license_plate = in.readString();
            this.type = in.readString();
            this.singular_num = in.readString();
            this.truck_poto = in.readString();
            this.driving_license_photo = in.readString();
            this.created_at = in.readString();
        }

        public static final Parcelable.Creator<TruckDetailBean> CREATOR = new Parcelable.Creator<TruckDetailBean>() {
            @Override
            public TruckDetailBean createFromParcel(Parcel source) {
                return new TruckDetailBean(source);
            }

            @Override
            public TruckDetailBean[] newArray(int size) {
                return new TruckDetailBean[size];
            }
        };
    }
}
