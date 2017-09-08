package com.arkui.transportation_shipper.driver.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.DriverApi;
import com.arkui.transportation_shipper.common.entity.DriverOrderDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.arkui.transportation_shipper.R.id.remarks;
import static com.arkui.transportation_shipper.R.id.tv_unloading_person;
import static com.arkui.transportation_shipper.R.id.tv_unloading_phone;


public class DriverWaybillDetailActivity extends BaseActivity implements OnConfirmClick {

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.loading_address)
    TextView mLoadingAddress;
    @BindView(R.id.loading_address_detail)
    TextView mLoadingAddressDetail;
    @BindView(R.id.unloading_address)
    TextView mUnloadingAddress;
    @BindView(R.id.unloading_address_detail)
    TextView mUnloadingAddressDetail;
    @BindView(R.id.tv_license_plate)
    TextView mTvLicensePlate;
    @BindView(R.id.pro_number)
    TextView mProNumber;
    @BindView(R.id.cargo_info)
    TextView mCargoInfo;
    @BindView(R.id.cargo_density)
    TextView mCargoDensity;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_cargo_person)
    TextView mTvCargoPerson;
    @BindView(R.id.cargo_phone)
    TextView mCargoPhone;
    @BindView(R.id.iv_cargo_phone)
    ImageView mIvCargoPhone;
    @BindView(tv_unloading_person)
    TextView mTvUnloadingPerson;
    @BindView(tv_unloading_phone)
    TextView mTvUnloadingPhone;
    @BindView(R.id.iv_unloading_phone)
    ImageView mIvUnloadingPhone;
    @BindView(remarks)
    TextView mRemarks;
    private int mType;
    private DriverApi driverApi;
    private String orderId;
    private CommonDialog commonDialog;
    private DriverOrderDetailEntity mDriverOrderDetailEntity;

    public static void openActivity(Context context, int type, String orderId) {
        Intent intent = new Intent(context, DriverWaybillDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_waybill_detail);
        //setTitle("DriverWaybillDetailActivity");
    }


    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra("type", 0);
        orderId = getIntent().getStringExtra("orderId");
        commonDialog = new CommonDialog();
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmClick(this);
        commonDialog.setConfirmText("打电话");


        switch (mType) {
            case 1:
                setTitle("待装货详情");
                break;
            case 2:
                setTitle("运输中详情");
                setRight("查看磅单");
                mTvSubmit.setText("提交卸货磅单");
                break;
            case 3:
                setTitle("已卸货详情");
                setRight("查看磅单");
                mTvSubmit.setVisibility(View.GONE);
                break;
        }

        driverApi = RetrofitFactory.createRetrofit(DriverApi.class);

    }

    @Override
    public void initData() {
        Observable<DriverOrderDetailEntity> observable = driverApi.getCargoListDetail(orderId).map(new HttpResultFunc<DriverOrderDetailEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<DriverOrderDetailEntity>(this) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            // 请求数据成功
            @Override
            public void onNext(DriverOrderDetailEntity value) {
                setUiFunction(value);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
            }
        });
    }
    //展示数据
    private void setUiFunction(DriverOrderDetailEntity value) {
        mDriverOrderDetailEntity = value;
        String unit = StrUtil.formatUnit(value.getUnit());
        String [] loadingAddress = value.getLoadingAddress().split(" ");
        String [] unloadingAddress = value.getUnloadingAddress().split(" ");
        mLoadingAddress.setText(loadingAddress[0]);
        mLoadingAddressDetail.setText(loadingAddress[1]);
        mUnloadingAddress.setText(unloadingAddress[0]);
        mLoadingAddressDetail.setText(unloadingAddress[1]);
        mTvLicensePlate.setText(value.getLicensePlate());
        mProNumber.setText(value.getCarrierNum()+unit);
        mCargoInfo.setText(value.getCargoName() +" "+ value.getCargoNum()+StrUtil.formatUnit(unit) );
        mCargoDensity.setText(value.getCargoDensity() + "吨/方");
        mTvTime.setText(value.getLoadingTime());
        mTvCargoPerson.setText(value.getTruckDrawer());
        mCargoPhone.setText(value.getTruckTel());
        mTvUnloadingPerson.setText(value.getUnloadingContact());
        mTvUnloadingPhone.setText(value.getUnloadingTel());
        mRemarks.setText(value.getRemarks());

    }

    @OnClick(R.id.tv_submit)
    public void onClick() {
        if (mType == 1) {
            LoadingBillActivity.openActivity(DriverWaybillDetailActivity.this,orderId);
            //showActivity(LoadingBillActivity.class);
        } else {
            UnloadBillActivity.openActivity(DriverWaybillDetailActivity.this,orderId);
        }

    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        DriverPoundBillDetailActivity.openActivity(DriverWaybillDetailActivity.this,orderId);
    }


    @OnClick({R.id.iv_cargo_phone, R.id.iv_unloading_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cargo_phone:
                commonDialog.setContent(mDriverOrderDetailEntity.getTruckTel());
                commonDialog.showDialog(DriverWaybillDetailActivity.this,"phone");
                break;
            case R.id.iv_unloading_phone:
                commonDialog.setContent(mDriverOrderDetailEntity.getUnloadingTel());
                commonDialog.showDialog(DriverWaybillDetailActivity.this,"phone");
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
