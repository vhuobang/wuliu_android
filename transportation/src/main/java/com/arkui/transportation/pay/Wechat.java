package com.arkui.transportation.pay;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/9/18.
 * Author: XuDeLong
 */
public class Wechat {
    private Activity mContext;
   // public static final String WX_PARTNER_ID = Constants.WX_PARTNER_ID;

    IWXAPI msgApi;
    public Wechat(Activity mContext) {
        this.mContext = mContext;
        msgApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID);
        msgApi.registerApp(Constants.WX_APP_ID);
    }

    public void pay(WxPayEntity wxPayEntity) {
        try {
            PayReq req = new PayReq();
            req.appId = wxPayEntity.getAppid();
            req.partnerId = wxPayEntity.getPartnerid();
            req.prepayId = wxPayEntity.getPrepayid();
            req.nonceStr = wxPayEntity.getNoncestr();
            req.timeStamp = String.valueOf(wxPayEntity.getTimestamp());
            req.packageValue = "Sign=WXPay";
            req.sign = wxPayEntity.getSign();
            req.extData = "app data"; // optional
            // Toast.makeText(this.mContext, "正常调起支付", Toast.LENGTH_SHORT).show();
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            Boolean aa = msgApi.sendReq(req);
            //Toast.makeText(this.mContext, "正常调起支付"+ aa, Toast.LENGTH_SHORT).show()
        } catch (Exception e) {
            Log.e("PAY_GET", "异常：" + e.getMessage());
            Toast.makeText(this.mContext, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
