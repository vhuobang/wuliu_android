package com.arkui.transportation.activity.my;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyBalanceActivity extends BaseActivity implements UserInterface {

    private TextView tv_balance;
    UserPresenter userPresenter;
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
        userPresenter = new UserPresenter(this, this);
        tv_balance = (TextView) findViewById(R.id.tv_balance);
      //  tv_balance.setText(getIntent().getStringExtra("balance"));
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

    @Override
    protected void onResume() {
        super.onResume();
        userPresenter.getUserInfo(App.getUserId());
    }

    @Override
    public void onSucceed() {

    }

    @Override
    public void loginSucceed(UserEntity userEntity) {
        tv_balance.setText(userEntity.getBalance());
    }
}
