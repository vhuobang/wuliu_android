package com.arkui.fz_tools.mvp;

import android.app.Activity;
import android.content.Context;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.api.PublishApi;
import com.arkui.fz_tools.entity.PublishEntity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/17.
 */

public class PublishPresenter extends BasePresenter {


    PublicInterface mPublicInterface;
    private final PublishApi mPublishApi;

    public PublishPresenter(PublicInterface mPublicInterface, Activity context) {
        this.mPublicInterface = mPublicInterface;
        this.mContext=context;
        mPublishApi = RetrofitFactory.createRetrofit(PublishApi.class);
    }

    public void postSave(PublishEntity publishEntity){
        Observable<BaseHttpResult> observable = mPublishApi.postSaveCargo(publishEntity);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                if(mPublicInterface!=null){
                    mPublicInterface.onSuccess();
                }
            }
        });

    }

}
