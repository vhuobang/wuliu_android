package com.arkui.transportation.activity.waybill;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.mvp.ReleaseDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.publish.PublishCompleteInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.arkui.transportation.R.id.cargo_density;
import static com.arkui.transportation.R.id.freight_price;
import static com.arkui.transportation.R.id.loading_time;
import static com.arkui.transportation.R.id.payment_terms;
import static com.arkui.transportation.R.id.settlement_time;
import static com.arkui.transportation.R.id.truck_drawer;
import static com.arkui.transportation.R.id.truck_tel;
import static com.arkui.transportation.R.id.unloading_contact;
import static com.arkui.transportation.R.id.unloading_tel;


public class PlanPublishDetailActivity extends BaseActivity implements ReleaseDetailInterface {

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
    @BindView(cargo_density)
    TextView cargoDensity;
    @BindView(freight_price)
    TextView freightPrice;
    @BindView(R.id.cargo_price)
    TextView cargoPrice;
    @BindView(loading_time)
    TextView loadingTime;
    @BindView(payment_terms)
    TextView paymentTerms;
    @BindView(settlement_time)
    TextView settlementTime;
    @BindView(R.id.press_charges)
    TextView pressCharges;
    @BindView(truck_drawer)
    TextView truckDrawer;
    @BindView(truck_tel)
    TextView truckTel;
    @BindView(unloading_contact)
    TextView unloadingContact;
    @BindView(unloading_tel)
    TextView unloadingTel;
    @BindView(R.id.remarks)
    TextView remarks;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    private ReleaseDetailPresenter releaseDetailPresenter;
    private String carGoId;
    private  ReleaseDetailsEntity releaseDetailsEntity;

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
        carGoId = getIntent().getStringExtra("carGoId");
        releaseDetailPresenter = new ReleaseDetailPresenter(this, this);
    }

    /**
     * 获取焦点 请求数据
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        if (carGoId != null) {
            releaseDetailPresenter.getReleaseDetail(carGoId);
        }
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(EditPlanPublishDetailActivity.class);
    }
    //立即发布
    @OnClick(R.id.tv_publish)
    public void onViewClicked() {
        if (releaseDetailsEntity!=null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("releaseDetails",releaseDetailsEntity);
            showActivity(PublishCompleteInfoActivity.class,bundle);
        }

    }

    //数据请求成功的回调
    @Override
    public void onSuccess(ReleaseDetailsEntity entity) {
        releaseDetailsEntity = entity;
        tvLoadingAddress.setText(entity.getLoadingAddress());
        tvUnloadingAddress.setText(entity.getUnloadingAddress());
        String unit = StrUtil.formatUnit(entity.getUnit());
        tvGoodInfo.setText(entity.getCargoName() + "/" + entity.getCargoNum() + unit);
        cargoDensity.setText(entity.getCargoDensity());
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
        Toast.makeText(PlanPublishDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
