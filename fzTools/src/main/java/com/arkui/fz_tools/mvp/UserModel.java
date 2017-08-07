package com.arkui.fz_tools.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.model.UserApi;
import com.arkui.fz_tools.utils.UserType;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by nmliz on 2017/8/7.
 * 用户相关的 所有Model
 */

public class UserModel implements BaseModel {

    //登录接口
    public void getLogin() {

    }

    //注册接口 没有邀请码 写Null
    public void getRegister(@NonNull String mobile, @NonNull String password, @UserType int type, @Nullable String invitation_code, ProgressSubscriber<BaseHttpResult> progressSubscriber) {
        Map<String,Object> parameter=new HashMap<>();
        parameter.put("mobile",mobile);
        parameter.put("password",password);
        parameter.put("type",type);
        if(!TextUtils.isEmpty(invitation_code)){
            parameter.put("invitation_code",invitation_code);
        }
        Observable<BaseHttpResult> observable = RetrofitFactory.createRetrofit(UserApi.class).getRegister(parameter);
        HttpMethod.getInstance().getNetData(observable,progressSubscriber);
    }

    @Override
    public void onDestroy() {

    }
}
