package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PrePayInterface;
import com.arkui.fz_tools.api.FinanceApi;
import com.arkui.fz_tools.entity.PrePayEntity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/9/7.
 */

public class PrePayPresenter extends BasePresenter {
    PrePayInterface mPrePayInterface;
    FinanceApi mFinanceApi;

    public PrePayPresenter(PrePayInterface mPrePayInterface , Activity activity) {
        this.mPrePayInterface = mPrePayInterface;
        mContext = activity;
        mFinanceApi = RetrofitFactory.createRetrofit(FinanceApi.class);
    }

    public void  getPrePay(String orderId){
        Observable<PrePayEntity> observable = mFinanceApi.prePay(orderId).map(new HttpResultFunc<PrePayEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<PrePayEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(PrePayEntity value) {
                  mPrePayInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                mPrePayInterface.onFail(e.getMessage());
            }
        });
    }
}
