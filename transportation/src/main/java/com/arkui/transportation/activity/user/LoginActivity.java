package com.arkui.transportation.activity.user;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.SPUtil;
import com.arkui.fz_tools.utils.SystemBarHelper;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MainActivity;
import com.arkui.transportation.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseMvpActivity<UserPresenter> implements UserInterface {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.ll_phone)
    ShapeLinearLayout llPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ll_password)
    ShapeLinearLayout llPassword;
    @BindView(R.id.bt_login)
    ShapeButton btLogin;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_register)
    TextView tvRegister;

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
        boolean isLogin = SPUtil.getInstance(this).read(Constants.IS_LOGIN, false);
        if (isLogin){
            showActivity(MainActivity.class);
            finish();
        }
    }
    @OnClick({R.id.bt_login, R.id.tv_forget, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                // showActivity(MainActivity.class);
                // finish();
                getLogin();
                break;
            case R.id.tv_forget:
                showActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tv_register:
                showActivity(RegisterActivity.class);
                break;

        }
    }

    /**
     * 登陆
     */
    private void getLogin() {
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String passWord = etPassword.getText().toString().trim();
        mPresenter.getLogin(phoneNumber,passWord, Constants.LOGISTICS);
    }

    /**
     * 初始化 presenter
     */
    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    @Override
    public void onSucceed() {

    }

    /**
     * 登陆成功的回调
     *
     * @param userEntity
     */
    @Override
    public void loginSucceed(UserEntity userEntity) {
        App.setUserEntity(userEntity);
        showActivity(MainActivity.class);
        finish();
    }

}
