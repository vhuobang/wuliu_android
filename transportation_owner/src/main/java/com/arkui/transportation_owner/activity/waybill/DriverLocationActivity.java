package com.arkui.transportation_owner.activity.waybill;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;


public class DriverLocationActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_location);
        setTitle("司机位置");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
