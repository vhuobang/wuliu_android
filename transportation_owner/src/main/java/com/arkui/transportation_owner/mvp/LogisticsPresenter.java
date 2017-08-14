package com.arkui.transportation_owner.mvp;

import android.app.Activity;
import android.content.Context;

import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.mvp.BasePresenter;
import com.arkui.transportation_owner.api.LogisticalApi;
import com.arkui.transportation_owner.base.App;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.view.LogisticsView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/11.
 */

public class LogisticsPresenter extends BasePresenter {


    LogisticsView mLogisticsView;
    private final LogisticalApi mLogisticalApi;

    public LogisticsPresenter(LogisticsView mLogisticsView, Activity context) {
        this.mLogisticsView = mLogisticsView;
        mContext=context;
        mLogisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
    }

    //获取物流列表
    public void postLogisticsList(String address,String name,int page){
        Map<String,Object> parameter=new HashMap<>();

        parameter.put("user_id", App.getUserId());
        parameter.put("address",address);
        parameter.put("name",name);
        parameter.put("page",page);
        parameter.put("pagesize",20);

        Observable<List<LogisticalListEntity>> observable = mLogisticalApi.postLogisticalList(parameter).map(new HttpResultFunc<List<LogisticalListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogisticalListEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<LogisticalListEntity> value) {
                if(mLogisticsView!=null){
                    mLogisticsView.onSucceed(value);
                }
            }
        });
    }

}
