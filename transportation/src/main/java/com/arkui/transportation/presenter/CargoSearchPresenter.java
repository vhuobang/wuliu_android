package com.arkui.transportation.presenter;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.mvp.BasePresenter;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.entity.CargoSearchListEntity;
import com.arkui.transportation.view.CargoSearchView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/25.
 */

public class CargoSearchPresenter extends BasePresenter {
     LogisticalApi mLogisticalApi;
     CargoSearchView mCargoSearchView;

    public CargoSearchPresenter(CargoSearchView mCargoSearchView, Activity activity) {
        this.mCargoSearchView = mCargoSearchView;
        mContext = activity;
        mLogisticalApi= RetrofitFactory.createRetrofit(LogisticalApi.class);
    }
    public void getCargoSearchList(String userId,String keywords){
        Observable<List<CargoSearchListEntity>> observable = mLogisticalApi.getCargoSearch(userId, keywords).map(new HttpResultFunc<List<CargoSearchListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<CargoSearchListEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                 mDisposables.add(d);
            }

            @Override
            public void onNext(List<CargoSearchListEntity> value) {
                mCargoSearchView.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
             //   super.onApiError(e);
                mCargoSearchView.onFail(e.getMessage());
            }
        });
    }
}
