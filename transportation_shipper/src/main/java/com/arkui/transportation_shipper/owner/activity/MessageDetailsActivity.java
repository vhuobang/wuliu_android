package com.arkui.transportation_shipper.owner.activity;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


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
