package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.WayBillDetialsInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.WayBillDetailEntity;

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
     public void getWayBillDetail(String wayBillId,String userId){

         Observable<WayBillDetailEntity> observable = mPublicApi.getWayBillDetails(wayBillId,userId).map(new HttpResultFunc<WayBillDetailEntity>());
         HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<WayBillDetailEntity>(mContext) {
             @Override
             protected void getDisposable(Disposable d) {
                 mDisposables.add(d);
             }

             @Override
             public void onNext(WayBillDetailEntity value) {
                 mWayBillDetialsInterface.onSuccess(value);
             }

             @Override
             public void onApiError(ApiException e) {
                 super.onApiError(e);
                 mWayBillDetialsInterface.onFail(e.getMessage());
             }
         });
     }


}
