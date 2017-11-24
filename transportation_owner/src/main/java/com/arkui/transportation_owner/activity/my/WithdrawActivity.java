package com.arkui.transportation_owner.activity.my;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.BankCarEntity;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.mvp.WithDrawPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WithdrawActivity extends BaseActivity implements PublicInterface, UserInterface {
    public static final int REQUEST_CODE = 1;
    public static final String RECHARGE_TYPE_BALANCE = "1";
    public static final String RECHARGE_TYPE_INTEGRAL = "2";
    private BankCarEntity bankCarInfo;
    private WithDrawPresenter withDrawPresenter;
    @BindView(R.id.et_with_draw_number)
    EditText withDrawNumber;
    private UserPresenter userPresenter;
    @BindView(R.id.can_withdraw_number)
    TextView canWithDrawNumber;
    private UserEntity mUserEntity;
    @BindView(R.id.iv_bank)
    ImageView bankIcon;
    @BindView(R.id.tv_name)
    TextView bankName;
    @BindView(R.id.tv_content)
    TextView tvContent;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw);
        setTitle("提现");
        setRight("添加银行卡");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        withDrawPresenter = new WithDrawPresenter(this, this);
        userPresenter = new UserPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userPresenter.getUserInfo(App.getUserId());
    }

    @OnClick({R.id.rl_bank, R.id.bt_start, R.id.withdraw_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_bank:
                Intent intent = new Intent(WithdrawActivity.this, SelectBankCardActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.bt_start:
                String withDrawMoney = withDrawNumber.getText().toString().trim();
                if (TextUtils.isEmpty(withDrawMoney) || 0 == Double.parseDouble(withDrawMoney)) {
                    Toast.makeText(mActivity, "请输入提现金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (null == bankCarInfo) {
                    Toast.makeText(mActivity, "请选择银行卡", Toast.LENGTH_SHORT).show();
                    return;
                }
                withDrawPresenter.getWithDraw(App.getUserId(), RECHARGE_TYPE_BALANCE, withDrawMoney,
                        bankCarInfo.getNumber(), bankCarInfo.getName(), bankCarInfo.getNote());

                break;
            case R.id.withdraw_all:
                withDrawNumber.setText(mUserEntity.getBalance());
                break;
        }
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(AddBankActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    bankCarInfo = (BankCarEntity) data.getSerializableExtra("bankCarInfo");
                    GlideUtils.getInstance().load(mActivity, bankCarInfo.getIcon(), bankIcon);
                    bankName.setText(bankCarInfo.getNote());
                    int length = bankCarInfo.getNumber().length();
                    tvContent.setText("尾号" + bankCarInfo.getNumber().substring(length-4, length));
                    break;
            }
        }
    }
    // 提现成功
    @Override
    public void onSuccess() {
        WithdrawSucceedActivity.openActivity(WithdrawActivity.this, withDrawNumber.getText().toString().trim());
        finish();
    }

    // 提现失败
    @Override
    public void onFail(String message) {
          ShowToast(message);
    }

    //
    @Override
    public void onSucceed() {

    }

    // 得到用户信息
    @Override
    public void loginSucceed(UserEntity userEntity) {
        mUserEntity = userEntity;
        canWithDrawNumber.setText("可提现金额: " + userEntity.getBalance() + "元");
    }
}
