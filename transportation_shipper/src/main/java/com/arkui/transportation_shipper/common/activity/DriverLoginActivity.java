package com.arkui.transportation_shipper.common.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.SPUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.driver.activity.DriverMainActivity;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


public class DriverLoginActivity extends BaseMvpActivity<UserPresenter> implements UserInterface {

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
    @BindView(R.id.tv_owner_login)
    TextView tvOwnerLogin;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_driver_login);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        boolean isLogin = SPUtil.getInstance(this).read(Constants.IS_LOGIN, false);
        if (isLogin){
            showActivity(DriverMainActivity.class);
            finish();
        }
    }

    @OnClick({R.id.bt_login, R.id.tv_owner_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                getLogin();
                break;
            case R.id.tv_owner_login:
                showActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    /**
     * 登陆
     */
    private void getLogin() {
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String passWord = etPassword.getText().toString().trim();
        mPresenter.getLogin(phoneNumber, passWord, Constants.DRIVER,JPushInterface.getRegistrationID(mActivity));
    }


    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    @Override
    public void onSucceed() {

    }

    /**
     * 登陆成功
     *
     * @param userEntity
     */
    @Override
    public void loginSucceed(UserEntity userEntity) {
        App.setUserEntity(userEntity);
        showActivity(DriverMainActivity.class);
        JPushInterface.setAlias(mActivity, userEntity.getId(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });
        //标识位司机登录
        SPUtil.getInstance(mActivity).save("type",Constants.DRIVER);
        finish();
        AppManager.getAppManager().finishActivity(LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
