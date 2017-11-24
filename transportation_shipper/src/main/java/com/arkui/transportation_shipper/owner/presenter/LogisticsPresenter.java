package com.arkui.transportation_shipper.owner.presenter;

import android.app.Activity;

import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.mvp.BasePresenter;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.owner.api.LogisticalApi;
import com.arkui.transportation_shipper.owner.entity.LogisticalListEntity;
import com.arkui.transportation_shipper.owner.view.LogisticsView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/11.
 */

public class LogisticsPresenter extends BasePresenter {


    private LogisticsView mLogisticsView;
    private final LogisticalApi mLogisticalApi;

    public LogisticsPresenter(LogisticsView mLogisticsView, Activity context) {
        this.mLogisticsView = mLogisticsView;
        mContext=context;
        mLogisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
    }



    //物流详情
    public void postLogisticsDetail(String log_id,String wayBillId){
        Observable<LogisticalListEntity> observable = mLogisticalApi.postLogisticalDetail(App.getUserId(),log_id,wayBillId).map(new HttpResultFunc<LogisticalListEntity>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<LogisticalListEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(LogisticalListEntity value) {
                mLogisticsView.onSuccess(value);
            }
        });
    }

}
