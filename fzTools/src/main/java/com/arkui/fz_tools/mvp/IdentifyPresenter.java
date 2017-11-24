package com.arkui.fz_tools.mvp;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.CodeInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.CodeEntity;
import com.arkui.fz_tools.entity.VerPicEntity;
import com.arkui.fz_tools.utils.MD5;
import com.arkui.fz_tools.utils.StrUtil;

import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/11/21.
 */

public class IdentifyPresenter extends BasePresenter {
     CodeInterface mCodeInterface;
     PublicApi publicApi;
    public static final String KEY= "wuliu";

    public IdentifyPresenter(CodeInterface mCodeInterface, Activity activity) {
        this.mCodeInterface = mCodeInterface;
        mContext= activity;
        publicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    //获取图形验证码
    public  void getImageCode(int imageW	,int imageH,int textSize ,String deviceKey){
        Map<String ,Object> map = new HashMap<>();
        map.put("imageW",imageW);
        map.put("imageH",imageH);
        map.put("fontSize",textSize);
        map.put("deviceKey",deviceKey);
        String time = System.currentTimeMillis() / 1000 + "";
        map.put("sign", sign(map, time));
        map.put("time", time);
        Observable<VerPicEntity> observable = publicApi.getVarImageCode(map).map(new HttpResultFunc<VerPicEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<VerPicEntity>(mContext,false) {
            @Override
            protected void getDisposable(Disposable d) {
                 mDisposables.add(d);
            }

            @Override
            public void onNext(VerPicEntity value) {
                mCodeInterface.loadImageSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mCodeInterface.onFail(e.getMessage(),1); // 获取验证图片失败
            }

        });
    }
    //获取验证码
    public  void getVarCode(String mobile,String code,String  deviceKey){
        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mContext, "手机号输入不合法", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(mContext, "请输入图形验证码", Toast.LENGTH_LONG).show();
            return;
        }

        HashMap<String ,Object> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("code",code);
        map.put("deviceKey",deviceKey);
        Observable<CodeEntity> observable = publicApi.getSendSms(map).map(new HttpResultFunc<CodeEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<CodeEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(CodeEntity value) {
                  mCodeInterface.loadCodeSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mCodeInterface.onFail(e.getMessage(),2);//表示获取验证码失败
            }
        });
    }


    private String sign(Map<String, Object> params, String time) {
        Map<String, Object> map = new TreeMap<>(new MapKeyComparator());
        if (params != null && !params.isEmpty()) {
            map.putAll(params);
        }
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        StringBuffer buffer = new StringBuffer();
        String key, value;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = String.valueOf(map.get(key));
            buffer.append(key + "=" + value + "&");

        }
        String result = buffer.toString() + "time=" + time + "&salt=" + KEY;
        // System.out.println("ceshi " + result);
        result = URLEncoder.encode(result);
        System.out.println("ceshi " + result);
        return MD5.MD5Encode(result).toUpperCase();
    }

    private class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }
}
