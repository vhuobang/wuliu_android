package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/8/16.
 */

public class WayBillDetailEntity {

    /**
     * //物流信息
     * cargo_info : {"loading_address":"111","cargo_price":"0.00","cargo_density":"0","press_charges":"0.00","cargo_status":"0","unloading_contact":"","logistical_status":"0","created_at":null,"payment_terms":"0","type":"0","settlement_time":"0000-00-00 00:00:00","user_id":"1","truck_drawer":"","freight_price":"0.00","unloading_tel":"","loading_time":"0000-00-00 00:00:00","unloading_address":"1111","cargo_num":"0","cargo_name":"aaa","truck_tel":"","id":"1","remarks":null}
     * driver_info : {"license_plate":"3"} //车牌号
     * //货主信息
     * owner_info : {"send_num":"0","unloading_contact":"","truck_drawer":"","unloading_tel":"","star_rating":"0.00","name":"eee","mobile":"eee","truck_tel":"","register_time":"0000-00-00 00:00:00"}
     * //车主信息
     * owner_driver_info : {"name":"eee","mobile":"eee"}
     * //
     * logistics_info : {"volume":"0","address":"","user_id":"1","handler_name":"","star_rating":"0.00","name":"111","created_at":null,"short_name":"","tel":"","id":"1","register_time":"0000-00-00 00:00:00"}
     * //运单详情
     * waybill_info : {"carrier_num":"0","truck_id":"3","driver_id":"1","cargo_status":"0","cargo_id":"1","order_number":"","created_at":null,"evaluate_status":null,"unloading_weight":null,"logistics_id":"3","message_status":"0","unloading_photo":null,"user_id":"1","loading_time":"0000-00-00 00:00:00","unloading_time":null,"owner_status":"0","id":"1","loading_weight":"0.00","owner_cargo_id":"1","loading_photo":"12343241324"}
     */

    @SerializedName("cargo_info")
    private CargoInfoBean cargoInfo;
    @SerializedName("driver_info")
    private DriverInfoBean driverInfo;
    @SerializedName("owner_info")
    private OwnerInfoBean ownerInfo;
    @SerializedName("owner_driver_info")
    private OwnerDriverInfoBean ownerDriverInfo;
    @SerializedName("logistics_info")
    private LogisticsInfoBean logisticsInfo;
    @SerializedName("waybill_info")
    private WaybillInfoBean waybillInfo;

    public CargoInfoBean getCargoInfo() {
        return cargoInfo;
    }

    public void setCargoInfo(CargoInfoBean cargoInfo) {
        this.cargoInfo = cargoInfo;
    }

