package com.arkui.transportation_shipper.owner.activity.waybill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class LogisticsInfoActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_logistics_info);
        setTitle("物流信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
