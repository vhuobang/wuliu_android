package com.arkui.transportation.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class ContactServiceActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_contact_service);
        setTitle("联系客服");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
