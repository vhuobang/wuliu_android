package com.arkui.fz_tools.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.api.UserApi;
import com.arkui.fz_tools.api.VerifyDao;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.net.JsonData;
import com.arkui.fz_tools.net.ResultCallBack;
import com.arkui.fz_tools.utils.Md5Util;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.utils.UserType;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/7.
 */

public class UserPresenter extends BasePresenter {

    private UserInterface mUserInterface;
    private int mVerificationCode = -1;
    private String mMobile = null;
    private UserApi mUserApi;

    public UserPresenter() {

    }


    public void setUserInterface(UserInterface userInterface) {
        mUserInterface = userInterface;
        mUserApi = RetrofitFactory.createRetrofit(UserApi.class);
    }
    // 注册
    public void getRegister(@NonNull String mobile, @NonNull String code, @NonNull String password, @NonNull String confirmPassword, @UserType int type, @Nullable String invitation_code) {

        if (mMobile == null) {
            Toast.makeText(mContext, "请获取验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mMobile.equals(mobile)) {
            Toast.makeText(mContext, "请重新获取验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        int verificationCode = Integer.parseInt(code);

        if (verificationCode != mVerificationCode) {
            Toast.makeText(mContext, "验证码输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(mContext, "密码长度不够", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(mContext, "两次密码输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("mobile", mobile);
        parameter.put("password", Md5Util.getStringMD5(password));
        parameter.put("type", type);
        if (!TextUtils.isEmpty(invitation_code)) {
            parameter.put("invitation_code", invitation_code);
        }
        Observable<BaseHttpResult> observable = mUserApi.getRegister(parameter);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            public void onNext(BaseHttpResult value) {
                Toast.makeText(mContext, value.getMessage(), Toast.LENGTH_SHORT).show();
                if (mUserInterface != null) {
                    mUserInterface.onSucceed();
                }
            }

            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                //注册失败
            }
        });
    }

    //获取验证码
    public void getCode(@NonNull String mobile, TimeCountUtil timeCountUtil) {
        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        timeCountUtil.start();
        mMobile = mobile;
        mVerificationCode = (int) ((Math.random() * 9 + 1) * 100000);
        VerifyDao.getInstance().sendVer(mContext, mMobile, mVerificationCode, new ResultCallBack() {
            @Override
            public void onSuccess(JsonData data) {

            }

            @Override
            public void onSuccess(String string) {
                super.onSuccess(string);
                Toast.makeText(mContext, "发送成功", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //登录
    public void getLogin(@NonNull String mobile, @NonNull String password, @UserType int type) {
        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(mContext, "密码长度不够", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("mobile", mobile);
        parameter.put("password", Md5Util.getStringMD5(password));
        parameter.put("type", type);
        Observable<UserEntity> observable = mUserApi.getLogin(parameter).map(new HttpResultFunc<UserEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<UserEntity>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(UserEntity userEntity) {
                if (mUserInterface != null) {
                    mUserInterface.loginSucceed(userEntity);
                }
            }
        });
    }
    // 验证验证码
    public void getVerifyCode(String phone,String code){
        if (mMobile == null) {
            Toast.makeText(mContext, "请获取验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mMobile.equals(phone)) {
            Toast.makeText(mContext, "请重新获取验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StrUtil.isMobileNO(phone)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        int verificationCode = Integer.parseInt(code);

        if (verificationCode != mVerificationCode) {
            Toast.makeText(mContext, "验证码输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }else{
            mUserInterface.onSucceed();
        }
    }
    // 修改密码
    public void getForgetPassword(String mobile,String password,String confirmPassword,@UserType int type){
        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(mContext, "密码长度不够", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(mContext, "两次密码输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("mobile",mobile);
        hashMap.put("password",Md5Util.getStringMD5(password));
        hashMap.put("type",type);
        Observable<BaseHttpResult> observable = mUserApi.getForgetPassword(hashMap);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            public void onNext(BaseHttpResult value) {
               Toast.makeText(mContext,value.getMessage(),Toast.LENGTH_SHORT).show();
                mUserInterface.onSucceed();
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
            }
        });
    }


}
