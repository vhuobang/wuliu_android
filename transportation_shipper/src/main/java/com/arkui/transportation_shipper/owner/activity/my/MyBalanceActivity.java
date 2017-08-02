package com.arkui.transportation_shipper.owner.activity.my;

import android.view.View;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyBalanceActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_my_balance);
        /*setTitle("我的余额");
        setRightIcon(R.mipmap.mingxi);*/
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

   /* @OnClick({R.id.bt_recharge, R.id.bt_withdraw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_recharge:
                showActivity(AccountRechargeActivity.class);
                break;
            case R.id.bt_withdraw:
                showActivity(WithdrawActivity.class);
                break;
        }
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(DetailBillActivity.class);
    }*/

    @OnClick({R.id.bt_withdraw, R.id.iv_back,R.id.iv_right,R.id.bt_recharge,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showActivity(WithdrawRecordActivity.class);
                break;
            case R.id.bt_recharge:
                showActivity(AccountRechargeActivity.class);
                break;
            case R.id.bt_withdraw:
                showActivity(WithdrawActivity.class);
                break;
        }

    }
}
