package com.arkui.fz_tools.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.DestroyActivityUtils;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseActivity<T> extends AppCompatActivity  {

    // 视图组件
    public ViewGroup vg;
    public Activity mActivity;
    protected View mBaseView;// 标题栏基础布局
    protected ImageView mBack; // 返回
    protected ImageView mIv_Right; //左边自定义图标
    protected TextView mTitle; // 标题
    protected RelativeLayout rl_title_bg; // 标题
    protected TextView mTv_right;
    protected TextView tv_rightAndIv;//右边左文字右图
    protected EditText mTvSearch;
    private CommonAdapter commonAdapter;
    public CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mActivity = this;
       // DestroyActivityUtils.unDestroyActivityList.add(this);
        setRootView();
        //透明状态栏
       // SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.white), 0);
       // SystemBarHelper.setStatusBarDarkMode(this);
        initView();
        initView(savedInstanceState);
        initData();
        AppManager.getAppManager().addActivity(this);
    }

    public void initView() {

    }
    public void initView(Bundle savedInstanceState) {

    }

    /**
     * 设置root界面
     */
    public abstract void setRootView();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.common_base);
        vg = (ViewGroup) findViewById(R.id.linearlayout_base);
        getLayoutInflater().inflate(layoutResID, vg);
        setupView();
    }

    public ViewDataBinding setDataBindingContentView(int layoutResID) {
        super.setContentView(R.layout.common_base);
        vg = (ViewGroup) findViewById(R.id.linearlayout_base);
        setupView();
        ViewDataBinding inflate = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, vg, false);
        vg.addView(inflate.getRoot());
        return inflate;
    }

    public void setContentViewNoTitle(int layoutResID) {
        super.setContentView(layoutResID);
    }


    private void setupView() {
        mTitle = (TextView) findViewById(R.id.tv_titles);
        mBack = (ImageView) findViewById(R.id.iv_back);
        mIv_Right = (ImageView) findViewById(R.id.iv_right);
        rl_title_bg = (RelativeLayout) findViewById(R.id.rl_title_bg);
         mTv_right = (TextView) findViewById(R.id.tv_right);
        //   tv_rightAndIv = (TextView) findViewById(R.id.tv_rightAndIv);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       mTv_right.setOnClickListener(rightClick);
        mIv_Right.setOnClickListener(rightClick);
        // tv_rightAndIv.setOnClickListener(rightClick);

        //搜索
        mTvSearch = (EditText) findViewById(R.id.tv_search);
    }

    private View.OnClickListener rightClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onRightClick();
        }
    };

    public void setRightIcon(int resId) {
        mIv_Right.setImageResource(resId);
    }

    //设置你的布局
    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    //跳转并关闭当前页面
    public void skipActivity(Class<?> cls) {
        showActivity(cls);
        finish();
    }

    //跳转并关闭当前页面,并携带参数
    public void skipActivity(Intent it) {
        showActivity(it);
        finish();
    }

    //跳转并不关闭当前页面
    public void showActivity(Class cls) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        startActivity(intent);
    }

    //跳转并不关闭当前页面,并且携带参数
    public void showActivity(Intent it) {
        startActivity(it);
    }

    public void showActivity(Class<?> cls,Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initData() {
    }


    protected void onRightClick() {

    }

    public void ShowToast(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_LONG).show();
    }

    protected void setRight(String string) {
        mTv_right.setText(string);
    }

   /* protected void setRightColor(int color) {
        mTv_right.setTextColor(mActivity.getResources().getColor(color));
    }

    protected void setRightClickable(boolean b) {
        if (b) {
            mTv_right.setTextColor(mActivity.getResources().getColor(R.color.black_list_color));
            mTv_right.setClickable(true);
        } else {
            mTv_right.setTextColor(mActivity.getResources().getColor(R.color.gray_text_color));
            mTv_right.setClickable(false);
        }
    }*/
/*
    //设置右边文本左文字右图
    protected void setRightIvAndTv(String msg,int layoutID) {
        tv_rightAndIv.setVisibility(View.VISIBLE);
        tv_rightAndIv.setText(msg);
        tv_rightAndIv.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(mActivity,layoutID), null);
    }*/

    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected <T extends View> T bindView(View v, int id) {
        return (T) v.findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        DestroyActivityUtils.finishActivity(this);
        mDisposables.clear();
    }


}
