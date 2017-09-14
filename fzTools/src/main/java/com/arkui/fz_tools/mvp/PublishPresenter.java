package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublishInterface;
import com.arkui.fz_tools.api.PublishApi;
import com.arkui.fz_tools.entity.PublishBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/17.
 */

public class PublishPresenter extends BasePresenter {


    PublishInterface mPublishInterface;
    private final PublishApi mPublishApi;

    public PublishPresenter(PublishInterface mPublishInterface, Activity context) {
        this.mPublishInterface = mPublishInterface;
        this.mContext=context;
        mPublishApi = RetrofitFactory.createRetrofit(PublishApi.class);
    }

    public void postSave(Map<String,Object> map){
        Observable<PublishBean> observable = mPublishApi.postSaveCargo(map).map(new HttpResultFunc<PublishBean>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<PublishBean>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(PublishBean value) {
                if(mPublishInterface!=null){
                    mPublishInterface.onSuccess(value);
                }
            }
        });

    }

    public void postEdit(Map<String,Object> map){
        Observable<BaseHttpResult> observable = mPublishApi.postEditCargo(map);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                if(mPublishInterface!=null){
                    mPublishInterface.onSuccess(null);
                }
            }
        });

    }

}
