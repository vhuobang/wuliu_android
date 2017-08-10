package com.arkui.fz_tools.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 84658 on 2017/8/8.
 */

public class PublicPresenter extends BasePresenter {

    public PublicInterface mPublicInterface;
    public PublicApi mPublicApi;

    public void setPublicInterface(PublicInterface publicInterface) {
        mPublicInterface = publicInterface;

        mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }

    // 意见反馈
    public void getFaceBack(@NonNull String userId, @NonNull String content, @NonNull String tel) {
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showToast(mContext, "反馈内容不能为空");
            return;
        }
        if (!StrUtil.isMobileNO(tel)) {
            Toast.makeText(mContext, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("user_id",userId);
        hashMap.put("content",content);
        hashMap.put("tel",tel);
        Observable<BaseHttpResult> observable = mPublicApi.getFreedBack(hashMap);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mContext) {
            @Override
            public void onNext(BaseHttpResult value) {
                mPublicInterface.onSuccess();

            }

            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mPublicInterface.onFail(e.getMessage());
            }

        });

    }
}
