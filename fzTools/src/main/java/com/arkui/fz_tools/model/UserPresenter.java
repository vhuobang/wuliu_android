package com.arkui.fz_tools.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.mvp.BasePresenter;
import com.arkui.fz_tools.mvp.UserInterface;
import com.arkui.fz_tools.mvp.UserModel;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.UserType;

/**
 * Created by nmliz on 2017/8/7.
 */

public class UserPresenter extends BasePresenter<UserModel> {

    UserInterface mUserInterface;


    public void setUserInterface(UserInterface userInterface,UserModel userModel) {
        mUserInterface = userInterface;
        this.mModel=userModel;
    }


    public void getRegister(@NonNull String mobile, @NonNull String password, @NonNull String confirmPassword, @UserType int type, @Nullable String invitation_code) {
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
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
