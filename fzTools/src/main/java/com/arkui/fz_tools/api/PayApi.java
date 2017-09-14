package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.WxPayEntity;
import com.arkui.fz_tools.model.NetConstants;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/9/14.
 */

public interface PayApi {
    // 微信支付 WEIXIN_PAY
    @FormUrlEncoded
    @POST(NetConstants.WEIXIN_PAY)
    Observable<BaseHttpResult<WxPayEntity>> getWxPay(@Field("user_id") String userId, @Field("pay_amount") String payAmount, @Field("pay_type") String wxpay
            , @Field("client_type") String clientType, @Field("type") String type);
    // 支付宝支付
    @FormUrlEncoded
    @POST(NetConstants.ALI_PAY)
    Observable<BaseHttpResult> getAli_Pay(@Field("user_id") String userId, @Field("pay_amount") String payAmount,@Field("type") String type);
}
