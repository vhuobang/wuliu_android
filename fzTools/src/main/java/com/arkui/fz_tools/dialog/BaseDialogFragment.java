package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.utils.LogUtil;

/**
 * Created by nmliz on 2017/6/22.
 */

public abstract   class BaseDialogFragment extends DialogFragment {

    private View mRootView;
    protected boolean mIsCanceledOnTouch =true;

    protected abstract View inflaterView(LayoutInflater inflater,
                                         ViewGroup container, Bundle bundle);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView= inflaterView(inflater, container, savedInstanceState);
        initView(mRootView);
        return mRootView;
    }

    protected abstract void initView(View mRootView);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.TransparentAppTheme);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //设置背景
        getDialog().setCancelable(mIsCanceledOnTouch);
        getDialog().setCanceledOnTouchOutside(mIsCanceledOnTouch);
        /*Window window = getDialog().getWindow();
        window.getAttributes().dimAmount = 0.8f;
        window.setLayout(dm.widthPixels, window.getAttributes().height);
        window.getAttributes().gravity = getGravity();*/
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = getGravity(); // 紧贴底部
        lp.width = (int) (dm.widthPixels*getWidthScale()); // 宽度持平
        window.setAttributes(lp);
    }



    public int getGravity() {
        return Gravity.CENTER;
    }

    protected float getWidthScale() {
        return 1f;
    }

    /**
     * dp to px
     */
    protected int dp2px(float dp) {
        final float scale = getActivity().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
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

        //这句关键代码能解决崩溃的问题 避免重复Add
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
