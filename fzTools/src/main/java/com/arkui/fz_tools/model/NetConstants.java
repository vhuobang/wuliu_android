package com.arkui.fz_tools.model;

/**
 * Created by nmliz on 2017/8/7.
 */

public class NetConstants {
    public static final String BASE_URL =  "http://wuliufz.gotoip1.com/";
    //注册
    public static final String REGISTER=BASE_URL+"index.php/App/Users/register";
    //登录
    public static final String LOGIN=BASE_URL+"index.php/App/Users/login";
    //意见反馈  App/Public/freed_back
    public static final String FREED_BACK=BASE_URL+"index.php/App/Public/freed_back";
    // 消息列表  App/Notice/noticeList
    public static final String NOTICE_LIST=BASE_URL+"index.php/App/Notice/noticeList";
    // 邀请列表 index.php/App/Users/shareCode
    public static final String SHARE_CODE =BASE_URL+"index.php/App/Users/shareCode";
    // 修改密码 index.php/App/Users/forgetPassword
    public static final String FORGET_PASSWORD =BASE_URL+"index.php/App/Users/forgetPassword";
    // 用户详情  index.php/App/Users/userInfo
    public static final String USER_INFO =BASE_URL+"index.php/App/Users/userInfo";
    // 完善用户信息 index.php/App/Users/userEdit
    public static final String USER_EDIT =BASE_URL+"index.php/App/Users/userEdit";
    //上传图片
    public static final String IMAGE_UPLOAD=BASE_URL+"index.php/App/Upload/image_upload";
    //个人认证
    public static final String PERSONAL_AUTH=BASE_URL+"index.php/App/Users/personalAuth";
    //企业认证
    public static final String COMPANY_AUTH=BASE_URL+"index.php/App/Users/companyAuth";
    //我的好友列表  index.php/App/Users/friendsList
    public static final String FRIENDS_LIST=BASE_URL+"index.php/App/Users/friendsList";
    // 消息已读
    public static final String READ_MESSAGE=BASE_URL+"index.php/App/Notice/read_message";
    //获取物流列表
    public static final String LOGISTICAL_LIST=BASE_URL+"index.php/App/logistical/logisticalList";
}
