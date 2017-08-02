package com.arkui.transportation_shipper.owner.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountRechargeActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_account_recharge);
        setTitle("账户充值");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_start)
    public void onClick() {
        showActivity(RechargeSucceedActivity.class);
    }
}
