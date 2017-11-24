package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.ReleaseDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CargoInfoActivity extends BaseActivity implements ReleaseDetailInterface, OnConfirmClick {


    @BindView(R.id.tv_loading_address)
    TextView tvLoadingAddress;
    @BindView(R.id.tv_loading_detail_address)
    TextView tvLoadingDetailAddress;
    @BindView(R.id.tv_unloading_address)
    TextView tvUnloadingAddress;
    @BindView(R.id.tv_unloading_detail_address)
    TextView tvUnloadingDetailAddress;
    @BindView(R.id.tv_good_info)
    TextView tvGoodInfo;
    @BindView(R.id.cargo_density)
    TextView cargoDensity;
    @BindView(R.id.freight_price)
    TextView freightPrice;
    @BindView(R.id.cargo_price)
    TextView cargoPrice;
    @BindView(R.id.loading_time)
    TextView loadingTime;
    @BindView(R.id.payment_terms)
    TextView paymentTerms;
    @BindView(R.id.settlement_time)
    TextView settlementTime;
    @BindView(R.id.press_charges)
    TextView pressCharges;
    @BindView(R.id.truck_drawer)
    TextView truckDrawer;
    @BindView(R.id.truck_tel)
    TextView truckTel;
    @BindView(R.id.unloading_contact)
    TextView unloadingContact;
    @BindView(R.id.unloading_tel)
    TextView unloadingTel;
    @BindView(R.id.remarks)
    TextView remarks;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.truck_phone)
    ImageView mTruckPhone;
    @BindView(R.id.unloading_phone)
    ImageView mUnloadingPhone;

    private String cargoId;
    private ReleaseDetailPresenter releaseDetailPresenter;
    CommonDialog commonDialog;
    private ReleaseDetailsEntity releaseDetailsEntity;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_cargo_info);
        setTitle("货物信息");
    }

    public static void openActivity(Context context, String cargoId) {
        Intent intent = new Intent(context, CargoInfoActivity.class);
        intent.putExtra("cargoId", cargoId);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        cargoId = getIntent().getStringExtra("cargoId");
        releaseDetailPresenter = new ReleaseDetailPresenter(this, this);
        commonDialog = new CommonDialog();
        commonDialog.setConfirmClick(this);
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmText("打电话");
    }

    @Override
    public void initData() {
        releaseDetailPresenter.getReleaseDetail(cargoId);
    }

    @Override
    public void onSuccess(ReleaseDetailsEntity entity) {
        releaseDetailsEntity = entity;
        tvLoadingAddress.setText(entity.getLoadingAddress());
        tvUnloadingAddress.setText(entity.getUnloadingAddress());
        String unit = StrUtil.formatUnit(entity.getUnit());
        tvGoodInfo.setText(entity.getCargoName() + "/" + entity.getCargoNum() + unit);
        cargoDensity.setText(entity.getCargoDensity() + "方/吨");
        freightPrice.setText(entity.getFreightPrice());
        cargoPrice.setText(entity.getCargoPrice());
        loadingTime.setText(entity.getLoadingTime());
        paymentTerms.setText(StrUtil.formatPayMent(entity.getPaymentTerms()));
        settlementTime.setText(StrUtil.formatSettlementTime(entity.getSettlementTime()));
        pressCharges.setText(entity.getPressCharges());
        truckDrawer.setText(entity.getTruckDrawer());
        truckTel.setText(entity.getTruckTel());
        unloadingContact.setText(entity.getUnloadingContact());
        unloadingTel.setText(entity.getUnloadingTel());
        remarks.setText(entity.getRemarks());
    }

    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.truck_phone, R.id.unloading_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.truck_phone:
                commonDialog.setContent(releaseDetailsEntity.getTruckTel());
                commonDialog.showDialog(CargoInfoActivity.this,"phone");
                break;
            case R.id.unloading_phone:
                commonDialog.setContent(releaseDetailsEntity.getUnloadingTel());
                commonDialog.showDialog(CargoInfoActivity.this,"phone");
                break;
        }
    }

    @Override
    public void onConfirmClick() {
        String phoneNumber = commonDialog.getContent();
        if (!TextUtils.isEmpty(phoneNumber)){
            Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
    }
}
