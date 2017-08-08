package com.arkui.fz_tools.mvp;

import android.app.Activity;

import com.arkui.fz_tools._interface.BaseModel;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:基类presenter
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */
public abstract class BasePresenter< E extends BaseModel> {
    public Activity mContext;
    public E mModel;
    //public T mView;

    public void setVM(E m) {
        //this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }


    public void onDestroy() {
        mModel.onDestroy();
    }

    public void RefreshSucceed(){

    }

    public void LoadMoreSucceed(){

    }
}
