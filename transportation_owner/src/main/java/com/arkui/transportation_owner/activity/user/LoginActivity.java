package com.arkui.transportation_owner.activity.user;

import android.view.View;
import android.widget.EditText;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.model.UserModel;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.SystemBarHelper;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.MainActivity;
import com.arkui.transportation_owner.base.App;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseMvpActivity<UserPresenter> implements UserInterface {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;

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
                //showActivity(MainActivity.class);
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

    private void getLogin() {
        String phone = mEtPhone.getText().toString().trim();
        String password = mEtPassword.getText().toString();
        mPresenter.getLogin(phone,password, Constants.OWNER);
    }

   /* @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this, mModel);
    }
*/
    @Override
    public void onSucceed() {

    }

    //登录成功回调
    @Override
    public void loginSucceed(UserEntity userEntity) {
        App.setUserEntity(userEntity);
        showActivity(MainActivity.class);
    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }
}
