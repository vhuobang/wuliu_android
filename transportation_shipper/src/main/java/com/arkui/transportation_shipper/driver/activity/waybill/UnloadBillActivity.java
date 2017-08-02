package com.arkui.transportation_shipper.driver.activity.waybill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class UnloadBillActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_unload_bill);
        setTitle("卸货磅单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
