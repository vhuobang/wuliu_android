package com.arkui.fz_tools.model;

/**
 * Created by nmliz on 2016/6/16.
 * 常量类
 */
public class Constants {
    /***********************
     * SP
     *************************/
   /* public static final String REMEMBER_PREFERENCE = "user";
    public static final String PHONE = "phone";
    public static final String PWD = "pwd";
    public static final String USER_NICKNAME = "nickname";
    public static final String USER_SEX = "sex";
    public static final String ISREMEMBER = "isRemember";
    public static final String USER_ID = "userId";*/
    public static final String USER_OBJECT = "userObject";
    public static final String SP_NAME = "onlyDe";
    public static final String IS_LOGIN = "is_login";
    public static final String IS_INIT = "is_init";
    /***********************
     * SP
     *************************/

    //最新
    public static final String NEW="is_new";
    public static final String HOT="is_popular";
    public static final String PRICE_UP="price_up";
    public static final String PRICE_DOWN="price_down";
    //时间
    public static final String START_TIME="start_time";
    public static final String END_TIME="end_time";
    //时间戳格式
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    //身份类别 1、货主；2、物流；3、车主；4、司机
    public static final int OWNER=1;
    public static final int LOGISTICS=2;
    public static final int CAR_OWNER=3;
    public static final int DRIVER=4;
    // 支付 type
    public static final String CAR_OWNER_PAY="1"; // 车主端
    public static final String OWNER_PAY="2";  //  货主
    public static final String  LOGISTICS_PAY="3"; // 物流
  //   货主
    public static  final  String CARGO_SOFTWARE_DETAILS = NetConstants.BASE_URL+ "App/Public/cargo_software_details";
    //司机 App/Public/driver_software_details
    public static  final  String DRIVER_SOFTWARE_DETAILS = NetConstants.BASE_URL+ "App/Public/driver_software_details";
    //物流 App/Public/log_software_details
    public static  final  String LOG_SOFTWARE_DETAILS = NetConstants.BASE_URL+ "App/Public/log_software_details";
    // 车主 App/Public/car_software_details
    public static  final  String CAR_SOFTWARE_DETAILS = NetConstants.BASE_URL+ "App/Public/car_software_details";



}
