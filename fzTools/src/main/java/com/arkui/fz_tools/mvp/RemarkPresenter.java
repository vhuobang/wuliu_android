package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.RemarkInterface;
import com.arkui.fz_tools.api.PublishApi;
import com.arkui.fz_tools.entity.RemarkEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/9/25.
 */

public class RemarkPresenter  extends BasePresenter{

    PublishApi mPublishApi;
    RemarkInterface mRemarkInterface;

    public RemarkPresenter(RemarkInterface mRemarkInterface , Activity activity) {
        this.mRemarkInterface = mRemarkInterface;
        mContext = activity;
        mPublishApi = RetrofitFactory.createRetrofit(PublishApi.class);
    }

    public void getRemarks(){
        Observable<List<RemarkEntity>> observable = mPublishApi.getRemark().map(new HttpResultFunc<List<RemarkEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<RemarkEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
               mDisposables.add(d);
            }

            @Override
            public void onNext(List<RemarkEntity> value) {
                mRemarkInterface.remarkList(value);
            }

            @Override
            public void onApiError(ApiException e) {
                mRemarkInterface.noRemark(e.getMessage());
            }
        });
    }
}
