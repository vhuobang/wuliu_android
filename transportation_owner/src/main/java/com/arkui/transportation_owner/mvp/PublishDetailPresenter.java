package com.arkui.transportation_owner.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.mvp.BasePresenter;
import com.arkui.transportation_owner.api.LogisticalApi;
import com.arkui.transportation_owner.base.App;
import com.arkui.transportation_owner.entity.PublishDetailEntity;
import com.arkui.transportation_owner.view.PublishDetailView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/22.
 * 已发布详情
 */

public class PublishDetailPresenter extends BasePresenter {

    public PublishDetailView mPublishDetailView;
    LogisticalApi mLogisticalApi;

    public PublishDetailPresenter(Activity context, PublishDetailView mPublishDetailView) {
        this.mPublishDetailView = mPublishDetailView;
        this.mContext=context;
        mLogisticalApi= RetrofitFactory.createRetrofit(LogisticalApi.class);
    }

    //获取已发布列表
    public void postPublishDetail(String cargo_id){

        Observable<PublishDetailEntity> observable = mLogisticalApi.postPublishDetail(App.getUserId(), cargo_id).map(new HttpResultFunc<PublishDetailEntity>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<PublishDetailEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(PublishDetailEntity value) {
                mPublishDetailView.onSucceed(value);
            }

            @Override
            public void onApiError(ApiException e) {
              //  super.onApiError(e);
                mPublishDetailView.onError();
            }
        });
    }
}
