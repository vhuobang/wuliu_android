package com.arkui.transportation.activity.waybill;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class FreightPaymentSucceedActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_freight_payment_succeed);
        setTitle("支付运费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
