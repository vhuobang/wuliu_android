package com.arkui.transportation.presenter;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.mvp.BasePresenter;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.entity.LogWayBIllListEntity;
import com.arkui.transportation.view.LogWaybillListView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/23.
 */

public class LogWaybillListPresenter extends BasePresenter {

    LogWaybillListView mLogWaybillListView ;
    LogisticalApi mLogisticalApi;

    public LogWaybillListPresenter(LogWaybillListView mLogWaybillListView, Activity activity) {
        this.mLogWaybillListView = mLogWaybillListView;
        mContext= activity;
        mLogisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
    }
    // 物流运单列表
    public  void  getWaybillList(String userId,String orderStatus,String type){

        Observable<List<LogWayBIllListEntity>> observable = mLogisticalApi.getLogWaybillList(userId, orderStatus, type).map(new HttpResultFunc<List<LogWayBIllListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogWayBIllListEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<LogWayBIllListEntity> value) {
                    mLogWaybillListView.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                mLogWaybillListView.onLoadDataFail(e.getMessage());
            }
        });
    }
   // 待收款列表
    public void getLogAgency(String user_id){
        Observable<List<LogWayBIllListEntity>> observable = mLogisticalApi.getLogAgency(user_id).map(new HttpResultFunc<List<LogWayBIllListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogWayBIllListEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<LogWayBIllListEntity> value) {
                mLogWaybillListView.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                mLogWaybillListView.onLoadDataFail(e.getMessage());
            }
        });
    }


}
