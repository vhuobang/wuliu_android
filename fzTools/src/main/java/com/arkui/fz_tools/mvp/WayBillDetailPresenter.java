package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.WayBillDetialsInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.SystemDetialEntity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/15.
 */

public class WayBillDetailPresenter extends BasePresenter {

    public WayBillDetialsInterface mWayBillDetialsInterface;
    public PublicApi  mPublicApi;

    public WayBillDetailPresenter() {
    }

    public void setmWayBillDetialsInterface( WayBillDetialsInterface wayBillDetialsInterface){
         mWayBillDetialsInterface = wayBillDetialsInterface;
    }
    public WayBillDetailPresenter(WayBillDetialsInterface mWayBillDetialsInterface, Activity activity) {
        this.mWayBillDetialsInterface = mWayBillDetialsInterface;
        mContext =activity;
        this.mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    // 请求运单详情
     public void getWayBillDetail(String wayBillId){

         Observable<BaseHttpResult<SystemDetialEntity>> observable = mPublicApi.getWayBillDetails(wayBillId);
         HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult<SystemDetialEntity>>(mContext) {
             @Override
             protected void getDisposable(Disposable d) {
                 mDisposables.add(d);
             }

             @Override
             public void onNext(BaseHttpResult<SystemDetialEntity> value) {

             }

             @Override
             public void onApiError(ApiException e) {
                 super.onApiError(e);
             }
         });
     }


}
