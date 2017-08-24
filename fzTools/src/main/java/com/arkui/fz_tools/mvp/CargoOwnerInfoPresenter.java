package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.CargoOwnerInfoInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.CargoOwnerInfoEntity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/24.
 */

public class CargoOwnerInfoPresenter extends BasePresenter {

    CargoOwnerInfoInterface mCargoOwnerInfoInterface;
    PublicApi mPublicApi ;

    public CargoOwnerInfoPresenter(CargoOwnerInfoInterface mCargoOwnerInfoInterface, Activity activity) {
        mContext = activity;
        this.mCargoOwnerInfoInterface = mCargoOwnerInfoInterface;
        mPublicApi= RetrofitFactory.createRetrofit(PublicApi.class);
    }
    // 貨主信息
    public  void getCargoOwnerInfo(String cargoId ,String ownerId){
        Observable<CargoOwnerInfoEntity> observable = mPublicApi.getCargoOwnerInfo(cargoId, ownerId).map(new HttpResultFunc<CargoOwnerInfoEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<CargoOwnerInfoEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(CargoOwnerInfoEntity value) {
                     mCargoOwnerInfoInterface.onSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
                mCargoOwnerInfoInterface.onFail(e.getMessage());
            }
        });
    }
}
