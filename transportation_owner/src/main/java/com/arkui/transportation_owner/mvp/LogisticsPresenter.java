package com.arkui.transportation_owner.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
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


    private LogisticsView mLogisticsView;
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

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogisticalListEntity>>(mContext,false) {
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

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                if(mLogisticsView!=null){
                    mLogisticsView.onError();
                }
            }
        });
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
                mLogisticsView.onSucceed(value);
            }
        });
    }

    //收藏与未收藏
    public void postCollectionLogistical(String log_id,final int position){
        Observable<BaseHttpResult> observable = mLogisticalApi.postCollectionLogistical(App.getUserId(),log_id);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                mLogisticsView.onSucceed(position);
            }
        });
    }

    //获取已收藏列表
    public void postCollectionLogisticsList(int page){

        Observable<List<LogisticalListEntity>> observable = mLogisticalApi.postCollectionLogisticalList( App.getUserId(),page,20).map(new HttpResultFunc<List<LogisticalListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogisticalListEntity>>(mContext,false) {
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

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                if(mLogisticsView!=null){
                    mLogisticsView.onError();
                }
            }
        });
    }
}
