package com.arkui.fz_tools.mvp;

import android.os.Bundle;

import com.arkui.fz_tools._interface.BaseModel;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.TUtil;

/**
 * Created by nmliz on 2017/8/7.
 */

public abstract class BaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity {
    public T mPresenter;
    public E mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initPresenter();
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
