package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.SystemMessageInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.SystemDetialEntity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/14.
 */

public class SystemMessagePresenter extends BasePresenter {
    public  SystemMessageInterface mSystemMessageInterface;
    public PublicApi mPublicApi;

    public SystemMessagePresenter(){

    }

    public SystemMessagePresenter(SystemMessageInterface mSystemMessageInterface, Activity activity) {
        this.mSystemMessageInterface = mSystemMessageInterface;
        mContext =activity;
        this.mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    public void setSystemMessageInterface(SystemMessageInterface systemMessageInterface) {
        mSystemMessageInterface = systemMessageInterface;
        mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    // 获取系统的消息内容
    public void  getSystemMessageDetail(String messageID){
        Observable<SystemDetialEntity> observable = mPublicApi.getNoticeDetails(messageID)
                .map(new HttpResultFunc<SystemDetialEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<SystemDetialEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(SystemDetialEntity value) {
                     mSystemMessageInterface
                             .onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {

                mSystemMessageInterface.onFail(e.getMessage());
            }
        });
    }

}
