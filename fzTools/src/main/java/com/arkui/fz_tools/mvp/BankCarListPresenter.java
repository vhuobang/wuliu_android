package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.BinkListInterface;
import com.arkui.fz_tools.api.FinanceApi;
import com.arkui.fz_tools.entity.BankCarEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/22.
 */

public class BankCarListPresenter extends BasePresenter {

    FinanceApi mFinanceApi;
    BinkListInterface mBinkListInterface;

    public BankCarListPresenter(BinkListInterface mBinkListInterface, Activity activity) {
        this.mBinkListInterface = mBinkListInterface;
        mContext= activity;
        mFinanceApi= RetrofitFactory.createRetrofit(FinanceApi.class);
    }

    public void getBankCarList(String userId){
        Observable<List<BankCarEntity>> observable = mFinanceApi.getBankList(userId).map(new HttpResultFunc<List<BankCarEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<BankCarEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<BankCarEntity> value) {
                mBinkListInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {

                mBinkListInterface.onFail(e.getMessage());
            }
        });
    }
}
