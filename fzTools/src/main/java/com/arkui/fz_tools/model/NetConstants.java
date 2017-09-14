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
    //系统消息内容
    public static final String NOTICE_DETAILS=BASE_URL+"index.php/App/Notice/notice_details";
    // 运单详情
    public static final String WAYBILL_DETAILS=BASE_URL+"index.php/App/Waybill/waybill_details";
    //获取物流列表
    public static final String LOGISTICAL_LIST=BASE_URL+"index.php/App/logistical/logisticalList";
    //物流详情
    public static final String LOGISTICAL_DETAILS=BASE_URL+"index.php/App/logistical/logisticalDetails";
    //已发布 预发布列表
    public static final String CARGO_LIST=BASE_URL+"index.php/App/logistical/cargoList";
    //  预发布详情 index.php/App/logistical/releaseDetails
    public static final String RELEASE_DETAILS=BASE_URL+"index.php/App/logistical/releaseDetails";

    //收藏物流
    public static final String COLLECTION_LOGISTICAL=BASE_URL+"index.php/App/logistical/collectionLogistical";
    //发布
    public static final String ADD_CARGO=BASE_URL+"index.php/App/logistical/addCargo";
    //已收藏物流列表
    public static final String MY_COLLECTION = BASE_URL + "index.php/App/logistical/myCollection";
    // 添加银行卡
    public static final String ADD_BANK = BASE_URL + "index.php/App/Finance/addBank";
    //删除银行卡
    public static final String DEL_BANK = BASE_URL + "index.php/App/Finance/delBank";
    // 银行卡列表
    public static final String BANK_LIST = BASE_URL + "index.php/App/Finance/bankList";
    // 资金明细
    public static final String BILLING_DETAILS = BASE_URL + "index.php/App/Finance/billingDetails";
    // 积分提现明细
    public static final String INTEGRAL_DETAILS = BASE_URL + "index.php/App/Finance/integralDetails";
    // 信息费明细
    public static final String INFOMATION_FEEDETAILS = BASE_URL + "index.php/App/Finance/infomationFeeDetails";
    //  支付
    public static final String PAY = BASE_URL + "index.php/App/Finance/pay";
    // 貨主信息
    public static final String CARGO_OWNER_INFO = BASE_URL + "index.php/App/logistical/cargoOwnerInfo";
    //编辑
    public static final String EDIT_CARGO_INFO=BASE_URL+"index.php/App/logistical/editCargoInfo";
    //已发布详情
    public static final String PUBLISH_DETAILS=BASE_URL+"index.php/App/logistical/publishDetails";
    // 车主端和货主端公用 运单列表
    public static final String WAYBILL_LIST=BASE_URL+"index.php/App/Waybill/waybill_list";
    // 评价
    public static final String EVALUATE=BASE_URL+"index.php/App/Waybill/evaluate";
    //关于我们  App/Public/about_us
    public static final String ABOUT_US=BASE_URL+"index.php/App/Public/about_us";
  // 支付预览  /App/Finance/pre_pay
  public static final String PRE_PAY=BASE_URL+"index.php/App/Finance/pre_pay";
    // 微信充值
    public static final String WEIXIN_PAY=BASE_URL+"index.php/App/Recharge/create_recharge_order";
    // 支付宝支付  App/Recharge/alipay
    public static final String ALI_PAY=BASE_URL+"index.php/App/Recharge/alipay";
    //搜索历史  App/Logistical/searchList
    public static final String SEARCH_LIST=BASE_URL+"index.php/App/Logistical/searchList";
    // 清空搜索历史 App/Logistical/delSearch
    public static final String DEL_SEARCH=BASE_URL+"index.php/App/Logistical/delSearch";
    // 邀请好友分享
    public static final String FRIEND_SHARE=BASE_URL+"index.php/App/Public/friend_share";
    // 发货分享 App/Public/share
    public static final String SHARE=BASE_URL+"index.php/App/Public/share";
}
