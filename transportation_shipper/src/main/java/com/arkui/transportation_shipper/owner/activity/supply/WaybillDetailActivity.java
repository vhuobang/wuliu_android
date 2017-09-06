package com.arkui.transportation_shipper.owner.activity.supply;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.SupplyApi;
import com.arkui.transportation_shipper.common.entity.CargoListDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 从货源列表跳进来的货源详情
 */
public class WaybillDetailActivity extends BaseActivity implements OnConfirmClick {

    @BindView(R.id.loading_address)
    TextView mLoadingAddress;
    @BindView(R.id.loading_address_detail)
    TextView mLoadingAddressDetail;
    @BindView(R.id.unloading_address)
    TextView mUnloadingAddress;
    @BindView(R.id.unloading_address_detail)
    TextView mUnloadingAddressDetail;
    @BindView(R.id.cargo_name)
    TextView mCargoName;
    @BindView(R.id.cargo_density)
    TextView mCargoDensity;
    @BindView(R.id.freight_price)
    TextView mFreightPrice;
    @BindView(R.id.cargo_price)
    TextView mCargoPrice;
    @BindView(R.id.loading_time)
    TextView mLoadingTime;
    @BindView(R.id.payment_terms)
    TextView mPaymentTerms;
    @BindView(R.id.settlement_time)
    TextView mSettlementTime;
    @BindView(R.id.press_charges)
    TextView mPressCharges;
    @BindView(R.id.infomation_fee)
    TextView mInfomationFee;
    @BindView(R.id.remarks)
    TextView mRemarks;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.owner_name)
    TextView mOwnerName;
    @BindView(R.id.register_year)
    TextView mRegisterYear;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.phone)
    ImageView mPhone;
    @BindView(R.id.bt_start)
    ShapeButton mBtStart;
    private String id;
    private SupplyApi mSupplyApi;
    private CommonDialog commonDialog;
    private CargoListDetailEntity mCargoListDetailEntity;

    public static void openActivity(Context context, String id) {
        Intent intent = new Intent(context, WaybillDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_waybill_detail);
        setTitle("运单详情");

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mSupplyApi = RetrofitFactory.createRetrofit(SupplyApi.class);
        id = getIntent().getStringExtra("id");
        commonDialog = new CommonDialog();
        commonDialog.setConfirmText("打电话");
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmClick(this);

    }

    @Override
    public void initData() {
        Observable<CargoListDetailEntity> observable = mSupplyApi.getCargoListDetail(id).map(new HttpResultFunc<CargoListDetailEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<CargoListDetailEntity>(this) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(CargoListDetailEntity entity) {
                setUiFunction(entity);
            }

            @Override
            public void onApiError(ApiException e) {
                //    super.onApiError(e);
            }
        });
    }

    //设置ui
    private void setUiFunction(CargoListDetailEntity entity) {
        mCargoListDetailEntity = entity;
        String[] loadingAddress = entity.getLoadingAddress().split(" ");
        String[] unloadingAddress = entity.getUnloadingAddress().split(" ");
        mLoadingAddress.setText(loadingAddress[0]);
        mUnloadingAddress.setText(unloadingAddress[0]);
        mLoadingAddressDetail.setText(loadingAddress[1]);
        mUnloadingAddressDetail.setText(unloadingAddress[1]);
        mCargoName.setText(entity.getCargoName());
        mCargoDensity.setText(entity.getCargoDensity() + StrUtil.formatMoneyUnit(entity.getUnit()));
        mFreightPrice.setText(entity.getFreightPrice() + " 元");
        mCargoPrice.setText(entity.getCargoPrice() + "元");
        mLoadingTime.setText(entity.getLoadingTime());
        mPaymentTerms.setText(StrUtil.formatPayMent(entity.getPaymentTerms()));
        mSettlementTime.setText(StrUtil.formatSettlementTime(entity.getSettlementTime()));
        mPressCharges.setText(entity.getPressCharges() + "元");
        mInfomationFee.setText(entity.getInfomationFee() + " 元");
        mRemarks.setText(entity.getRemarks());
        GlideUtils.getInstance().loadRound(mActivity, entity.getAvatar(), mAvatar);
        mOwnerName.setText(entity.getName());
        mRegisterYear.setText("注册" + entity.getRegisterYear() + "年" + entity.getCargoNum() + "次");
        mRatingBar.setRating(Float.parseFloat(entity.getStarRating()));

    }


    @OnClick({R.id.bt_start, R.id.phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                //showActivity(ConfirmOrderActivity.class);
                Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.phone: // 拨打电话
                commonDialog.setContent(mCargoListDetailEntity.getMobile());
                commonDialog.showDialog(WaybillDetailActivity.this, "phone");
                break;
        }

    }


    @Override
    public void onConfirmClick() {
        String phoneNumber = commonDialog.getContent();
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
