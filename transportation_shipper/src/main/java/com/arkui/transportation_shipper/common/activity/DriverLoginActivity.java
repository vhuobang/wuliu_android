package com.arkui.transportation_shipper.common.activity;

import android.view.View;

import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.driver.activity.DriverMainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class DriverLoginActivity extends BaseMvpActivity<UserPresenter>implements UserInterface {

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_driver_login);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.tv_owner_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                showActivity(DriverMainActivity.class);
                finish();
                break;
            case R.id.tv_owner_login:
                showActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    @Override
    public void onSucceed() {

    }

    /**
     * 登陆成功
     * @param userEntity
     */
    @Override
    public void loginSucceed(UserEntity userEntity) {
        App.setUserEntity(userEntity);
        showActivity(DriverMainActivity.class);
        finish();
    }
}
