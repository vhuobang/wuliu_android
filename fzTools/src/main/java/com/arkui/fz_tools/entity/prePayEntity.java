package com.arkui.fz_tools.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 84658 on 2017/9/7.
 */

public class PrePayEntity {

    /**
     * carrier_num : 2
     * cargo_price : 20.00
     * loss : 0
     * freight_price : 20.00
     * freight : 40
     * loss_money : 0
     * total_money : 40
     * loading_weight : 2.00
     * unloading_weight : 2.00
     * unit
     */

    @SerializedName("carrier_num")
    private String carrierNum;
    @SerializedName("cargo_price")
    private String cargoPrice;
    @SerializedName("loss")
    private String loss;
    @SerializedName("freight_price")
    private String freightPrice;
    @SerializedName("freight")
    private String freight;
    @SerializedName("loss_money")
    private String lossMoney;
    @SerializedName("total_money")
    private String totalMoney;
    @SerializedName("loading_weight")
    private String loadingWeight;
    @SerializedName("unloading_weight")
    private String unloadingWeight;
    @SerializedName("unit")
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(String carrierNum) {
        this.carrierNum = carrierNum;
    }

    public String getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(String cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getLossMoney() {
        return lossMoney;
    }

    public void setLossMoney(String lossMoney) {
        this.lossMoney = lossMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getLoadingWeight() {
        return loadingWeight;
    }

    public void setLoadingWeight(String loadingWeight) {
        this.loadingWeight = loadingWeight;
    }

    public String getUnloadingWeight() {
        return unloadingWeight;
    }

    public void setUnloadingWeight(String unloadingWeight) {
        this.unloadingWeight = unloadingWeight;
    }
}
