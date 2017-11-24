package com.arkui.transportation_owner.activity.my;

import android.Manifest;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.CodeInterface;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.entity.CodeEntity;
import com.arkui.fz_tools.entity.VerPicEntity;
import com.arkui.fz_tools.mvp.BankPresenter;
import com.arkui.fz_tools.mvp.IdentifyPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.ScreenUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.view.ShapeTextView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class AddBankActivity extends BaseActivity implements PublicInterface, CodeInterface {

    @BindView(R.id.et_car_host)
    EditText mEtCarHost;
    @BindView(R.id.et_bank_number)
    EditText mEtBankNumber;
    @BindView(R.id.et_pehone_number)
    EditText mEtPehoneNumber;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.verification_code)
    ShapeTextView mVerificationCode;
    @BindView(R.id.et_bank_branch)
    EditText mEtBankBranch;
    @BindView(R.id.et_province)
    EditText mEtProvince;
    @BindView(R.id.et_city)
    EditText mEtCity;
    TimeCountUtil timeCountUtil ;
    private int mVerificationCode1;
    private String mobile;
    private BankPresenter mBankPresenter;
    @BindView(R.id.et_pic_code) // 图形验证码
            EditText etPicCode;
    @BindView(R.id.iv_pic)  // 图片
            ImageView ivPic;


    private IdentifyPresenter identifyPresenter;
    private TelephonyManager telephonyMgr;
    String szImei;
    RxPermissions mRxPermissions;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_add_bank);
        setTitle("添加银行卡");
        setRight("完成");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        timeCountUtil= new TimeCountUtil(mVerificationCode);
        mBankPresenter = new BankPresenter(this,this);
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
                    Toast.makeText(AddBankActivity.this, "没有权限，无法获取验证码，建议你允许！", Toast.LENGTH_SHORT).show();
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
    protected void onRightClick() {
        String phoneNumber = mEtPehoneNumber.getText().toString().trim();
        String cardHostName = mEtCarHost.getText().toString().trim();
        String bankNumber = mEtBankNumber.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String bankBranch = mEtBankBranch.getText().toString().trim();
        String provinceName = mEtProvince.getText().toString().trim();
        String cityName = mEtCity.getText().toString().trim();
        if (!StrUtil.isMobileNO(phoneNumber)) {
            Toast.makeText(AddBankActivity.this, "手机号输入不合法", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(bankNumber)){
            Toast.makeText(mActivity, "银行卡为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cardHostName)){
            Toast.makeText(mActivity, "持卡人姓名为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(bankBranch)){
            Toast.makeText(mActivity, "请输入支行名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(provinceName)){
            Toast.makeText(mActivity, "请输入银行卡省份名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cityName)){
            Toast.makeText(mActivity, "请输入市区名", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String ,Object> map= new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("name",cardHostName);
        map.put("number",bankNumber);
        map.put("tel",phoneNumber);
        map.put("province",provinceName);
        map.put("city",cityName);
        map.put("bank_branch",bankBranch);
        map.put("code",code);
        map.put("deviceKey",szImei);
        mBankPresenter.addBank(map);

    }

    @OnClick({R.id.verification_code,R.id.iv_pic})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.verification_code:
                timeCountUtil.start();
                String phoneNumber = mEtPehoneNumber.getText().toString().trim();
                String picCode = etPicCode.getText().toString().trim();
                if (!StrUtil.isMobileNO(phoneNumber)) {
                    Toast.makeText(AddBankActivity.this, "手机号输入不合法", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(picCode)) {
                    Toast.makeText(AddBankActivity.this, "请输入图形验证码", Toast.LENGTH_LONG).show();
                    return;
                }
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

    //添加成功
    @Override
    public void onSuccess() {
        Toast.makeText(mActivity, "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBankPresenter.onDestroy();
        mDisposables.dispose();
        identifyPresenter.onDestroy();
    }

    @Override
    public void loadImageSuccess(VerPicEntity verPicEntity) {
        Glide.with(AddBankActivity.this)
                .load(verPicEntity.getUrl()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivPic);
    }

    @Override
    public void loadCodeSuccess(CodeEntity codeEntity) {

    }

    @Override
    public void onFail(String errorMessage, int type) {

    }
}
