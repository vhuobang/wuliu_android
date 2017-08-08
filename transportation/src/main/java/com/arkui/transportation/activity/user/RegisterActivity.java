package com.arkui.transportation.activity.user;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.model.UserModel;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.utils.ToastUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.arkui.transportation.R.id.et_new_password;
import static com.arkui.transportation.R.id.et_phone;


public class RegisterActivity extends BaseMvpActivity<UserPresenter,UserModel>implements UserInterface {

    @BindView(et_phone)
    ShapeEditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.ll_code)
    ShapeLinearLayout llCode;
    @BindView(et_new_password)
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
     private TimeCountUtil mTimeCountUtil;

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

    @Override
    public void initData() {
        mTimeCountUtil= new TimeCountUtil(tvCode);
    }

    @OnClick({R.id.bt_register, R.id.tv_clause,R.id.tv_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                if (checkbox.isChecked()){
                    getRegister();
                }else {
                    ToastUtil.showToast(RegisterActivity.this,"免责声明未勾选");
                }
                break;
            case R.id.tv_code:
                String phoneNumber = etPhone.getText().toString().trim();
                mPresenter.getCode(phoneNumber,mTimeCountUtil);
                break;
            case R.id.tv_clause:
                showActivity(ClauseActivity.class);
                break;
            case R.id.tv_back_login:
                finish();
                break;
        }
    }

    /**
     * 注册
     */
    private void getRegister() {
        String phoneNumber = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String newPassWord = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String invite = etInvite.getText().toString().trim();
        mPresenter.getRegister(phoneNumber,code,newPassWord,confirmPassword, Constants.LOGISTICS,invite);

    }

    // 初始化
    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this,mModel);
    }

    /**
     *  成功的回掉
     */
    @Override
    public void onSucceed() {
           finish();
    }

    /**
     * 这个是登陆回调 暂时不用
     * @param userEntity
     */
    @Override
    public void loginSucceed(UserEntity userEntity) {

    }
}
