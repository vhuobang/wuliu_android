package com.arkui.fz_tools.api;

import android.content.Context;

import com.arkui.fz_tools.net.LoadDataUtil;
import com.arkui.fz_tools.net.ResultCallBack;
import com.arkui.fz_tools.utils.Md5Util;

import java.util.HashMap;
import java.util.Map;

public class VerifyDao {
    private static VerifyDao instance = new VerifyDao();

    //短信宝
    public static final String SMS = "http://api.smsbao.com/sms";
    private VerifyDao() {
    }

    public static VerifyDao getInstance() {
        return instance;
    }


    public void sendVer(Context context, String phone,int ver ,ResultCallBack callBack) {
       // final int ver = (int) ((Math.random() * 9 + 1) * 100000);
        Map<String, Object> params = new HashMap<>();
        params.put("m", phone);
        params.put("u", "vhuobang");
        params.put("p", Md5Util.getStringMD5("vhuobang123"));
        params.put("c", String.format("[危货帮]您的验证码是%s，5分钟内有效。若非本人操作请忽略此消息！", ver));
       LoadDataUtil.getInstance().getData(context,SMS, params, "正在发送验证码", callBack);
    }
}
