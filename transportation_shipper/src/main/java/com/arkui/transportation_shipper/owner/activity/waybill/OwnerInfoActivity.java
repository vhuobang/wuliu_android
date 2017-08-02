package com.arkui.transportation_shipper.owner.activity.waybill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class OwnerInfoActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_owner_info);
        setTitle("货主信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
