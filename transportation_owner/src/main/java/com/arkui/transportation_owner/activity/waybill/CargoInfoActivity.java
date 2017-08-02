package com.arkui.transportation_owner.activity.waybill;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

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
