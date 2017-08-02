package com.arkui.transportation_shipper.owner.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


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
