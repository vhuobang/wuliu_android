package com.arkui.fz_tools.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.UserApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nmliz on 2017/8/9.
 * 认证功能
 */

public class AuthenticationPresenter extends BasePresenter {

    public PublicInterface mPublicInterface;
    private final UserApi mUserApi;

    public AuthenticationPresenter(Activity context, PublicInterface publicInterface) {
        mPublicInterface = publicInterface;
        mContext = context;
        mUserApi = RetrofitFactory.createRetrofit(UserApi.class);
    }

    //提交个人认证
    public void postPersonalAuth(@NonNull String user_id, @NonNull String name, @NonNull String address, @NonNull String detailed_address
            , @NonNull String id_card, @Nullable String id_photo_front, @Nullable String id_photo_back, @Nullable String id_photo_hold, @Nullable String drive_photo) {

        Map<String, Object> parameter = new HashMap<>();

        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            showToast("请输入地址");
            return;
        }
        if (TextUtils.isEmpty(detailed_address)) {
            showToast("请输入详细地址");
            return;
        }
        if (TextUtils.isEmpty(id_card)) {
            showToast("请输入身份证号");
            return;
        }
        if (id_photo_front == null) {
            showToast("请上传身份证正面照");
            return;
        }
        if (id_photo_back == null) {
            showToast("请上传身份证反面照");
            return;
        }
        if (id_photo_hold == null) {
            showToast("请上传手持身份证照");
            return;
        }

        parameter.put("user_id", user_id);
        parameter.put("name", name);
        parameter.put("address", address);
        parameter.put("detailed_address", detailed_address);
        parameter.put("id_card", id_card);
        parameter.put("id_photo_front", id_photo_front);
        parameter.put("id_photo_back", id_photo_back);
        parameter.put("id_photo_hold", id_photo_hold);
        if (drive_photo != null) {
            parameter.put("drive_photo", drive_photo);
        }

        Observable<BaseHttpResult> observable = mUserApi.postPersonalAuth(parameter);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {

            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                if (mPublicInterface != null) {
                    mPublicInterface.onSuccess();
                }
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                if (mPublicInterface != null) {
                    mPublicInterface.onFail(e.getMessage());
                }
            }
        });
    }

    //认证企业
    public void postCompanyAuth(@NonNull String user_id, @NonNull String name, @NonNull String address, @NonNull String detailed_address
            , @NonNull String license_card, @NonNull String handler_name, @NonNull String handler_card,
                                @Nullable String license_photo, @Nullable String handler_photo_hold) {

        Map<String, Object> parameter = new HashMap<>();

        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            showToast("请输入地址");
            return;
        }
        if (TextUtils.isEmpty(detailed_address)) {
            showToast("请输入详细地址");
            return;
        }
        if (TextUtils.isEmpty(license_card)) {
            showToast("请输入营业执照号");
            return;
        }
        if (TextUtils.isEmpty(handler_name)) {
            showToast("请输入经办人姓名");
            return;
        }
        if (TextUtils.isEmpty(handler_card)) {
            showToast("请输入经办人身份证号");
            return;
        }
        if (license_photo == null) {
            showToast("请上传营业执照");
            return;
        }
        if (handler_photo_hold == null) {
            showToast("请上传经办人手持营业执照");
            return;
        }

        parameter.put("user_id", user_id);
        parameter.put("name", name);
        parameter.put("address", address);
        parameter.put("detailed_address", detailed_address);
        parameter.put("license_card", license_card);
        parameter.put("handler_name", handler_name);
        parameter.put("handler_card", handler_card);
        parameter.put("license_photo", license_photo);
        parameter.put("handler_photo_hold", handler_photo_hold);

        Observable<BaseHttpResult> observable = mUserApi.postCompanyAuth(parameter);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {

            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                if (mPublicInterface != null) {
                    mPublicInterface.onSuccess();
                }
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                if (mPublicInterface != null) {
                    mPublicInterface.onFail(e.getMessage());
                }
            }
        });
    }


}
