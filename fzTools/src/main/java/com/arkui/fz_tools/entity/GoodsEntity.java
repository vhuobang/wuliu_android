package com.arkui.fz_tools.entity;

/**
 * Created by nmliz on 2017/2/28.
 */

public class GoodsEntity {

    /**
     * goods_id : 5
     * goods_name : 可口可乐荣誉出品 可乐600ml/24瓶箱装 夏日畅饮
     * goods_number : 1000
     * market_price : 0.00
     * shop_price : 64.70
     * goods_thumb : http://s-295498.gotocdn.com/canhe/Uploads/Goods/20160929/57ed1e841936c2.png
     * give_integral : -1
     * goods_brief :
     * is_hot : 0
     * is_new : 1
     * goods_img :
     */

    private String goods_id;
    private String goods_name;
    private String goods_number;
    private String market_price;
    private String shop_price;
    private String goods_thumb;
    private String give_integral;
    private String goods_brief;
    private String is_hot;
    private String is_new;
    private String goods_img;
    private String sale_number;

    public String getSale_quantity() {
        return sale_quantity;
    }

    public void setSale_quantity(String sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    private String sale_quantity;

    public String getSale_number() {
        return sale_number;
    }

    public void setSale_number(String sale_number) {
        this.sale_number = sale_number;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getGive_integral() {
        return give_integral;
    }

    public void setGive_integral(String give_integral) {
        this.give_integral = give_integral;
    }

    public String getGoods_brief() {
        return goods_brief;
    }

    public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }
}
