package com.arkui.transportation.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkui.fz_tools._interface.WayBillDetialsInterface;
import com.arkui.fz_tools.entity.WayBillDetailEntity;
import com.arkui.fz_tools.mvp.WayBillDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WaybillDetailActivity extends BaseActivity implements WayBillDetialsInterface {

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
    private String waybillId;
    private WayBillDetailPresenter wayBillDetailPresenter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_waybill_detail);
        setTitle("运单详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        wayBillDetailPresenter = new WayBillDetailPresenter(this, this);
        int type = getIntent().getIntExtra("type", -1);
        waybillId = getIntent().getStringExtra("waybillId");

        if (type == 3) { // 待付款
            mTvDriverLocation.setVisibility(View.GONE);
            mTvPayFreight.setVisibility(View.VISIBLE);
            mTrWaitPay.setVisibility(View.VISIBLE);
        } else if (type == 4) {
            //代收款
            mTvDriverLocation.setVisibility(View.GONE);
            mTrWaitPay.setVisibility(View.VISIBLE);
            mTvPaymentName.setText("待收货款");
        } else if (type == 5) { //已完成
            mTrWaitPay.setVisibility(View.VISIBLE);
            mTvPaymentName.setText("实付款");
            mTvDriverLocation.setVisibility(View.GONE);
            mTvEvaluate.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        wayBillDetailPresenter.getWayBillDetail(waybillId);
    }

    @OnClick({R.id.tr_wait_pay, R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_driver_location, R.id.tv_pay_freight, R.id.tv_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tr_wait_pay:
                break;
            case R.id.tv_owner_info:
                Intent intent=new Intent(mActivity,OwnerInfoActivity.class);
                boolean isMy = getIntent().getBooleanExtra("isMy", false);
                intent.putExtra("isMy",isMy);
                startActivity(intent);
                //showActivity(OwnerInfoActivity.class);
                break;
            case R.id.tv_cargo_info:
                showActivity(CargoInfoActivity.class);
                break;
            case R.id.tv_driver_location:
                showActivity(DriverLocationActivity.class);
                break;
            case R.id.tv_pay_freight:
                showActivity(PaymentFreightActivity.class);
                break;
            case R.id.tv_evaluate:
                Bundle bundle = new Bundle();
                bundle.putString("wayBillId",waybillId);
                showActivity(PublishEvaluateActivity.class,bundle);
                break;
           /*
            case R.id.tv_logistics_info:
                showActivity(LogisticsInfoActivity.class);
                break;
            case R.id.tv_driver_location:
                showActivity(DriverLocationActivity.class);
                break;
           */
        }
    }

    public static void openActivity(Context context, int type,boolean isMy,String waybillId) {
        Intent intent = new Intent(context, WaybillDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("isMy", isMy);
        intent.putExtra("waybillId",waybillId);
        context.startActivity(intent);
    }

    // 返回数据
    @Override
    public void onSuccess(WayBillDetailEntity wayBillDetailEntity) {

    }

    @Override
    public void onFail(String message) {

    }
}
