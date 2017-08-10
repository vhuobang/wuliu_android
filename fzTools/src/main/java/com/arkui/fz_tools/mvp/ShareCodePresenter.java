package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.ShareCodeInterface;
import com.arkui.fz_tools.api.UserApi;
import com.arkui.fz_tools.entity.ShareCodeEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/9.
 */

public class ShareCodePresenter extends BasePresenter {

    public  ShareCodeInterface mShareCodeInterface;
    public  UserApi mUserApi;
    public  Activity mContext;
    public  ShareCodePresenter(Activity context , ShareCodeInterface shareCodeInterface){
        mShareCodeInterface=shareCodeInterface;
        mContext =context;
        mUserApi = RetrofitFactory.createRetrofit(UserApi.class);
    }

    //生成邀请码
    public void getShareCode(String userId){
        HashMap<String ,Object>  hashMap = new HashMap<>();
        hashMap.put("user_id",userId);
        Observable<ShareCodeEntity> observable = mUserApi.getShareCode(hashMap).map(new HttpResultFunc<ShareCodeEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<ShareCodeEntity>(mContext) {
            @Override
            public void onNext(ShareCodeEntity shareCodeEntity) {
                mShareCodeInterface.onSuccess(shareCodeEntity);
            }

            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onApiError(ApiException e) {
                mShareCodeInterface.onFail(e.getMessage());

            }
        });

    }

}
