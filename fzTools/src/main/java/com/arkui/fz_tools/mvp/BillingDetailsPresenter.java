package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.BillingDetailsInterface;
import com.arkui.fz_tools.api.FinanceApi;
import com.arkui.fz_tools.entity.BillingDetailsEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/22.
 */

public class BillingDetailsPresenter extends BasePresenter {

    FinanceApi mFinanceApi;
    BillingDetailsInterface mBillingDetailsInterface;

    public BillingDetailsPresenter(BillingDetailsInterface mBillingDetailsInterface, Activity activity) {
        this.mBillingDetailsInterface = mBillingDetailsInterface;
        mContext= activity;
        mFinanceApi = RetrofitFactory.createRetrofit(FinanceApi.class);
    }

    public void getBillingDetails(String userId,int page,int pageSize){
        Observable<List<BillingDetailsEntity>> observable = mFinanceApi.getBillingDetails(userId, page, pageSize).map(new HttpResultFunc<List<BillingDetailsEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<BillingDetailsEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<BillingDetailsEntity> value) {
                mBillingDetailsInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
               // super.onApiError(e);
                mBillingDetailsInterface.onFail(e.getMessage());
            }
        });
    }
}
