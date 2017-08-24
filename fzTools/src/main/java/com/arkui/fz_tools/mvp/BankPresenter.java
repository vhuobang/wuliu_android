package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.FinanceApi;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/22.
 */

public class BankPresenter extends BasePresenter {
    PublicInterface mPublicInterface;
    FinanceApi mFinanceApi;

    public BankPresenter(PublicInterface mPublicInterface, Activity activity) {
        this.mPublicInterface = mPublicInterface;
        mContext = activity;
        mFinanceApi = RetrofitFactory.createRetrofit(FinanceApi.class);
    }
    // 添加银行卡
    public void addBank(HashMap<String ,Object> map) {
        Observable<BaseHttpResult> observable = mFinanceApi.addBank(map);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
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
    // 删除银行卡
    public void delBank(String binkId){
        Observable<BaseHttpResult> observable = mFinanceApi.delBank(binkId);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
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

    // 支付
    public void pay(HashMap<String,Object> map){
        Observable<BaseHttpResult> observable = mFinanceApi.pay(map);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
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
//                super.onApiError(e);
                mPublicInterface.onFail(e.getMessage());
            }
        });
    }

}
