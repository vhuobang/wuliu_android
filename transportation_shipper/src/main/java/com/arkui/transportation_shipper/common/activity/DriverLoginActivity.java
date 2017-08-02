package com.arkui.transportation_shipper.common.activity;

import android.view.View;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.driver.activity.DriverMainActivity;
import com.arkui.transportation_shipper.owner.activity.asset.DriverDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.DriverManageAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class DriverLoginActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_driver_login);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.tv_owner_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                showActivity(DriverMainActivity.class);
                finish();
                break;
            case R.id.tv_owner_login:
                showActivity(LoginActivity.class);
                finish();
                break;
        }
    }
}
