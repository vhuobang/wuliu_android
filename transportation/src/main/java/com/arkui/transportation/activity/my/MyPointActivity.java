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


public class MyPointActivity extends BaseActivity implements UserInterface {

    UserPresenter userPresenter;
    private TextView tvIntegral;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_my_point);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
      //  String points = getIntent().getStringExtra("points");
        userPresenter = new UserPresenter(this, this);
        tvIntegral = (TextView) findViewById(R.id.integral);
      //  tvIntegral.setText(points);
    }

    @OnClick({R.id.bt_withdraw, R.id.iv_back,R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_withdraw:
                showActivity(PointWithdrawActivity.class);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showActivity(WithdrawRecordActivity.class);
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
        tvIntegral.setText(userEntity.getIntegral());
    }

   /* @Override
    protected void onRightClick() {
        super.onRightClick();

    }*/
}
