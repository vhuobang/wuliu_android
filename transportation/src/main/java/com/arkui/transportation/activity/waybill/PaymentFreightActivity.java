package com.arkui.transportation.activity.waybill;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PaymentFreightActivity extends BaseActivity implements OnConfirmClick {

    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_payment_freight);
        setTitle("支付运费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("支付运费").setContent("运费信息确认无误，立即支付运费");
        mCommonDialog.setConfirmClick(this);
    }

    @OnClick(R.id.bt_pay)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(),"pay");
    }

    @Override
    public void onConfirmClick() {
        //支付成功
        showActivity(FreightPaymentSucceedActivity.class);
    }
}
