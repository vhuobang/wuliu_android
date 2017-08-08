package com.arkui.transportation_shipper.common.activity;

import android.view.View;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.UserModel;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.SystemBarHelper;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.owner.activity.MainActivity;

import butterknife.OnClick;

/**
 * 车主登陆界面
 */

public class LoginActivity extends BaseMvpActivity<UserPresenter>implements UserInterface {

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_login);
    }

    @Override
    public void initView() {
        super.initView();

        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.white), 0);
        SystemBarHelper.setStatusBarDarkMode(this);
    }

    @OnClick({R.id.bt_login, R.id.tv_forget, R.id.tv_register, R.id.tv_driver_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                getLogin();
                break;
            case R.id.tv_forget:
                showActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tv_register:
                showActivity(RegisterActivity.class);
                break;
            case R.id.tv_driver_login:
                showActivity(DriverLoginActivity.class);
                break;
        }
    }

    private void getLogin() {

    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    @Override
    public void onSucceed() {

    }

    /**
     * 登陆成功回掉
     * @param userEntity
     */
    @Override
    public void loginSucceed(UserEntity userEntity) {
        App.setUserEntity(userEntity);
        showActivity(MainActivity.class);
        finish();
    }
}
