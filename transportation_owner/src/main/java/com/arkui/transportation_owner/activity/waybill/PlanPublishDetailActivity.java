package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.mvp.ReleaseDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlanPublishDetailActivity extends BaseActivity implements ReleaseDetailInterface {

    @BindView(R.id.tv_loading_address)
    TextView mTvLoadingAddress;
    @BindView(R.id.tv_loading_detail_address)
    TextView mTvLoadingDetailAddress;
    @BindView(R.id.tv_unloading_address)
    TextView mTvUnloadingAddress;
    @BindView(R.id.tv_unloading_detail_address)
    TextView mTvUnloadingDetailAddress;
    @BindView(R.id.tv_cargo_name)
    TextView mTvCargoName;
    @BindView(R.id.tv_cargo_density)
    TextView mTvCargoDensity;
    @BindView(R.id.tv_freight_price)
    TextView mTvFreightPrice;
    @BindView(R.id.tv_cargo_price)
    TextView mTvCargoPrice;
    @BindView(R.id.tv_loading_time)
    TextView mTvLoadingTime;
    @BindView(R.id.tv_payment_terms)
    TextView mTvPaymentTerms;
    @BindView(R.id.tv_settlement_time)
    TextView mTvSettlementTime;
    @BindView(R.id.tv_press_charges)
    TextView mTvPressCharges;
    @BindView(R.id.tv_truck_drawer)
    TextView mTvTruckDrawer;
    @BindView(R.id.tv_truck_tel)
    TextView mTvTruckTel;
    @BindView(R.id.tv_unloading_contact)
    TextView mTvUnloadingContact;
    @BindView(R.id.tv_unloading_tel)
    TextView mTvUnloadingTel;
    @BindView(R.id.tv_remarks)
    TextView mTvRemarks;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_plan_publish_detail);
        setTitle("货源详情");
        setRightIcon(R.mipmap.hz_bianji);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        String id = getIntent().getStringExtra("id");
        ReleaseDetailPresenter releaseDetailPresenter = new ReleaseDetailPresenter(this, this);
        releaseDetailPresenter.getReleaseDetail(id);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(EditPlanPublishDetailActivity.class);
    }

    @Override
    public void onSuccess(ReleaseDetailsEntity releaseDetailsEntity) {

        String[] loadingAddress = releaseDetailsEntity.getLoadingAddress().split(" ");
        String[] unloadingAddress = releaseDetailsEntity.getUnloadingAddress().split(" ");

        mTvLoadingAddress.setText(loadingAddress.length>=0?loadingAddress[0]:"");
        mTvLoadingDetailAddress.setText(loadingAddress.length>=2?loadingAddress[1]:"");
        mTvUnloadingAddress.setText(unloadingAddress.length>=0?unloadingAddress[0]:"");
        mTvUnloadingDetailAddress.setText(unloadingAddress.length>=2?unloadingAddress[1]:"");

        mTvCargoName.setText(releaseDetailsEntity.getCargoName());
        mTvCargoDensity.setText(releaseDetailsEntity.getCargoDensity());
        mTvFreightPrice.setText(releaseDetailsEntity.getFreightPrice());
        mTvCargoPrice.setText(releaseDetailsEntity.getCargoDensity());
        mTvLoadingTime.setText(releaseDetailsEntity.getLoadingTime());
        mTvPressCharges.setText(releaseDetailsEntity.getPressCharges());

        mTvPaymentTerms.setText(StrUtil.formatPayMent(releaseDetailsEntity.getPaymentTerms()));
        mTvSettlementTime.setText(StrUtil.formatSettlementTime(releaseDetailsEntity.getSettlementTime()));

        mTvTruckDrawer.setText(releaseDetailsEntity.getTruckDrawer());
        mTvTruckTel.setText(releaseDetailsEntity.getTruckTel());
        mTvUnloadingContact.setText(releaseDetailsEntity.getUnloadingContact());
        mTvUnloadingTel.setText(releaseDetailsEntity.getUnloadingTel());
        mTvRemarks.setText(releaseDetailsEntity.getRemarks());
    }

    @Override
    public void onFail(String errorMessage) {

    }

    public static void showActivity(Context context, String cargo_id) {
        Intent intent = new Intent(context, PlanPublishDetailActivity.class);
        intent.putExtra("id", cargo_id);
        context.startActivity(intent);
    }

}
