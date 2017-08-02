package com.arkui.transportation.activity.waybill;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class CargoInfoActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_cargo_info);
        setTitle("货物信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);


    }

}