    public DriverInfoBean getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfoBean driverInfo) {
        this.driverInfo = driverInfo;
    }

    public OwnerInfoBean getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(OwnerInfoBean ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public OwnerDriverInfoBean getOwnerDriverInfo() {
        return ownerDriverInfo;
    }

    public void setOwnerDriverInfo(OwnerDriverInfoBean ownerDriverInfo) {
        this.ownerDriverInfo = ownerDriverInfo;
    }

    public LogisticsInfoBean getLogisticsInfo() {
        return logisticsInfo;
    }

    public void setLogisticsInfo(LogisticsInfoBean logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
    }

    public WaybillInfoBean getWaybillInfo() {
        return waybillInfo;
    }

    public void setWaybillInfo(WaybillInfoBean waybillInfo) {
        this.waybillInfo = waybillInfo;
    }

    public static class CargoInfoBean {
        /**
         * loading_address : 111
         * cargo_price : 0.00
         * cargo_density : 0
         * press_charges : 0.00
         * cargo_status : 0
         * unloading_contact :
         * logistical_status : 0
         * created_at : null
         * payment_terms : 0
         * type : 0
         * settlement_time : 0000-00-00 00:00:00
         * user_id : 1
         * truck_drawer :
         * freight_price : 0.00
         * unloading_tel :
         * loading_time : 0000-00-00 00:00:00
         * unloading_address : 1111
         * cargo_num : 0
         * cargo_name : aaa
         * truck_tel :
         * id : 1
         * remarks : null
         */

        @SerializedName("loading_address")
        private String loadingAddress;
        @SerializedName("cargo_price")
        private String cargoPrice;
        @SerializedName("cargo_density")
        private String cargoDensity;
        @SerializedName("press_charges")
        private String pressCharges;
        @SerializedName("cargo_status")
        private String cargoStatus;
        @SerializedName("unloading_contact")
        private String unloadingContact;
        @SerializedName("logistical_status")
        private String logisticalStatus;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("payment_terms")
        private String paymentTerms;
        @SerializedName("type")
        private String type;
        @SerializedName("settlement_time")
        private String settlementTime;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("truck_drawer")
        private String truckDrawer;
        @SerializedName("freight_price")
        private String freightPrice;
        @SerializedName("unloading_tel")
        private String unloadingTel;
        @SerializedName("loading_time")
        private String loadingTime;
        @SerializedName("unloading_address")
        private String unloadingAddress;
        @SerializedName("cargo_num")
        private String cargoNum;
        @SerializedName("cargo_name")
        private String cargoName;
        @SerializedName("truck_tel")
        private String truckTel;
        @SerializedName("id")
        private String id;
        @SerializedName("remarks")
        private String remarks;

        public String getLoadingAddress() {
            return loadingAddress;
        }

        public void setLoadingAddress(String loadingAddress) {
            this.loadingAddress = loadingAddress;
        }

        public String getCargoPrice() {
            return cargoPrice;
        }

        public void setCargoPrice(String cargoPrice) {
            this.cargoPrice = cargoPrice;
        }

        public String getCargoDensity() {
            return cargoDensity;
        }

        public void setCargoDensity(String cargoDensity) {
            this.cargoDensity = cargoDensity;
        }

        public String getPressCharges() {
            return pressCharges;
        }

        public void setPressCharges(String pressCharges) {
            this.pressCharges = pressCharges;
        }

        public String getCargoStatus() {
            return cargoStatus;
        }

        public void setCargoStatus(String cargoStatus) {
            this.cargoStatus = cargoStatus;
        }

        public String getUnloadingContact() {
            return unloadingContact;
        }

        public void setUnloadingContact(String unloadingContact) {
            this.unloadingContact = unloadingContact;
        }

        public String getLogisticalStatus() {
            return logisticalStatus;
        }

        public void setLogisticalStatus(String logisticalStatus) {
            this.logisticalStatus = logisticalStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPaymentTerms() {
            return paymentTerms;
        }

        public void setPaymentTerms(String paymentTerms) {
            this.paymentTerms = paymentTerms;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSettlementTime() {
            return settlementTime;
        }

        public void setSettlementTime(String settlementTime) {
            this.settlementTime = settlementTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTruckDrawer() {
            return truckDrawer;
        }

        public void setTruckDrawer(String truckDrawer) {
            this.truckDrawer = truckDrawer;
        }

        public String getFreightPrice() {
            return freightPrice;
        }

        public void setFreightPrice(String freightPrice) {
            this.freightPrice = freightPrice;
        }

        public String getUnloadingTel() {
            return unloadingTel;
        }

        public void setUnloadingTel(String unloadingTel) {
            this.unloadingTel = unloadingTel;
        }

        public String getLoadingTime() {
            return loadingTime;
        }

        public void setLoadingTime(String loadingTime) {
            this.loadingTime = loadingTime;
        }

        public String getUnloadingAddress() {
            return unloadingAddress;
        }

        public void setUnloadingAddress(String unloadingAddress) {
            this.unloadingAddress = unloadingAddress;
        }

        public String getCargoNum() {
            return cargoNum;
        }

        public void setCargoNum(String cargoNum) {
            this.cargoNum = cargoNum;
        }

        public String getCargoName() {
            return cargoName;
        }

        public void setCargoName(String cargoName) {
            this.cargoName = cargoName;
        }

        public String getTruckTel() {
            return truckTel;
        }

        public void setTruckTel(String truckTel) {
            this.truckTel = truckTel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }

    public static class DriverInfoBean {
        /**
         * license_plate : 3
         */

        @SerializedName("license_plate")
        private String licensePlate;

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }
    }

    public static class OwnerInfoBean {
        /**
         * send_num : 0
         * unloading_contact :
         * truck_drawer :
         * unloading_tel :
         * star_rating : 0.00
         * name : eee
         * mobile : eee
         * truck_tel :
         * register_time : 0000-00-00 00:00:00
         */

        @SerializedName("send_num")
        private String sendNum;
        @SerializedName("unloading_contact")
        private String unloadingContact;
        @SerializedName("truck_drawer")
        private String truckDrawer;
        @SerializedName("unloading_tel")
        private String unloadingTel;
        @SerializedName("star_rating")
        private String starRating;
        @SerializedName("name")
        private String name;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("truck_tel")
        private String truckTel;
        @SerializedName("register_time")
        private String registerTime;

        public String getSendNum() {
            return sendNum;
        }

        public void setSendNum(String sendNum) {
            this.sendNum = sendNum;
        }

        public String getUnloadingContact() {
            return unloadingContact;
        }

        public void setUnloadingContact(String unloadingContact) {
            this.unloadingContact = unloadingContact;
        }

        public String getTruckDrawer() {
            return truckDrawer;
        }

        public void setTruckDrawer(String truckDrawer) {
            this.truckDrawer = truckDrawer;
        }

        public String getUnloadingTel() {
            return unloadingTel;
        }

        public void setUnloadingTel(String unloadingTel) {
            this.unloadingTel = unloadingTel;
        }

        public String getStarRating() {
            return starRating;
        }

        public void setStarRating(String starRating) {
            this.starRating = starRating;
        }

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

        public String getTruckTel() {
            return truckTel;
        }

        public void setTruckTel(String truckTel) {
            this.truckTel = truckTel;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }
    }

    public static class OwnerDriverInfoBean {
        /**
         * name : eee
         * mobile : eee
         */

        @SerializedName("name")
        private String name;
        @SerializedName("mobile")
        private String mobile;

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
    }

    public static class LogisticsInfoBean {
        /**
         * volume : 0
         * address :
         * user_id : 1
         * handler_name :
         * star_rating : 0.00
         * name : 111
         * created_at : null
         * short_name :
         * tel :
         * id : 1
         * register_time : 0000-00-00 00:00:00
         */

        @SerializedName("volume")
        private String volume;
        @SerializedName("address")
        private String address;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("handler_name")
        private String handlerName;
        @SerializedName("star_rating")
        private String starRating;
        @SerializedName("name")
        private String name;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("short_name")
        private String shortName;
        @SerializedName("tel")
        private String tel;
        @SerializedName("id")
        private String id;
        @SerializedName("register_time")
        private String registerTime;

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getHandlerName() {
            return handlerName;
        }

        public void setHandlerName(String handlerName) {
            this.handlerName = handlerName;
        }

        public String getStarRating() {
            return starRating;
        }

        public void setStarRating(String starRating) {
            this.starRating = starRating;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }
    }

    public static class WaybillInfoBean {
        /**
         * carrier_num : 0
         * truck_id : 3
         * driver_id : 1
         * cargo_status : 0
         * cargo_id : 1
         * order_number :
         * created_at : null
         * evaluate_status : null
         * unloading_weight : null
         * logistics_id : 3
         * message_status : 0
         * unloading_photo : null
         * user_id : 1
         * loading_time : 0000-00-00 00:00:00
         * unloading_time : null
         * owner_status : 0
         * id : 1
         * loading_weight : 0.00
         * owner_cargo_id : 1
         * loading_photo : 12343241324
         */

        @SerializedName("carrier_num")
        private String carrierNum;
        @SerializedName("truck_id")
        private String truckId;
        @SerializedName("driver_id")
        private String driverId;
        @SerializedName("cargo_status")
        private String cargoStatus;
        @SerializedName("cargo_id")
        private String cargoId;
        @SerializedName("order_number")
        private String orderNumber;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("evaluate_status")
        private String evaluateStatus;
        @SerializedName("unloading_weight")
        private String unloadingWeight;
        @SerializedName("logistics_id")
        private String logisticsId;
        @SerializedName("message_status")
        private String messageStatus;
        @SerializedName("unloading_photo")
        private String unloadingPhoto;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("loading_time")
        private String loadingTime;
        @SerializedName("unloading_time")
        private String unloadingTime;
        @SerializedName("owner_status")
        private String ownerStatus;
        @SerializedName("id")
        private String id;
        @SerializedName("loading_weight")
        private String loadingWeight;
        @SerializedName("owner_cargo_id")
        private String ownerCargoId;
        @SerializedName("loading_photo")
        private String loadingPhoto;

        public String getCarrierNum() {
            return carrierNum;
        }

        public void setCarrierNum(String carrierNum) {
            this.carrierNum = carrierNum;
        }

        public String getTruckId() {
            return truckId;
        }

        public void setTruckId(String truckId) {
            this.truckId = truckId;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getCargoStatus() {
            return cargoStatus;
        }

        public void setCargoStatus(String cargoStatus) {
            this.cargoStatus = cargoStatus;
        }

        public String getCargoId() {
            return cargoId;
        }

        public void setCargoId(String cargoId) {
            this.cargoId = cargoId;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getEvaluateStatus() {
            return evaluateStatus;
        }

        public void setEvaluateStatus(String evaluateStatus) {
            this.evaluateStatus = evaluateStatus;
        }

        public String getUnloadingWeight() {
            return unloadingWeight;
        }

        public void setUnloadingWeight(String unloadingWeight) {
            this.unloadingWeight = unloadingWeight;
        }

        public String getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(String logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getMessageStatus() {
            return messageStatus;
        }

        public void setMessageStatus(String messageStatus) {
            this.messageStatus = messageStatus;
        }

        public String getUnloadingPhoto() {
            return unloadingPhoto;
        }

        public void setUnloadingPhoto(String unloadingPhoto) {
            this.unloadingPhoto = unloadingPhoto;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLoadingTime() {
            return loadingTime;
        }

        public void setLoadingTime(String loadingTime) {
            this.loadingTime = loadingTime;
        }

        public String getUnloadingTime() {
            return unloadingTime;
        }

        public void setUnloadingTime(String unloadingTime) {
            this.unloadingTime = unloadingTime;
        }

        public String getOwnerStatus() {
            return ownerStatus;
        }

        public void setOwnerStatus(String ownerStatus) {
            this.ownerStatus = ownerStatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoadingWeight() {
            return loadingWeight;
        }

        public void setLoadingWeight(String loadingWeight) {
            this.loadingWeight = loadingWeight;
        }

        public String getOwnerCargoId() {
            return ownerCargoId;
        }

        public void setOwnerCargoId(String ownerCargoId) {
            this.ownerCargoId = ownerCargoId;
        }

        public String getLoadingPhoto() {
            return loadingPhoto;
        }

        public void setLoadingPhoto(String loadingPhoto) {
            this.loadingPhoto = loadingPhoto;
        }
    }
}
