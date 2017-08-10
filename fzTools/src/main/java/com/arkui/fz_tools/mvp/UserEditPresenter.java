package com.arkui.fz_tools.mvp;

import android.text.TextUtils;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.UserEditInterface;
import com.arkui.fz_tools.api.UserApi;
import com.arkui.fz_tools.entity.UserEntity;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by 84658 on 2017/8/9.
 */

public class UserEditPresenter extends BasePresenter {
    public  UserEditInterface  mUserEditInterface;
    private UserApi mUserApi;


    public void setUserEditInterface(UserEditInterface userEditInterface) {
        mUserEditInterface = userEditInterface;
        mUserApi = RetrofitFactory.createRetrofit(UserApi.class);
    }

    // 完善用户信息
    public void getUserEdit(String user_id,String nickname,String avatar,String qq){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("user_id",user_id);
        if (!TextUtils.isEmpty(nickname)){
            hashMap.put("nickname",nickname);
        }
        if (!TextUtils.isEmpty(avatar)){
            hashMap.put("avatar",avatar);
        }
        if (!TextUtils.isEmpty(qq)){
            hashMap.put("qq",qq);
        }
        Observable<UserEntity> observable = mUserApi.getUserEdit(hashMap).map(new HttpResultFunc<UserEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<UserEntity>(mContext) {
            @Override
            public void onNext(UserEntity userEntity) {
                   mUserEditInterface.onSuccess(userEntity);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mUserEditInterface.onFail(e.getMessage());
            }
        });
    }

}
