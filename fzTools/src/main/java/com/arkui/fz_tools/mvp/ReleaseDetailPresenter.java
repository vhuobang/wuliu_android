package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/17.
 * 预发布详情
 */

public class ReleaseDetailPresenter extends BasePresenter {
    ReleaseDetailInterface mReleaseDetailInterface;
    PublicApi mPublicApi;

    public ReleaseDetailPresenter(ReleaseDetailInterface mReleaseDetailInterface , Activity activity) {
        this.mReleaseDetailInterface = mReleaseDetailInterface;
        mContext= activity;
        mPublicApi= RetrofitFactory.createRetrofit(PublicApi.class);
    }

    //获取预发布详情的接口
    public void getReleaseDetail(String cargoId){
        Observable<ReleaseDetailsEntity> observable = mPublicApi.getReleaseDetails(cargoId).map(new HttpResultFunc<ReleaseDetailsEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<ReleaseDetailsEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
               mDisposables.add(d);
            }

            @Override
            public void onNext(ReleaseDetailsEntity value) {
                    mReleaseDetailInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mReleaseDetailInterface.onFail(e.getMessage());
            }
        });
    }

}
