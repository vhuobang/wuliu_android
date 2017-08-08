package com.arkui.fz_tools.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.UserModel;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.utils.UserType;

/**
 * Created by nmliz on 2017/8/7.
 */

public class UserPresenter extends BasePresenter<UserModel> {

    private UserInterface mUserInterface;
    private int mVerificationCode = -1;
    private String mMobile = null;

    public UserPresenter() {
    }

    public UserPresenter(UserInterface userInterface, UserModel userModel) {
        super();
        this.mUserInterface=userInterface;
        this.mModel = userModel;
    }

    public void setUserInterface(UserInterface userInterface, UserModel userModel) {
        mUserInterface = userInterface;
        this.mModel = userModel;
        mModel.initModel();
    }

    public void getRegister(@NonNull String mobile, @NonNull String code, @NonNull String password, @NonNull String confirmPassword, @UserType int type, @Nullable String invitation_code) {

        if (mMobile == null) {
            Toast.makeText(mContext, "请获取验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!mMobile.equals(mobile)){
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

        mModel.getRegister(mobile, password, type, invitation_code, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            public void onNext(BaseHttpResult value) {
                Toast.makeText(mContext, value.getMessage(), Toast.LENGTH_SHORT).show();
                if (mUserInterface != null) {
                    mUserInterface.onSucceed();
                }
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                //注册失败
               // Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
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
        mMobile=mobile;
        mVerificationCode = (int) ((Math.random() * 9 + 1) * 100000);
        mModel.getCode(mobile,mVerificationCode,mContext);
    }

    //登录
    public void getLogin(@NonNull String mobile, @NonNull String password, @UserType int type){
        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(mContext, "密码长度不够", Toast.LENGTH_SHORT).show();
            return;
        }

        mModel.getLogin(mobile, password, type, new ProgressSubscriber<UserEntity>(mContext) {
            @Override
            public void onNext(UserEntity userEntity) {
                if(mUserInterface!=null){
                    mUserInterface.loginSucceed(userEntity);
                }
            }
        });
    }

}
