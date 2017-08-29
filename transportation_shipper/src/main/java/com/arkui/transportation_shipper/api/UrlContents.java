package com.arkui.transportation_shipper.api;

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
    public static final String TRUCK_LIST = BASE_URL + "/App/TruckOwner/truck_list";
    //添加车辆
    public static final String TRUCK_ADD = BASE_URL + "App/TruckOwner/truck_add";
}
