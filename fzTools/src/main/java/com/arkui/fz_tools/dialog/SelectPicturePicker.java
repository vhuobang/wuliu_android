package com.arkui.fz_tools.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.listener.OnPictureClickListener;
import com.arkui.fz_tools.utils.LogUtil;

/**
 * Created by nmliz on 2017/6/22.
 */

public class SelectPicturePicker extends BaseDialogFragment implements View.OnClickListener {


    private OnPictureClickListener mOnPictureClickListener;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_select_picture, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mRootView.findViewById(R.id.tv_photo).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_camera).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_photo) {
            if (mOnPictureClickListener != null) {
                mOnPictureClickListener.onClick(0);
            }
        } else if (v.getId() == R.id.tv_camera) {
            mOnPictureClickListener.onClick(1);
        } else if (v.getId() == R.id.tv_cancel) {
        }
        dismiss();
    }

    public void setOnPictureClickListener(OnPictureClickListener mOnPictureClickListener) {
        this.mOnPictureClickListener = mOnPictureClickListener;
    }

    /**
     * 弹出Dialog
     * @param activity
     * @param redDialog
     */
    public void showDialog(FragmentActivity activity, String redDialog) {
        if (activity==null){
            LogUtil.e("依赖的activity为null");
            return;
        }
        if (activity.isFinishing()){
            LogUtil.e("依赖的activity被干掉");
            return;
        }
        if(this.isAdded()) {
            LogUtil.e("之前被添加过");
            return;
        }

        if (this==null){
            LogUtil.e("找不到dialog");
            return;
        }
        if (this.isVisible()){
            LogUtil.e("dialog 正在显示");
            return;
        }
        LogUtil.e("开始正式的提交");
        try{
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(this, redDialog);
            transaction.commitAllowingStateLoss();
        }catch (Exception e){
            LogUtil.e(Log.getStackTraceString(e));
        }


    }

}
