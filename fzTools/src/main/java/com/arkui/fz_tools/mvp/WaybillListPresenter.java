package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.WaybillListInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.LogWayBIllListEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/28.
 */

public class WaybillListPresenter extends BasePresenter {
    WaybillListInterface mWaybillListInterface ;
    PublicApi mPublicApi;

    public WaybillListPresenter(WaybillListInterface mWaybillListInterface, Activity activity) {
        this.mWaybillListInterface = mWaybillListInterface;
        mContext= activity;
        mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    // 请求运单列表
    public  void  getWaybillList(String userId,String orderStatus){

        Observable<List<LogWayBIllListEntity>> observable = mPublicApi.getLogWaybillList(userId, orderStatus).map(new HttpResultFunc<List<LogWayBIllListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogWayBIllListEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<LogWayBIllListEntity> value) {
                mWaybillListInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                mWaybillListInterface.onError(e.getMessage());
            }
        });
    }

}
