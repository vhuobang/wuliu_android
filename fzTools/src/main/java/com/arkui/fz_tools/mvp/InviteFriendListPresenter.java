package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.InviteFriendListInterface;
import com.arkui.fz_tools.api.UserApi;
import com.arkui.fz_tools.entity.InviteFriendEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/10.
 */

public class InviteFriendListPresenter extends BasePresenter {

    private  InviteFriendListInterface  mInviteFriendListInterface ;
    private  Activity mContext;
    private UserApi mUserApi;
    public InviteFriendListPresenter(InviteFriendListInterface inviteFriendListInterface, Activity activity) {
        mInviteFriendListInterface = inviteFriendListInterface;
        mContext = activity;
        mUserApi= RetrofitFactory.createRetrofit(UserApi.class);
    }
      public  void setInviteFriendListInterface (InviteFriendListInterface inviteFriendListInterface){
          mInviteFriendListInterface = inviteFriendListInterface;
      }

    public void  getInviteFriendList(String user_id,int page,int pageSize){
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("user_id",user_id);
        hashMap.put("page",page);
        hashMap.put("pagesize",pageSize);
        Observable<List<InviteFriendEntity>> observable = mUserApi.getFriendList(hashMap).map(new HttpResultFunc<List<InviteFriendEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<InviteFriendEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<InviteFriendEntity> inviteFriendEntityList) {
                mInviteFriendListInterface.onSuccess(inviteFriendEntityList);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mInviteFriendListInterface.onFail(e.getMessage());
            }
        });
    }
}
