package com.arkui.transportation_shipper.common.activity;

import android.Manifest;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.CodeInterface;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.CodeEntity;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.entity.VerPicEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.IdentifyPresenter;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.utils.ScreenUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.fz_tools.view.ShapeLinearLayout;
import com.arkui.transportation_shipper.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class RegisterActivity extends BaseMvpActivity<UserPresenter> implements UserInterface, CodeInterface {

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
    private IdentifyPresenter identifyPresenter;
    private TelephonyManager telephonyMgr;
    @BindView(R.id.et_pic_code) // 图形验证码
            EditText etPicCode;
    @BindView(R.id.iv_pic)  // 图片
            ImageView ivPic;
    String szImei;
    RxPermissions mRxPermissions;

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
                    Toast.makeText(RegisterActivity.this, "没有权限，无法获取验证码，建议你允许！", Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.bt_register, R.id.tv_clause,R.id.tv_code,R.id.tv_back_login,R.id.iv_pic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                if (checkbox.isChecked()){
                    getRegister();
                }else {
                    Toast.makeText(mActivity,"免责声明未勾选",Toast.LENGTH_SHORT).show();

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
                String picCode = etPicCode.getText().toString().trim();
                if (!StrUtil.isMobileNO(phoneNumber)) {
                    Toast.makeText(RegisterActivity.this, "手机号输入不合法", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(picCode)) {
                    Toast.makeText(RegisterActivity.this, "请输入图形验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                timeCountUtil.start();
                identifyPresenter.getVarCode(phoneNumber,picCode,szImei);
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

    /**
     * 注册
     */
    private void getRegister() {
        String phoneNumber = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String inviteCode = etInvite.getText().toString().trim();
        mPresenter.getRegister(phoneNumber,code,newPassword,confirmPassword, Constants.CAR_OWNER,inviteCode,szImei);
    }

    /**
     * 初始化 presenter
     */
    @Override
    public void initPresenter() {
        mPresenter.setUserInterface(this);
    }

    /**
     * 成功回调方法
     */
    @Override
    public void onSucceed() {
        //注册成功
          finish();
    }
    //暂不使用
    @Override
    public void loginSucceed(UserEntity userEntity) {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void loadImageSuccess(VerPicEntity verPicEntity) {
        Glide.with(RegisterActivity.this)
                .load(verPicEntity.getUrl()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivPic);
    }

    @Override
    public void loadCodeSuccess(CodeEntity codeEntity) {
        Log.e("fz", "loadCodeSuccess: "+ codeEntity.getCode() );
    }

    @Override
    public void onFail(String errorMessage, int type) {

    }
}
