package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WaybillDetailActivity extends BaseActivity {

    @BindView(R.id.tv_driver_location)
    TextView mTvDriverLocation;
    @BindView(R.id.tv_pay_freight)
    TextView mTvPayFreight;
    @BindView(R.id.tr_wait_pay)
    TableRow mTrWaitPay;
    @BindView(R.id.tv_evaluate)
    TextView mTvEvaluate;
    @BindView(R.id.tv_payment_name)
    TextView mTvPaymentName;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_waybill_detail);
        setTitle("运单详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        int type = getIntent().getIntExtra("type", -1);

        if (type == 5) {
            mTvDriverLocation.setVisibility(View.GONE);
            mTvPayFreight.setVisibility(View.VISIBLE);
            mTrWaitPay.setVisibility(View.VISIBLE);
        } else if (type == 6) {
            mTvEvaluate.setVisibility(View.VISIBLE);
            mTvDriverLocation.setVisibility(View.GONE);
            mTrWaitPay.setVisibility(View.VISIBLE);
            mTvPaymentName.setText("实付款");
        }
    }

    @OnClick({R.id.tr_wait_pay, R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_logistics_info, R.id.tv_driver_location, R.id.tv_pay_freight,R.id.tv_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tr_wait_pay:
                break;
            case R.id.tv_owner_info:
                break;
            case R.id.tv_cargo_info:
                showActivity(CargoInfoActivity.class);
                break;
            case R.id.tv_logistics_info:
                showActivity(LogisticsInfoActivity.class);
                break;
            case R.id.tv_driver_location:
                showActivity(DriverLocationActivity.class);
                break;
            case R.id.tv_pay_freight:
                showActivity(PaymentFreightActivity.class);
                break;
            case R.id.tv_evaluate:
                showActivity(PublishEvaluateActivity.class);
                break;
        }
    }

    public static void openActivity(Context context, int type) {
        Intent intent = new Intent(context, WaybillDetailActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
