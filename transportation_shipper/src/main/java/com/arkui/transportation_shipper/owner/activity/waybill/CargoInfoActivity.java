package com.arkui.transportation_shipper.owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.mvp.ReleaseDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 货物详情信息
 */
public class CargoInfoActivity extends BaseActivity implements ReleaseDetailInterface {

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
    private String cargoId;
    private ReleaseDetailPresenter releaseDetailPresenter;

    public  static  void openActivity(Context context , String cargo_id){
        Intent intent = new Intent(context,CargoInfoActivity.class);
        intent.putExtra("cargoId",cargo_id);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_cargo_info);
        setTitle("货物信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        cargoId = getIntent().getStringExtra("cargoId");
        releaseDetailPresenter = new ReleaseDetailPresenter(this, this);
    }

    // 货物详情
    @Override
    public void onSuccess(ReleaseDetailsEntity entity) {
        String[] loadingAddress = entity.getLoadingAddress().split(" ");
        String[] unloadingAddress = entity.getUnloadingAddress().split(" ");

        tvLoadingAddress.setText(loadingAddress.length >= 0 ? loadingAddress[0] : "");
        tvLoadingDetailAddress.setText(loadingAddress.length >= 2 ? loadingAddress[1] : "");
        tvUnloadingAddress.setText(unloadingAddress.length >= 0 ? unloadingAddress[0] : "");
        tvUnloadingDetailAddress.setText(unloadingAddress.length >= 2 ? unloadingAddress[1] : "");


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

    }
}
