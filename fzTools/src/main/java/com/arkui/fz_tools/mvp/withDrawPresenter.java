package com.arkui.fz_tools.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.PayApi;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/10/26.
 */

public class WithDrawPresenter extends BasePresenter {
    public PayApi mPayApi;
    public PublicInterface mPublicInterface;

    public WithDrawPresenter(PublicInterface mPublicInterface, Activity activity) {
        mContext = activity;
        mPayApi = RetrofitFactory.createRetrofit(PayApi.class);
        this.mPublicInterface = mPublicInterface;
    }


    public void getWithDraw(@NonNull  String userId, @NonNull String recharge_type, @NonNull  String withdrawals, String number,
                            String account_name, String bank) {
        HashMap<String,Object> map = new HashMap<>();
         map.put("user_id",userId);
        // map.put("recharge_type",recharge_type);
         map.put("withdrawals",withdrawals);
         map.put("number",number);
       //  map.put("account_name",account_name);
       //  map.put("bank",bank);
        Observable<BaseHttpResult> withDraw = mPayApi.getWithDraw(map);
        HttpMethod.getInstance().getNetData(withDraw, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                  mPublicInterface.onSuccess();
            }

            @Override
            public void onApiError(ApiException e) {
                mPublicInterface.onFail(e.getMessage());
            }
        });
    }

    public void getIntegral(@NonNull  String userId, @NonNull String recharge_type, @NonNull String integral ){
        HashMap<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("recharge_type",recharge_type);
        map.put("integral",integral);
        Observable<BaseHttpResult> withDraw = mPayApi.getFinanceWithdraw(map);
        HttpMethod.getInstance().getNetData(withDraw, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                mPublicInterface.onSuccess();
            }

            @Override
            public void onApiError(ApiException e) {
                mPublicInterface.onFail(e.getMessage());
            }
        });
    }

}
