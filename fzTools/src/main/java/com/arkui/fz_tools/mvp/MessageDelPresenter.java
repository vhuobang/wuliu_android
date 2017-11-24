package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.MessageDelInterface;
import com.arkui.fz_tools.api.PublicApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/9/26.
 */

public class MessageDelPresenter  extends BasePresenter{
    MessageDelInterface messageDelInterface;
    PublicApi publicApi;

    public MessageDelPresenter(MessageDelInterface messageDelInterface, Activity activity) {
         mContext = activity;
        this.messageDelInterface = messageDelInterface;
        publicApi=  RetrofitFactory.createRetrofit(PublicApi.class);
    }

    // 删除消息
    public void getNoticeDel(String id){
        Observable<BaseHttpResult> observable = publicApi.getNoticeDel(id);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                messageDelInterface.delMessageSuccess();
            }

            @Override
            public void onApiError(ApiException e) {
                // super.onApiError(e);
                messageDelInterface.delMessageFail(e.getMessage());
            }
        });

    }

}
