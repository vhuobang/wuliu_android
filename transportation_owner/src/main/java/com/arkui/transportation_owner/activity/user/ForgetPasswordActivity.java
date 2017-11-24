package com.arkui.transportation_owner.activity.user;


import android.Manifest;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.CodeInterface;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.CodeEntity;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.entity.VerPicEntity;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.IdentifyPresenter;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.ScreenUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation_owner.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class ForgetPasswordActivity extends BaseMvpActivity<UserPresenter> implements UserInterface, CodeInterface {

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
    @BindView(R.id.et_pic_code) // 图形验证码
            EditText etPicCode;
    @BindView(R.id.iv_pic)  // 图片
            ImageView ivPic;
    private IdentifyPresenter identifyPresenter;
    private TelephonyManager telephonyMgr;
    String szImei;
    RxPermissions mRxPermissions;
    private String varCode;

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
        identifyPresenter = new IdentifyPresenter(this, this);
        mRxPermissions = new RxPermissions(this);
        getMIEI();
    }
    private void getMIEI() {
        mRxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    telephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    szImei = telephonyMgr.getDeviceId();
                    getVarPic(szImei);
                }else {
                    Toast.makeText(ForgetPasswordActivity.this, "没有权限，无法获取验证码，建议你允许！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getVarPic(String szImei) {
        int weight = ScreenUtils.dp2px(this, 100);
        int height = ScreenUtils.dp2px(this, 50);
        System.out.println("高 ： " + height + "宽： " + weight + "设备id" + szImei);
        int textSize = ScreenUtils.dp2px(this, 12);
        identifyPresenter.getImageCode(weight,height,textSize,szImei);
    }

    @Override
    public void onSucceed() {

    }

    // 暂时没有用
    @Override
    public void loginSucceed(UserEntity userEntity) {

    }

    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    @OnClick({R.id.verify_code, R.id.bt_forget,R.id.iv_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verify_code:
                String phoneNumber = etPhone.getText().toString().trim();
                String picCode = etPicCode.getText().toString().trim();
                if (!StrUtil.isMobileNO(phoneNumber)) {
                    Toast.makeText(ForgetPasswordActivity.this, "手机号输入不合法", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(picCode)) {
                    Toast.makeText(ForgetPasswordActivity.this, "请输入图形验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                timeCountUtil.start();
                identifyPresenter.getVarCode(phoneNumber,picCode,szImei);
                break;
            case R.id.bt_forget:
                String code = etVerifyCode.getText().toString().trim();
                String mobile = etPhone.getText().toString().trim();

                if (varCode!=null && code.equals(varCode)){
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", mobile);
                    bundle.putString("code",code);
                    bundle.putString("deviceKey",szImei);
                    showActivity(PasswordResetActivity.class, bundle);
                }else {
                    ShowToast("验证码有误");
                }
                break;
            case R.id.iv_pic:
                if (!TextUtils.isEmpty(szImei)){
                    getVarPic(szImei);
                }else {
                    szImei = telephonyMgr.getDeviceId();
                    getVarPic(szImei);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void loadImageSuccess(VerPicEntity verPicEntity) {

        Glide.with(ForgetPasswordActivity.this)
                .load(verPicEntity.getUrl()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivPic);
    }

    @Override
    public void loadCodeSuccess(CodeEntity codeEntity) {
        varCode = String.valueOf(codeEntity.getCode());
    }

    @Override
    public void onFail(String errorMessage, int type) {

    }
}

