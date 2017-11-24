package com.arkui.transportation_owner.activity.my;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WithdrawSucceedActivity extends BaseActivity {

    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.bt_succeed)
    ShapeButton mBtSucceed;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw_succeed);
        setTitle("提现成功");
    }

    public static void openActivity(Context context, String withDrawNumber) {
        Intent intent = new Intent(context, WithdrawSucceedActivity.class);
        intent.putExtra("money", withDrawNumber);
        context.startActivity(intent);

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        String money = getIntent().getStringExtra("money");
        mMoney.setText(money);

    }

    @OnClick(R.id.bt_succeed)
    public void onClick() {
        finish();
    }


}
