package com.arkui.transportation_shipper.owner.activity.my;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.VerifyDao;
import com.arkui.fz_tools.mvp.BankPresenter;
import com.arkui.fz_tools.net.JsonData;
import com.arkui.fz_tools.net.ResultCallBack;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.utils.TimeCountUtil;
import com.arkui.fz_tools.view.ShapeTextView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddBankActivity extends BaseActivity implements PublicInterface {

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

    TimeCountUtil timeCountUtil ;
    private int mVerificationCode1;
    private String mobile;
    private BankPresenter mBankPresenter;

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
    }

    @Override
    protected void onRightClick() {
        String phoneNumber = mEtPehoneNumber.getText().toString().trim();
        String cardHostName = mEtCarHost.getText().toString().trim();
        String bankNumber = mEtBankNumber.getText().toString().trim();
        String trim = etCode.getText().toString().trim();
        if (!phoneNumber.equals(mobile)){
            Toast.makeText(mActivity, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!trim.equals(mVerificationCode1+"")){
            Toast.makeText(mActivity, "验证不正确", Toast.LENGTH_SHORT).show();
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
        HashMap<String ,Object> map= new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("name",cardHostName);
        map.put("number",bankNumber);
        map.put("tel",phoneNumber);

        mBankPresenter.addBank(map);

    }

    @OnClick(R.id.verification_code)
    public void onViewClicked() {

        mobile = mEtPehoneNumber.getText().toString().trim();
        if (!StrUtil.isMobileNO(mobile)) {
            Toast.makeText(mActivity, "手机号输入不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        timeCountUtil.start();

        mVerificationCode1 = (int) ((Math.random() * 9 + 1) * 100000);

        VerifyDao.getInstance().sendVer(mActivity, mobile, mVerificationCode1, new ResultCallBack() {
            @Override
            public void onSuccess(JsonData data) {

            }

            @Override
            public void onSuccess(String string) {
                super.onSuccess(string);
                Toast.makeText(mActivity, "发送成功", Toast.LENGTH_SHORT).show();

            }
        });
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
    }

}
