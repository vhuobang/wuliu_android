package com.arkui.transportation_owner.activity.my;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyBalanceActivity extends BaseActivity implements UserInterface {

    private TextView mBalance;
    UserPresenter userPresenter;
    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_my_balance);
       /* setTitle("我的余额");
        setRightIcon(R.mipmap.mingxi);*/
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        userPresenter = new UserPresenter(this, this);
        mBalance = (TextView) findViewById(R.id.balance);
      //  mBalance.setText(getIntent().getStringExtra("balance"));
    }
    @OnClick({R.id.bt_withdraw, R.id.iv_back,R.id.iv_right,R.id.bt_recharge,})
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
        mBalance.setText(userEntity.getBalance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (userPresenter!=null){
            userPresenter.onDestroy();
        }
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
*/
    /*@Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(DetailBillActivity.class);
    }*/
}
