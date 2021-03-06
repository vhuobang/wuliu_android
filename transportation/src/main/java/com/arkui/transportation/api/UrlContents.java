package com.arkui.transportation.api;

/**
 * Created by 84658 on 2017/8/7.
 */

public class UrlContents {
    // 服务器根地址
    //   public static final String BASE_URL = "http://shunfengche.181858.com/index.php/";

    public static final String BASE_URL = "http://www.vhuobang.net/index.php/";
    //1.   接收到的货源列表
    public static final String CAR_GOTO_LOGISTICAL = BASE_URL + "App/logistical/cargoToLogistical";
    // 2.接收到货源的详情 (未发布)
    public static final String LOGISTICAL_RECEIVE_DETAILS = BASE_URL + "App/logistical/logisticalReceiveDetails";
   // 3. 物流转发货源
   public static final String LOGISTICAL_FORWARD_CARGO = BASE_URL + "App/logistical/logisticalForwardCargo";
   // 4. 物流端承运详情列表
   public static final String CARGO_CARRIER_LIST = BASE_URL + "App/logistical/cargoCarrierList";
    // 5.物流端已发布货源详情
    public static final String PUBLISH_DETAILS = BASE_URL + "App/logistical/publishDetails";
    // 6.货源状态已停止
    public static final String UP_CARGO_STATUS = BASE_URL + "App/logistical/upCargoStatus";
    //7  物流运单列表
    public static final String LOG_WAYBILL_LIST = BASE_URL + "App/Waybill/logWaybillList";
    // 8 首页轮播消息
    public static final String SLIDER_MESSAGE = BASE_URL + "App/Public/sliderMessage";
    // 9  货源搜索
    public static final String CARGO_TOLOGISTICAL_SEARCH = BASE_URL + "App/logistical/cargoToLogisticalSearch";
    //  10 物流待收货  App/Waybill/log_agency
    public static final  String LOG_AGENCY = BASE_URL+ "App/Waybill/log_agency";
}
