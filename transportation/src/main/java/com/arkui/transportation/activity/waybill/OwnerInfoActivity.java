package com.arkui.transportation.activity.waybill;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkui.fz_tools._interface.CargoOwnerInfoInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.CargoOwnerInfoEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.CargoOwnerInfoPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
 /*
   货主信息
  */

public class OwnerInfoActivity extends BaseActivity implements CargoOwnerInfoInterface, OnConfirmClick {

    @BindView(R.id.tl_owner_phone)
    TableRow mTlOwnerPhone;
    @BindView(R.id.tl_owner_time)
    TableRow mTlOwnerTime;
    @BindView(R.id.tl_owner_number)
    TableRow mTlOwnerNumber;
    @BindView(R.id.tl_ratingBar)
    TableRow mTlRatingBar;
    @BindView(R.id.tv_cargo_host)
    TextView mTvCargoHost;
    @BindView(R.id.cargo_phone)
    TextView mCargoPhone;
    @BindView(R.id.cargo_owner_phone_btn)
    ImageView mCargoOwnerPhoneBtn;
    @BindView(R.id.register_year)
    TextView mRegisterYear;
    @BindView(R.id.cargo_number)
    TextView mCargoNumber;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.loading_person_name)
    TextView mLoadingPersonName;
    @BindView(R.id.loading_phone)
    TextView mLoadingPhone;
    @BindView(R.id.loading_phone_btn)
    ImageView mLoadingPhoneBtn;
    @BindView(R.id.unloading_person_name)
    TextView mUnloadingPersonName;
    @BindView(R.id.unloading_phone)
    TextView mUnloadingPhone;
    @BindView(R.id.unloading_phone_btn)
    ImageView mUnloadingPhoneBtn;
    private String cargoId;
    private String ownerId;
    private boolean isMy;
    private CargoOwnerInfoPresenter cargoOwnerInfoPresenter;
    CommonDialog commonDialog;
    private CargoOwnerInfoEntity cargoOwnerInfoEntity;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_owner_info);
        setTitle("货主信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        isMy = getIntent().getBooleanExtra("isMy", false);
        cargoId = getIntent().getStringExtra("cargoId");
        ownerId = getIntent().getStringExtra("ownerId");
        cargoOwnerInfoPresenter = new CargoOwnerInfoPresenter(this, this);
        if (isMy) {
            mTlOwnerTime.setVisibility(View.GONE);
            mTlOwnerNumber.setVisibility(View.GONE);
            mTlRatingBar.setVisibility(View.GONE);
        }
        commonDialog = new CommonDialog();
        commonDialog.setConfirmText("打电话");
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmClick(this);
    }

    @Override
    public void initData() {
        cargoOwnerInfoPresenter.getCargoOwnerInfo(cargoId, ownerId);
    }

    @Override
    public void onSuccess(CargoOwnerInfoEntity entity) {
        cargoOwnerInfoEntity = entity;

        mTvCargoHost.setText(entity.getName());
        mCargoPhone.setText(entity.getTel());
        mRegisterYear.setText(entity.getRegisterYear());
        mCargoNumber.setText(entity.getSendNum());
        mRatingBar.setRating(Float.parseFloat(entity.getStarRating()));
        mLoadingPersonName.setText(entity.getTruckDrawer());
        mLoadingPhone.setText(entity.getTruckTel());
        mUnloadingPersonName.setText(entity.getUnloadingContact());
        mUnloadingPhone.setText(entity.getUnloadingTel());
    }

    @Override
    public void onFail(String errorMessage) {

    }


   // 打電話
    @OnClick({R.id.cargo_owner_phone_btn, R.id.loading_phone_btn, R.id.unloading_phone_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cargo_owner_phone_btn:
                commonDialog.setContent(cargoOwnerInfoEntity.getTel());
                commonDialog.showDialog(OwnerInfoActivity.this, "phone");
                break;
            case R.id.loading_phone_btn:
                commonDialog.setContent(cargoOwnerInfoEntity.getTruckTel());
                commonDialog.showDialog(OwnerInfoActivity.this, "phone");
                break;
            case R.id.unloading_phone_btn:
                commonDialog.setContent(cargoOwnerInfoEntity.getUnloadingTel());
                commonDialog.showDialog(OwnerInfoActivity.this, "phone");
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
