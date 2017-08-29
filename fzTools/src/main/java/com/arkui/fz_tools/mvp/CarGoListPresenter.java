package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.CarGoListInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.CarGoListEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/17.
 */

public class CarGoListPresenter extends BasePresenter {
    CarGoListInterface mCarGoListInterface;
    PublicApi mPublicApi ;

    public CarGoListPresenter(CarGoListInterface mCarGoListInterface , Activity activity) {
        this.mCarGoListInterface = mCarGoListInterface;
        mContext = activity;
        mPublicApi= RetrofitFactory.createRetrofit(PublicApi.class);
    }
    // 请求 预发布  已发布列表
    public void getCarGoList(String userId,String opStatus,int page,int pageSize){
        Observable<List<CarGoListEntity>> observable = mPublicApi.getCargoList(userId, opStatus, page, pageSize).map(new HttpResultFunc<List<CarGoListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<CarGoListEntity>>(mContext,false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<CarGoListEntity> value) {
                mCarGoListInterface.onCarGoListSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
               // super.onApiError(e);
                mCarGoListInterface.onCarGoListFail(e.getMessage());
            }
        });
    }



}
