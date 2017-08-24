package com.arkui.transportation.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.WayBillDetialsInterface;
import com.arkui.fz_tools.entity.WayBillDetailEntity;
import com.arkui.fz_tools.mvp.WayBillDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.arkui.transportation.R.id.tv_payment_name;
import static com.arkui.transportation.R.id.tv_product_number;


public class WaybillDetailActivity extends BaseActivity implements WayBillDetialsInterface {

    @BindView(R.id.tv_driver_location)
    TextView mTvDriverLocation;
    @BindView(R.id.tv_pay_freight)
    TextView mTvPayFreight;
    @BindView(R.id.tr_wait_pay)
    TableRow mTrWaitPay;
    @BindView(R.id.tv_evaluate)
    TextView mTvEvaluate;
    @BindView(tv_payment_name)
    TextView mTvPaymentName;
    @BindView(R.id.waybill_id)
    TextView mWaybillId;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.tv_car_owner_name)
    TextView mTvCarOwnerName;
    @BindView(R.id.tv_car_owner_phone)
    TextView mTvCarOwnerPhone;
    @BindView(R.id.tv_car_id)
    TextView mTvCarId;
    @BindView(tv_product_number)
    TextView mTvProductNumber;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.creat_time)
    EditText mCreatTime;
    @BindView(R.id.et_loading_weight)
    EditText mEtLoadingWeight;
    @BindView(R.id.iv_list)
    ImageView mIvList;
    @BindView(R.id.unloading_time)
    EditText mUnloadingTime;
    @BindView(R.id.unloading_weight)
    EditText mUnloadingWeight;
    @BindView(R.id.unloading_list)
    ImageView mUnloadingList;
    @BindView(R.id.payment_money)
    TextView mPaymentMoney;
    @BindView(R.id.tv_cargo_info)
    TextView mTvCargoInfo;
    @BindView(R.id.tv_owner_info)
    TextView mTvOwnerInfo;
    private String waybillId;
    private WayBillDetailPresenter wayBillDetailPresenter;
    private int type;

    private  WayBillDetailEntity mWayBillDetailEntity;

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
        type = getIntent().getIntExtra("type", -1);
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
        wayBillDetailPresenter.getWayBillDetail(waybillId, App.getUserId());
    }

    @OnClick({R.id.tr_wait_pay, R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_driver_location, R.id.tv_pay_freight, R.id.tv_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tr_wait_pay: // 需要付款
                break;
            case R.id.tv_owner_info:

                Intent intent = new Intent(mActivity, OwnerInfoActivity.class);
                boolean isMy = getIntent().getBooleanExtra("isMy", false);
                intent.putExtra("isMy", isMy);
                intent.putExtra("cargoId",mWayBillDetailEntity.getCargoId());
                intent.putExtra("ownerId",mWayBillDetailEntity.getOwnerCargoId());
                startActivity(intent);
                //showActivity(OwnerInfoActivity.class);
                break;
            case R.id.tv_cargo_info: // 货物信息
                CargoInfoActivity.openActivity(mActivity,mWayBillDetailEntity.getCargoId());

                break;
            case R.id.tv_driver_location: //司机位置
                showActivity(DriverLocationActivity.class);
                break;
            case R.id.tv_pay_freight: //支付运费
                showActivity(PaymentFreightActivity.class);
                break;
            case R.id.tv_evaluate: // 评价界面
                Bundle bundle = new Bundle();
                bundle.putString("wayBillId", waybillId);
                showActivity(PublishEvaluateActivity.class, bundle);
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

    public static void openActivity(Context context, int type, boolean isMy, String waybillId) {
        Intent intent = new Intent(context, WaybillDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("isMy", isMy);
        intent.putExtra("waybillId", waybillId);
        context.startActivity(intent);
    }

    /**
     * 请求数据成功
     *
     * @param entity
     */
    @Override
    public void onSuccess(WayBillDetailEntity entity) {
        mWayBillDetailEntity= entity;
        mWaybillId.setText(entity.getOrderNumber());
        mTvCarOwnerName.setText(entity.getOwnerName());
        mTvCarOwnerPhone.setText(entity.getTel());
        mTvCarId.setText(entity.getLicensePlate());
        mTvProductNumber.setText(entity.getCarrierNum() + StrUtil.formatUnit(entity.getUnit()));
        mRatingBar.setRating(Float.parseFloat(entity.getStarRating()));
        mCreatTime.setText(entity.getLoadingTime());
        mEtLoadingWeight.setText(entity.getLoadingWeight());
        if (!TextUtils.isEmpty(entity.getLoadingPhoto())){
            GlideUtils.getInstance().load(mActivity,entity.getLoadingPhoto(),mIvList);
        }
        mUnloadingTime.setText(entity.getUnloadingTime());
        mEtLoadingWeight.setText(entity.getUnloadingWeight()+ StrUtil.formatUnit(entity.getUnit()));
        if (!TextUtils.isEmpty(entity.getUnloadingPhoto())){
            GlideUtils.getInstance().load(mActivity,entity.getUnloadingPhoto(),mUnloadingList);
        }

        if (type == 3) { // 待付款
            mTvPaymentName.setText("待付款");
            mPaymentMoney.setText(entity.getAllPrice());
        } else if (type == 4) {
            //代收款
            mTvPaymentName.setText("待收货款");
            mPaymentMoney.setText(entity.getAllPrice());
        } else if (type == 5) { //已完成
            mTvPaymentName.setText("实付款");
            mPaymentMoney.setText(entity.getAllPrice());

        }

    }

    /**
     * 请求数据失败
     *
     * @param message
     */
    @Override
    public void onFail(String message) {
        Toast.makeText(mActivity,message,Toast.LENGTH_SHORT).show();
    }

}
