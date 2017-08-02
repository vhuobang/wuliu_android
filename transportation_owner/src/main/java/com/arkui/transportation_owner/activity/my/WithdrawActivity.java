package com.arkui.transportation_owner.activity.my;

import android.view.View;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class WithdrawActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw);
        setTitle("提现");
        setRight("添加银行卡");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_bank, R.id.bt_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_bank:
                showActivity(SelectBankCardActivity.class);
                break;
            case R.id.bt_start:
                showActivity(WithdrawSucceedActivity.class);
                break;
        }
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(AddBankActivity.class);
    }
}
