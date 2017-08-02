package com.arkui.transportation.activity.user;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgetPasswordActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_forget_password);
        setTitle("密码找回");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_forget)
    public void onClick() {
        showActivity(PasswordResetActivity.class);
    }
}
