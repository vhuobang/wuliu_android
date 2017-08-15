package com.arkui.transportation_owner.activity.user;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PasswordResetActivity extends BaseMvpActivity<UserPresenter> implements UserInterface, OnConfirmClick {

    @BindView(R.id.et_new_password)
    ShapeEditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    ShapeEditText etConfirmPassword;
    private CommonDialog mCommonDialog;
    private String phone;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_password_reset);
        setTitle("密码重置");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("重置密码").setContent("密码重置成功！").setNoCancel();
        mCommonDialog.setConfirmClick(this);
    }

    @OnClick(R.id.bt_reset)
    public void onClick() {
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        mPresenter.getForgetPassword(phone,newPassword,confirmPassword, Constants.OWNER);

    }

    @Override
    public void onSucceed() {
        mCommonDialog.show(getSupportFragmentManager(), "reset");

    }

    @Override
    public void loginSucceed(UserEntity userEntity) {

    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }


    @Override
    public void onConfirmClick() {
        AppManager.getAppManager().finishAllActivity();
        showActivity(LoginActivity.class);
    }
}

