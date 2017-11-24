package com.arkui.transportation.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.dialog.ViewVehicleLargeMapDialog;
import com.arkui.fz_tools.entity.EvaluateEvent;
import com.arkui.fz_tools.entity.WayBillDetailEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.WayBillDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WaybillDetailActivity extends BaseActivity implements WayBillDetialsInterface, OnConfirmClick {

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
    @BindView(R.id.tv_product_number)
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
    @BindView(R.id.phone)
    ImageView phone;

    @BindView(R.id.tv_driver_name)
    TextView mTvDriverName;
    @BindView(R.id.tv_product_phone)
    TextView mTvProductPhone;
    @BindView(R.id.tv_driver_id)
    TextView mTvDriverId;
    private String waybillId;
    private WayBillDetailPresenter wayBillDetailPresenter;
    private int type;

    private WayBillDetailEntity mWayBillDetailEntity;
    private CommonDialog commonDialog;
    private ViewVehicleLargeMapDialog viewVehicleLargeMapDialog;
    private boolean isMessage;
    @BindView(R.id.tv_hand_car)
    TextView handCar;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_waybill_detail);
        setTitle("运单详情");

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        wayBillDetailPresenter = new WayBillDetailPresenter(this, this);
        type = getIntent().getIntExtra("type", -1);
        waybillId = getIntent().getStringExtra("waybillId");
        isMessage = getIntent().getBooleanExtra("isMessage", false);
        if (type == 3) { // 待付款

            mTvDriverLocation.setVisibility(View.GONE);
            mTrWaitPay.setVisibility(View.VISIBLE);
            if (isMessage) {
                mTvPayFreight.setVisibility(View.GONE);
            } else {
                mTvPayFreight.setVisibility(View.VISIBLE);
            }
        } else if (type == 4) {
            //代收款
            mTvDriverLocation.setVisibility(View.GONE);
            mTrWaitPay.setVisibility(View.VISIBLE);
            mTvPaymentName.setText("待收运费");
        } else if (type == 5) { //已完成
            //  mTrWaitPay.setVisibility(View.VISIBLE);
            // mTvPaymentName.setText("实付款");
            mTvDriverLocation.setVisibility(View.GONE);
            mTvEvaluate.setVisibility(View.VISIBLE);
        }
        commonDialog = new CommonDialog();
        commonDialog.setConfirmText("打电话");
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmClick(this);

        viewVehicleLargeMapDialog = new ViewVehicleLargeMapDialog();
    }

    @Override
    public void initData() {
        wayBillDetailPresenter.getWayBillDetail(waybillId, App.getUserId());
    }

    @OnClick({R.id.tr_wait_pay, R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_driver_location, R.id.tv_pay_freight, R.id.tv_evaluate, R.id.phone
            , R.id.iv_list, R.id.unloading_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tr_wait_pay: // 需要付款
                break;
            case R.id.tv_owner_info:
                Intent intent = new Intent(mActivity, OwnerInfoActivity.class);
                boolean isMy = getIntent().getBooleanExtra("isMy", false);
                intent.putExtra("isMy", isMy);
                intent.putExtra("cargoId", mWayBillDetailEntity.getCargoId());
                intent.putExtra("ownerId", mWayBillDetailEntity.getOwnerCargoId());
                startActivity(intent);
                break;
            case R.id.tv_cargo_info: // 货物信息
                CargoInfoActivity.openActivity(mActivity, mWayBillDetailEntity.getCargoId(),type);

                break;
            case R.id.tv_driver_location: //司机位置
                //showActivity(DriverLocationActivity.class);
                if (TextUtils.isEmpty(mWayBillDetailEntity.getLat())) {
                    Toast.makeText(mActivity, "司机未上传地址", Toast.LENGTH_SHORT).show();
                } else {
                    DriverLocationActivity.openActivity(WaybillDetailActivity.this, mWayBillDetailEntity.getLog(),
                            mWayBillDetailEntity.getLat());
                }

                break;
            case R.id.tv_pay_freight: //支付运费
                PaymentFreightActivity.openActivity(mActivity, waybillId, mWayBillDetailEntity.getOrderNumber());
                break;
            case R.id.tv_evaluate: // 评价界面
                Bundle bundle = new Bundle();
                bundle.putString("wayBillId", waybillId);
                showActivity(PublishEvaluateActivity.class, bundle);
                break;
            case R.id.phone:
                commonDialog.setContent(mWayBillDetailEntity.getTel());
                commonDialog.showDialog(WaybillDetailActivity.this, "phone");
                break;
            case R.id.unloading_list:
                // 卸货磅单
                String unloadingPhoto = mWayBillDetailEntity.getUnloadingPhoto();
                if (unloadingPhoto != null) {
                    viewVehicleLargeMapDialog.setImgUrl(unloadingPhoto).showDialog(WaybillDetailActivity.this, "photo");
                } else {
                    Toast.makeText(mActivity, "未上传", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_list:
                // 装货磅单
                String loadingPhoto = mWayBillDetailEntity.getLoadingPhoto();
                if (loadingPhoto != null) {
                    viewVehicleLargeMapDialog.setImgUrl(loadingPhoto).showDialog(WaybillDetailActivity.this, "photo");
                } else {
                    Toast.makeText(mActivity, "未上传", Toast.LENGTH_SHORT).show();
                }
                break;
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
        mWayBillDetailEntity = entity;
        mWaybillId.setText("订单号:" + entity.getOrderNumber());
        mTvCarOwnerName.setText(entity.getOwnerName());
        mTvCarOwnerPhone.setText(entity.getTel());
        mTvCarId.setText(entity.getLicensePlate());
        mTvProductNumber.setText(entity.getCarrierNum() + StrUtil.formatUnit(entity.getUnit()));
        mRatingBar.setRating(Float.parseFloat(entity.getStarRating()));
        mCreatTime.setText(entity.getLoadingTime());
        mTvDriverId .setText(entity.getCar_id_card());
        mTvProductPhone.setText(entity.getCar_tel());
        mTvDriverName.setText(entity.getCar_name());
        if (entity.getLoadingWeight() == null) {
            mEtLoadingWeight.setText("未填写");
        } else {
            mEtLoadingWeight.setText(entity.getLoadingWeight() + StrUtil.formatUnit(entity.getUnit()));
        }
        handCar.setText(TextUtils.isEmpty(entity.getHandCar())?"无":entity.getHandCar());

        if (!TextUtils.isEmpty(entity.getLoadingPhoto())) {
            GlideUtils.getInstance().load(mActivity, entity.getLoadingPhoto(), mIvList);
        }
        mUnloadingTime.setText(entity.getUnloadingTime());
        if (entity.getUnloadingWeight() == null) {
            mUnloadingWeight.setText("未填写");
        } else {
            mUnloadingWeight.setText(entity.getUnloadingWeight() + StrUtil.formatUnit(entity.getUnit()));
        }


        if (!TextUtils.isEmpty(entity.getUnloadingPhoto())) {
            GlideUtils.getInstance().load(mActivity, entity.getUnloadingPhoto(), mUnloadingList);
        }

        if (type == 3) { // 待付款
            mTvPaymentName.setText("待付款");
            mPaymentMoney.setText(entity.getAllPrice());
        } else if (type == 4) {
            //代收款
            mTvPaymentName.setText("待收运费");
            mPaymentMoney.setText(entity.getAllPrice());
        } else if (type == 5) { //已完成

            if (entity.getPaymentTerms().equals("3")) {
                mTrWaitPay.setVisibility(View.GONE);
            } else {
                mTrWaitPay.setVisibility(View.VISIBLE);
                mTvPaymentName.setText("实付款");
                mPaymentMoney.setText(entity.getOrderMoney());
            }

            if (entity.getLog_evaluate_status().equals("1")) {
                mTvEvaluate.setText("已评价");
                mTvEvaluate.setEnabled(false);
            }

        }
    }

    /**
     * 请求数据失败
     *
     * @param message
     */
    @Override
    public void onFail(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfirmClick() {
        String phoneNumber = commonDialog.getContent();
        if (!TextUtils.isEmpty(phoneNumber)) {
            Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void evaluateSuccess(EvaluateEvent event) {
        mTvEvaluate.setText("已评价");
        mTvEvaluate.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
