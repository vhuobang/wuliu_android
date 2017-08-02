package com.arkui.transportation_shipper.owner.activity.supply;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PayMessageFeeActivity extends BaseActivity {

    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_pay_message_fee);
        setTitle("支付信息费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("余额不足").setContent("您的余额不足（剩余36.00）请先充值在支付!").setConfirmText("立即充值").setNoCancel();
        mCommonDialog.setConfirmClick(new OnConfirmClick() {
            @Override
            public void onConfirmClick() {
                ShowToast("待完善！");
            }
        });
    }

    @OnClick(R.id.bt_start)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(),"pay");
    }
}
