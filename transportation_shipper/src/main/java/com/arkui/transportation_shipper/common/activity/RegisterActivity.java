package com.arkui.transportation_shipper.common.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserInterface;
import com.arkui.fz_tools.mvp.UserModel;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.utils.ToastUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation_shipper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseMvpActivity<UserPresenter, UserModel> implements UserInterface {

    @BindView(R.id.et_phone)
    ShapeEditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.ll_code)
    ShapeLinearLayout llCode;
    @BindView(R.id.et_new_password)
    ShapeEditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    ShapeEditText etConfirmPassword;
    @BindView(R.id.et_invite)
    ShapeEditText etInvite;
    @BindView(R.id.bt_register)
    ShapeButton btRegister;
    @BindView(R.id.tv_back_login)
    TextView tvBackLogin;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_clause)
    TextView tvClause;
    private TimeCountUtil timeCountUtil;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
        setTitle("用户注册");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        timeCountUtil = new TimeCountUtil(tvCode);
    }

    @OnClick({R.id.bt_register, R.id.tv_clause,R.id.tv_code,R.id.tv_back_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                if (checkbox.isChecked()){
                    getRegister();
                }else {
                    ToastUtil.showToast(RegisterActivity.this,"免责声明未勾选");
                }
                break;
            case R.id.tv_clause:

                showActivity(ClauseActivity.class);
                break;
            case  R.id.tv_back_login:
                finish();
                break;
            case R.id.tv_code:
                String phoneNumber = etPhone.getText().toString().trim();
                mPresenter.getCode(phoneNumber,timeCountUtil);
                break;
        }
    }

    /**
     * 注册
     */
    private void getRegister() {
        String phoneNumber = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String inviteCode = etInvite.getText().toString().trim();
        mPresenter.getRegister(phoneNumber,code,newPassword,confirmPassword, Constants.CAR_OWNER,inviteCode);
    }

    /**
     * 初始化 presenter
     */
    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this, mModel);
    }

    /**
     * 成功回调方法
     */
    @Override
    public void onSucceed() {
        //注册成功
          finish();
    }

    @Override
    public void loginSucceed(UserEntity userEntity) {

    }

}
