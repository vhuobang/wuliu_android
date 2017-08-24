package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.IntegralDetailsInterface;
import com.arkui.fz_tools.api.FinanceApi;
import com.arkui.fz_tools.entity.IntegralDetailsEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/22.
 */

public class IntegralDetailsPresenter extends BasePresenter {

    FinanceApi mFinanceApi;
    IntegralDetailsInterface mIntegralDetailsInterface;

    public IntegralDetailsPresenter(IntegralDetailsInterface mIntegralDetailsInterface, Activity activity) {
        this.mIntegralDetailsInterface = mIntegralDetailsInterface;
        mContext= activity;
        mFinanceApi = RetrofitFactory.createRetrofit(FinanceApi.class);
    }

    public void getIntegralDetails(String userId,int page,int pageSize){
        Observable<List<IntegralDetailsEntity>> observable = mFinanceApi.getIntegralDetails(userId, page, pageSize).map(new HttpResultFunc<List<IntegralDetailsEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<IntegralDetailsEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<IntegralDetailsEntity> value) {
                mIntegralDetailsInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                // super.onApiError(e);
                mIntegralDetailsInterface.onFail(e.getMessage());
            }
        });
    }
}
