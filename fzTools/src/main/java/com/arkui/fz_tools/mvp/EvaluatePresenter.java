package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.PublicApi;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/9/5.
 */

public class EvaluatePresenter extends BasePresenter {
     PublicInterface mPublicInterface;
     PublicApi mPublicApi;

    public EvaluatePresenter(PublicInterface mPublicInterface, Activity activity) {
        this.mPublicInterface = mPublicInterface;
        mContext =activity;
        mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    public void evaluate(String cargo_star_num, String log_star_num , String car_star_num, String user_id,String type
    ,String order_id){
        HashMap<String,Object> hashMap = new HashMap<>();
        if (cargo_star_num!=null){
            hashMap.put("cargo_star_num",cargo_star_num);//货主评星
        }
        if (log_star_num!=null){
            hashMap.put("log_star_num",log_star_num);// 物流评星
        }
        if (car_star_num !=null){
            hashMap.put("car_star_num",car_star_num); // 车主评星
        }

        hashMap.put("user_id",user_id);
        hashMap.put("type",type);
        hashMap.put("order_id",order_id);
        Observable<BaseHttpResult> observable = mPublicApi.getEvaluate(hashMap);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
               mPublicInterface.onSuccess();
            }

            @Override
            public void onApiError(ApiException e) {
                mPublicInterface.onFail(e.getMessage());
            }
        });
    }

}
