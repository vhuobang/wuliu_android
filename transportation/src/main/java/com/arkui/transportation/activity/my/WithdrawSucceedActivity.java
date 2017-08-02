package com.arkui.transportation.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class WithdrawSucceedActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw_succeed);
        setTitle("提现成功");
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
