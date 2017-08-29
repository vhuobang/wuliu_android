package com.arkui.transportation.activity.my;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyBalanceActivity extends BaseActivity {

    private TextView tv_balance;

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
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_balance.setText(getIntent().getStringExtra("balance"));
    }

    @OnClick({R.id.bt_withdraw, R.id.iv_back, R.id.iv_right, R.id.bt_recharge,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showActivity(DetailBillActivity.class);
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
