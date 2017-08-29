package com.arkui.transportation_shipper.common.entity;

/**
 * Created by nmliz on 2017/8/25.
 */

public class TruckListEntity {

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
    private Object created_at;

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

    public Object getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Object created_at) {
        this.created_at = created_at;
    }
}
