package com.arkui.transportation_shipper.owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.entity.LogisticalListEntity;
import com.arkui.transportation_shipper.owner.presenter.LogisticsPresenter;
import com.arkui.transportation_shipper.owner.view.LogisticsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * // TODO  添加联系人姓名 和联系电话
 */

public class LogisticsInfoActivity extends BaseActivity implements LogisticsView, OnConfirmClick {

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
    private String logid;
    private LogisticsPresenter logisticsPresenter;
    private CommonDialog commonDialog;
    private LogisticalListEntity entity;
    private String waybill_id;

    public static void openActivity(Context context, String log_id,String waybill_id) {
        Intent intent = new Intent(context, LogisticsInfoActivity.class);
        intent.putExtra("logid", log_id);
        intent.putExtra("waybill_id",waybill_id);
        context.startActivity(intent);
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
        logid = getIntent().getStringExtra("logid"); // 物流id
        waybill_id = getIntent().getStringExtra("waybill_id");
        logisticsPresenter = new LogisticsPresenter(this, this);
        commonDialog = new CommonDialog();
        commonDialog.setConfirmText("打电话");
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmClick(this);

    }

    @Override
    public void initData() {
        logisticsPresenter.postLogisticsDetail(logid,waybill_id);
    }

    @Override
    public void onSuccess(LogisticalListEntity logisticalDetails) {
        entity = logisticalDetails;
        mTvCompanyName.setText(logisticalDetails.getName());
        mTvCompanyAddress.setText(logisticalDetails.getAddress());
        mTvCompanyPhone.setText(logisticalDetails.getTel());
        mTvName.setText(logisticalDetails.getHandlerName());
        mTvRegisterYear.setText(logisticalDetails.getRegisterYear());
        mDealNumber.setText(logisticalDetails.getVolume());
            mConnectName.setText(logisticalDetails.getLogContactName());
        mTvConnectPhone.setText(logisticalDetails.getLogContactTel());
        mRatingBar.setRating(Float.parseFloat(logisticalDetails.getStarRating()));
    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logisticsPresenter.onDestroy();
    }
   // TODO 拨打电话
    @OnClick({R.id.iv_company_phone, R.id.iv_connect_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_company_phone:
                commonDialog.setContent(entity.getTel());
                commonDialog.showDialog(LogisticsInfoActivity.this, "phone");
                break;
            case R.id.iv_connect_phone:
                commonDialog.setContent(entity.getLogContactTel());
                commonDialog.showDialog(LogisticsInfoActivity.this, "phone");
                break;
        }
    }

    // 确定拨打
    @Override
    public void onConfirmClick() {
        String phoneNumber = commonDialog.getContent();
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
