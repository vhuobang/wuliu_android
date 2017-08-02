package com.arkui.transportation_owner.activity.user;

import android.view.View;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
        setTitle("用户注册");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_register, R.id.tv_clause})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                break;
            case R.id.tv_clause:
                showActivity(ClauseActivity.class);
                break;
        }
    }
}
