package com.arkui.transportation_shipper.common.api;

/**
 * Created by 84658 on 2017/8/7.
 */

public class UrlContents {
    // 服务器根地址
    //   public static final String BASE_URL = "http://shunfengche.181858.com/index.php/";
    //public static final String BASE_URL = "http://wuliu.181858.com/index.php/";
    public static final String BASE_URL = "http://wuliufz.gotoip1.com/index.php/";
    //1. 注册
    public static final String REGISTER = BASE_URL + "App/Users/register";
    //2.登陆
    public static final String LOGIN = BASE_URL + "App/Users/login";
    // 3.生成邀请码 App/Users/shareCode
    public static final String SHARE_CODE = BASE_URL + "App/Users/shareCode";
    // 4.App/Users/forgetPassword 忘记密码
    public static final String FORGET_PASSWORD = BASE_URL + "App/Users/forgetPassword";
    // 5. App/Users/userInfo 用户信息
    public static final String USER_INFO = BASE_URL + "App/Users/userInfo";
    // 6.App/Users/userEdit 完善信息
    public static final String USER_EDIT = BASE_URL + "App/Users/userEdit";
    //车辆列表
    public static final String TRUCK_LIST = BASE_URL + "App/TruckOwner/truck_list";
    //添加车辆
    public static final String TRUCK_ADD = BASE_URL + "App/TruckOwner/truck_add";
    //货源列表
    public static final String OWNER_CARGO_LIST = BASE_URL + "App/logistical/ownerCargoList";
    //车型
    public static final String CAR_TYPE = BASE_URL + "App/TruckOwner/car_type";
    //司机列表
    public static final String DRIVER_LIST=BASE_URL+"App/TruckOwner/driver_list";
    //添加司机
    public static final String DRIVER_ADD=BASE_URL+"App/TruckOwner/driver_add";
    //车辆详情
    public static final String TRUCK_DETAIL=BASE_URL+"App/TruckOwner/truck_detail";
    //删除车辆
    public static final String TRUCK_DEL =BASE_URL+"App/TruckOwner/truck_del";
    //编辑车辆
    public static final String TRUCK_EDIT =BASE_URL+"App/TruckOwner/truck_edit";
    //货源列表进入详情
    public static final String CARGO_LIST_DETAIL =BASE_URL+"App/TruckOwner/cargo_list_detail";
    //司机详情
    public static final String DRIVER_DETAIL =BASE_URL+"App/TruckOwner/driver_detail";
    //编辑司机
    public static final String DRIVER_EDIT =BASE_URL+"App/TruckOwner/driver_edit";
    //删除司机
    public static final String DRIVER_DEL =BASE_URL+"App/TruckOwner/driver_del";
    //选择司机列表
    public static final String DRIVER_LIST_BY_TRUCK =BASE_URL+"App/TruckOwner/driver_list_by_truck";
    //选择车辆
    public static final String DRIVER_TRUCK_LIST =BASE_URL+"App/TruckOwner/driver_truck_list";
    //货源详情抢单
    public static final String GET_WAYBILL =BASE_URL+"App/TruckOwner/get_waybill";
    //获取未支付订单
    public static final String IS_SETTLE =BASE_URL+"App/Logistical/is_settle";
    //支付信息费
    public static final String PAY =BASE_URL+"App/Finance/pay";
     //车主端运单详情  App/TruckOwner/waybill_details
     public static final String TRUCK_OWNER_WAYBILL_DETAILS =BASE_URL+"App/TruckOwner/waybill_details";
    // 司机端运单列表
    public static final String DRIVER_ORDER_LIST =BASE_URL+"App/Driver/driver_order_list";
    //司机端运单列表详情
    public static final String DRIVER_ORDER_DETAIL =BASE_URL+"App/Driver/driver_order_detail";
    // 提交装货榜单 App/Driver/loading_submit
    public static final String LOADING_SUBMIT =BASE_URL+"App/Driver/loading_submit";
    // 提交卸货磅单 App/Driver/unload
    public static final String UNLOAD_SUBMIT =BASE_URL+"App/Driver/unload";
  // 查看磅单详情  App/Driver/loading_list_detail
  public static final String LOADING_LIST_DETAIL =BASE_URL+"App/Driver/loading_list_detail";
   // 司机端提交经纬度
   public static final String DRIVER_POSITION =BASE_URL+"App/TruckOwner/driver_position";

}
