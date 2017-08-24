package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.InformationFeeInterface;
import com.arkui.fz_tools.api.FinanceApi;
import com.arkui.fz_tools.entity.InformationDetailEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/22.
 */

public class InformationFeePresenter extends BasePresenter {
    FinanceApi mFinanceApi;
    InformationFeeInterface mInformationFeeInterface;

    public InformationFeePresenter(InformationFeeInterface mInformationFeeInterface, Activity activity) {
        this.mInformationFeeInterface = mInformationFeeInterface;
        mContext= activity;
        mFinanceApi = RetrofitFactory.createRetrofit(FinanceApi.class);
    }

    public void getInformationFeeList(String userId,int page,int pageSize){
        Observable<List<InformationDetailEntity>> observable = mFinanceApi.getInformationDetails(userId, page, pageSize).map(new HttpResultFunc<List<InformationDetailEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<InformationDetailEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<InformationDetailEntity> value) {
                mInformationFeeInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                // super.onApiError(e);
                mInformationFeeInterface.onFail(e.getMessage());
            }
        });
    }
}
