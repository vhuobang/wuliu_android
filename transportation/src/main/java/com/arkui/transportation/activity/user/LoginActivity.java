package com.arkui.transportation.activity.user;

import android.view.View;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.SystemBarHelper;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_login);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.white), 0);
        SystemBarHelper.setStatusBarDarkMode(this);
    }

    @OnClick({R.id.bt_login, R.id.tv_forget, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                showActivity(MainActivity.class);
                finish();
                break;
            case R.id.tv_forget:
                showActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tv_register:
                showActivity(RegisterActivity.class);
                break;

        }
    }
}
