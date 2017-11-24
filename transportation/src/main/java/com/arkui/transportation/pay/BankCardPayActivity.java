package com.arkui.transportation.pay;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;

public class BankCardPayActivity extends BaseActivity {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bind_bank_card);
        setTitle("快捷支付");
        ButterKnife.bind(this);
    }


}
