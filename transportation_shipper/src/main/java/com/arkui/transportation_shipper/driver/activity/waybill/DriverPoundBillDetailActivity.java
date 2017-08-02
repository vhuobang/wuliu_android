package com.arkui.transportation_shipper.driver.activity.waybill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class DriverPoundBillDetailActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_pound_bill_detail);
        setTitle("磅单详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
