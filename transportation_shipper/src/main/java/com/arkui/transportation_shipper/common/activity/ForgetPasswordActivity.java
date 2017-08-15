package com.arkui.transportation_shipper.common.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation_shipper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgetPasswordActivity extends BaseMvpActivity<UserPresenter> implements UserInterface {

    @BindView(R.id.et_phone)
    ShapeEditText etPhone;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.verify_code)
    TextView verifyCode;
    @BindView(R.id.ll_phone)
    ShapeLinearLayout llPhone;
    @BindView(R.id.bt_forget)
    ShapeButton btForget;

    private TimeCountUtil timeCountUtil;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_forget_password);
        setTitle("密码找回");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        timeCountUtil = new TimeCountUtil(verifyCode);
    }

    @Override
    public void onSucceed() {
        Bundle bundle = new Bundle();
        bundle.putString("phone",etPhone.getText().toString().trim());
        showActivity(PasswordResetActivity.class,bundle);
    }

    // 暂时没有用
    @Override
    public void loginSucceed(UserEntity userEntity) {

    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    @OnClick({R.id.verify_code, R.id.bt_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verify_code:
                String phone = etPhone.getText().toString().trim();
                mPresenter.getCode(phone,timeCountUtil);
                break;
            case R.id.bt_forget:
                String code = etVerifyCode.getText().toString().trim();
                String mobile = etPhone.getText().toString().trim();
                mPresenter.getVerifyCode(mobile,code);
                break;
        }
    }
}
