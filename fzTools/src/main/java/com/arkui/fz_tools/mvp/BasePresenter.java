package com.arkui.fz_tools.mvp;

import android.app.Activity;
import android.widget.Toast;

import com.arkui.fz_tools._interface.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:基类presenter
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */
public abstract class BasePresenter {
    public Activity mContext;
    public List<Disposable> mDisposables = new ArrayList<>();

    public void showToast(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    public void onDestroy(){
        if (mDisposables != null) {
            for (Disposable disposable : mDisposables) {
                disposable.dispose();
            }
            mDisposables.clear();
        }
    }
}
