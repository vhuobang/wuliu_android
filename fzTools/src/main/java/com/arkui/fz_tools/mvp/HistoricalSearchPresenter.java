package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.HistoricalSearchInterface;
import com.arkui.fz_tools.api.SearchApi;
import com.arkui.fz_tools.entity.HistocialSearchEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/9/12.
 */

public class HistoricalSearchPresenter extends BasePresenter {
    HistoricalSearchInterface mHistoricalSearchInterface;
    SearchApi mSearchApi ;

    public HistoricalSearchPresenter(HistoricalSearchInterface mHistoricalSearchInterface, Activity activity) {
        this.mHistoricalSearchInterface = mHistoricalSearchInterface;
        mContext=activity;
        mSearchApi= RetrofitFactory.createRetrofit(SearchApi.class);
    }

    // 历史搜索列表
    public void  getSearchList(String userId,String type){
        Observable<List<HistocialSearchEntity>> observable = mSearchApi.postSearchList(userId, type).map(new HttpResultFunc<List<HistocialSearchEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<HistocialSearchEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<HistocialSearchEntity> value) {
                mHistoricalSearchInterface.onSearchListSuccess(value);
            }

            @Override
            public void onApiError(ApiException e) {
             mHistoricalSearchInterface.onSearchFail(e.getMessage());
            }
        });
    }

    // 清空搜索记录
    public void  delSearchList(String userId,String type){
        Observable<BaseHttpResult> observable = mSearchApi.postDelSearch(userId, type);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                  mHistoricalSearchInterface.onSuccess( value.getMessage());
            }

            @Override
            public void onApiError(ApiException e) {
                mHistoricalSearchInterface.onSearchFail(e.getMessage());
            }
        });
    }

}
