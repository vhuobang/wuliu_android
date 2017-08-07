package com.arkui.transportation_owner.activity.user;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserInterface;
import com.arkui.fz_tools.mvp.UserModel;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseMvpActivity<UserPresenter, UserModel> implements UserInterface {

    @BindView(R.id.et_phone)
    ShapeEditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.et_new_password)
    ShapeEditText mEtNewPassword;
    @BindView(R.id.et_confirm_password)
    ShapeEditText mEtConfirmPassword;
    @BindView(R.id.et_invite)
    ShapeEditText mEtInvite;
    @BindView(R.id.bt_register)
    ShapeButton mBtRegister;
    @BindView(R.id.tv_clause)
    TextView mTvClause;
    TimeCountUtil mTimeCountUtil;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    @BindView(R.id.cb_deal)
    CheckBox mCbDeal;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
        setTitle("用户注册");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mTimeCountUtil = new TimeCountUtil(mTvCode);
    }

    @OnClick({R.id.bt_register, R.id.tv_clause, R.id.tv_code, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                //注册
                getRegister();
                break;
            case R.id.tv_clause:
                showActivity(ClauseActivity.class);
                break;
            case R.id.tv_code:
                //获取验证码
                mPresenter.getCode(mEtPhone.getText().toString().trim(), mTimeCountUtil);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void getRegister() {
        if(!mCbDeal.isChecked()){
            ShowToast("请勾选免责声明");
            return;
        }
        String phoneText = mEtPhone.getText().toString().trim();
        String newPasswordText = mEtNewPassword.getText().toString().trim();
        String confirmPasswordText = mEtConfirmPassword.getText().toString().trim();
        String invite = mEtInvite.getText().toString().trim();
        String code = mEtCode.getText().toString();
        mPresenter.getRegister(phoneText, code, newPasswordText, confirmPasswordText, Constants.OWNER, invite);
    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this, mModel);
    }

    //登录 操作成功
    @Override
    public void onSucceed() {
        // showActivity(LoginActivity.class);
        finish();
    }
}
