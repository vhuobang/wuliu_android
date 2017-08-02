package com.arkui.transportation_shipper.owner.activity.asset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class DriverEditActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_edit);
        setTitle("编辑司机");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
