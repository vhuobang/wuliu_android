package com.arkui.transportation.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FreightPaymentSucceedActivity extends BaseActivity {

    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.bt_succeed)
    ShapeButton mBtSucceed;

    public static void openActivity(Context context, String money) {
        Intent intent = new Intent(context, FreightPaymentSucceedActivity.class);
        intent.putExtra("money", money);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_freight_payment_succeed);
        setTitle("支付运费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        String money = getIntent().getStringExtra("money");
        mMoney.setText(money);
    }



    @OnClick(R.id.bt_succeed)
    public void onViewClicked() {
        finish();
        AppManager.getAppManager().finishActivity(WaybillDetailActivity.class);
    }
}
