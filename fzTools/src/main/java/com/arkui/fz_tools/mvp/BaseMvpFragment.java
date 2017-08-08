package com.arkui.fz_tools.mvp;

import com.arkui.fz_tools._interface.BaseModel;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.TUtil;

/**
 * Created by 84658 on 2017/8/8.
 */

public abstract  class BaseMvpFragment<T extends BasePresenter> extends BaseFragment {

    public T mPresenter;
    //public E mModel;
    @Override
    protected void initMvp() {
        mPresenter = TUtil.getT(this, 0);
      //  mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = mContext;
        }
        initPresenter();
    }


    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter=null;
        }
       /* if (mModel!=null){
            mModel=null;
        }*/

    }
}
