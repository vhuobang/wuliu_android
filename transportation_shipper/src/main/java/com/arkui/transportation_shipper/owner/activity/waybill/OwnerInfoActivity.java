package com.arkui.transportation_shipper.owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_tools._interface.CargoOwnerInfoInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.CargoOwnerInfoEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.CargoOwnerInfoPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OwnerInfoActivity extends BaseActivity implements CargoOwnerInfoInterface, OnConfirmClick {

    @BindView(R.id.owner_name)
    TextView mOwnerName;
    @BindView(R.id.owner_phone)
    TextView mOwnerPhone;
    @BindView(R.id.iv_owner_phone)
    ImageView mIvOwnerPhone;
    @BindView(R.id.register_years)
    TextView mRegisterYears;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.truck_drawer)
    TextView mTruckDrawer;
    @BindView(R.id.truck_tel)
    TextView mTruckTel;
    @BindView(R.id.iv_connect_phone)
    ImageView mIvConnectPhone;
    @BindView(R.id.unloading_name)
    TextView mUnloadingName;
    @BindView(R.id.unloading_phone)
    TextView mUnloadingPhone;
    @BindView(R.id.iv_unloading_phone)
    ImageView mIvUnloadingPhone;
    private String ownerId;
    private String cargoId;
    private CargoOwnerInfoPresenter cargoOwnerInfoPresenter;
    private CommonDialog commonDialog;
    private CargoOwnerInfoEntity cargoOwnerInfoEntity;

    public static void openActivity(Context context, String ownerId, String cargoId) {
        Intent intent = new Intent(context, OwnerInfoActivity.class);
        intent.putExtra("ownerId", ownerId); //货主id
        intent.putExtra("cargoId", cargoId);// 货源id
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_owner_info);
        setTitle("货主信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        commonDialog = new CommonDialog();
        commonDialog.setConfirmClick(this);
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmText("打电话");
        ownerId = getIntent().getStringExtra("ownerId");
        cargoId = getIntent().getStringExtra("cargoId");
        cargoOwnerInfoPresenter = new CargoOwnerInfoPresenter(this, this);
    }

    @Override
    public void initData() {

        cargoOwnerInfoPresenter.getCargoOwnerInfo(cargoId, ownerId);
    }

    @Override
    public void onSuccess(CargoOwnerInfoEntity entity) {
        cargoOwnerInfoEntity = entity;
         mOwnerName.setText(entity.getName());
        mOwnerPhone.setText(entity.getTel());
        mRegisterYears.setText(entity.getRegisterYear());
        mTvNumber.setText(entity.getSendNum());
        mRatingBar.setRating(Float.parseFloat(entity.getStarRating()));
        mTruckDrawer.setText(entity.getTruckDrawer());
        mTruckTel.setText(entity.getTruckTel());
        mUnloadingName.setText(entity.getUnloadingContact());
        mUnloadingPhone.setText(entity.getUnloadingTel());
    }

    @Override
    public void onFail(String errorMessage) {

    }


    @OnClick({R.id.iv_owner_phone, R.id.iv_connect_phone, R.id.iv_unloading_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_owner_phone:  // 货主电话
                   commonDialog.setContent(cargoOwnerInfoEntity.getTel());
                   commonDialog.show(getSupportFragmentManager(),"phone");
                break;
            case R.id.iv_connect_phone:// 装车开票人电话
                commonDialog.setContent(cargoOwnerInfoEntity.getTruckTel());
                commonDialog.show(getSupportFragmentManager(),"phone");
                break;
            case R.id.iv_unloading_phone: // 卸货联系人电话
                commonDialog.setContent(cargoOwnerInfoEntity.getUnloadingTel());
                commonDialog.show(getSupportFragmentManager(),"phone");
                break;
        }
    }
  // 打电话
    @Override
    public void onConfirmClick() {
        String phoneNumber = commonDialog.getContent();
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
