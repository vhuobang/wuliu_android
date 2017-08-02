package com.arkui.transportation_owner.activity.waybill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;


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
