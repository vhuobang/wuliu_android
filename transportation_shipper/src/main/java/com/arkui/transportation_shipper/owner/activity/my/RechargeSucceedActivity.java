package com.arkui.transportation_shipper.owner.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class RechargeSucceedActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recharge_succeed);
        setTitle("支付成功");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_succeed)
    public void onClick() {
        finish();
    }
}
