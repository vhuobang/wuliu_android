package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.view.LogisticsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
// 物流详情

public class LogisticsInfoActivity extends BaseActivity implements LogisticsView, OnConfirmClick {

    LogisticsPresenter mLogisticsPresenter;
    @BindView(R.id.tv_company_name)
    TextView mTvCompanyName;
    @BindView(R.id.tv_company_address)
    TextView mTvCompanyAddress;
    @BindView(R.id.tv_company_phone)
    TextView mTvCompanyPhone;
    @BindView(R.id.iv_company_phone)
    ImageView mIvCompanyPhone;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_register_year)
    TextView mTvRegisterYear;
    @BindView(R.id.deal_number)
    TextView mDealNumber;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.connect_name)
    TextView mConnectName;
    @BindView(R.id.tv_connect_phone)
    TextView mTvConnectPhone;
    @BindView(R.id.iv_connect_phone)
    ImageView mIvConnectPhone;
    private String logisticId;
    CommonDialog commonDialog;
    private LogisticalListEntity logisticalListEntity;
    private String wayBillId;

    public static void openActivity(Context mContext, String logisticId,String wayBillId) {
        Intent intent = new Intent(mContext, LogisticsInfoActivity.class);
        intent.putExtra("logisticId", logisticId);
        intent.putExtra("wayBillId",wayBillId);
        mContext.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_logistics_info);
        setTitle("物流信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        logisticId = getIntent().getStringExtra("logisticId");
        wayBillId = getIntent().getStringExtra("wayBillId");
        commonDialog = new CommonDialog();
        commonDialog.setConfirmClick(this);
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmText("打电话");
    }

    @Override
    public void initData() {
        mLogisticsPresenter = new LogisticsPresenter(this, this);
        mLogisticsPresenter.postLogisticsDetail(logisticId,wayBillId);
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {

    }

    @Override
    public void onError() {

    }


    // 返回物流详情
    @Override
    public void onSucceed(LogisticalListEntity logisticalDetails) {

        logisticalListEntity = logisticalDetails;
        mTvCompanyName.setText(logisticalDetails.getName());
        mTvCompanyAddress.setText(logisticalDetails.getAddress());
        mTvCompanyPhone.setText(logisticalDetails.getTel());
        mTvName.setText(logisticalDetails.getHandlerName());
        mTvRegisterYear.setText(logisticalDetails.getRegisterYear());
        mDealNumber.setText(logisticalDetails.getVolume());
        mRatingBar.setRating(Float.parseFloat(logisticalDetails.getStarRating()));
        mConnectName.setText(logisticalDetails.getLogContactName());
        mTvConnectPhone.setText(logisticalDetails.getLogContactTel());
    }

    @Override
    public void onSucceed(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLogisticsPresenter != null) {
            mLogisticsPresenter.onDestroy();
        }

    }

    @OnClick({R.id.iv_company_phone, R.id.iv_connect_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_company_phone:
                commonDialog.setContent(logisticalListEntity.getTel());
                commonDialog.showDialog(LogisticsInfoActivity.this,"phone");
                break;
            case R.id.iv_connect_phone:
                commonDialog.setContent(logisticalListEntity.getLogContactTel());
                commonDialog.showDialog(LogisticsInfoActivity.this,"phone");
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
