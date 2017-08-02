package com.arkui.transportation.activity;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class MessageDetailsActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_message_details);
        setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
