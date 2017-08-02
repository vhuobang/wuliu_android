package com.arkui.transportation.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class AboutActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_about);
        setTitle("关于我们");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
