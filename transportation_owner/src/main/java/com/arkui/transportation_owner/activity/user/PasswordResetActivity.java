package com.arkui.transportation_owner.activity.user;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PasswordResetActivity extends BaseActivity {

    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_password_reset);
        setTitle("密码重置");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("重置密码").setContent("密码重置成功！").setNoCancel();
    }

    @OnClick(R.id.bt_reset)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(),"reset");
    }
}
