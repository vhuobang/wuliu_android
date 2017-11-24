package com.arkui.transportation_shipper.owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.EvaluateEvent;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.SupplyApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.OrderEntity;
import com.arkui.transportation_shipper.common.entity.RefreshSupplyListEntity;
import com.arkui.transportation_shipper.driver.activity.waybill.LoadingBillActivity;
import com.arkui.transportation_shipper.driver.activity.waybill.UnloadBillActivity;
import com.arkui.transportation_shipper.owner.activity.supply.PayMessageFeeActivity;
import com.arkui.transportation_shipper.owner.api.LogisticalApi;
import com.arkui.transportation_shipper.owner.dialog.ViewVehicleLargeMapDialog;
import com.arkui.transportation_shipper.owner.entity.TruckOwnerWaybillDetialEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 车主端运单详情界面
 */

public class WaybillListDetailActivity extends BaseActivity implements OnConfirmClick {

    @BindView(R.id.tv_owner_info)
    TextView mTvOwnerInfo;
    @BindView(R.id.tv_cargo_info)
    TextView mTvCargoInfo;
    @BindView(R.id.tv_logistics_info)
    TextView mTvLogisticsInfo;
    @BindView(R.id.tv_driver_location)
    TextView mTvDriverLocation;
    @BindView(R.id.tr_wait_pay)
    TableRow mTrWaitPay;
    @BindView(R.id.total_money)
    TextView totalMoney;
    @BindView(R.id.tv_uploading)
    TextView mTvUploading;
    @BindView(R.id.order_number)
    TextView mOrderNumber;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.driver_name)
    TextView mDriverName;
    @BindView(R.id.driver_phone)
    TextView mDriverPhone;
    @BindView(R.id.iv_phone)
    ImageView mIvPhone;
    @BindView(R.id.license_plate)
    TextView mLicensePlate;
    @BindView(R.id.carry_number)
    TextView mCarryNumber;
    @BindView(R.id.loading_time)
    EditText mLoadingTime;
    @BindView(R.id.loading_weight)
    EditText mLoadingWeight;
    @BindView(R.id.loading_pic)
    ImageView mLoadingPic;
    @BindView(R.id.unloading_time)
    EditText mUnloadingTime;
    @BindView(R.id.unloading_weight)
    EditText mUnloadingWeight;
    @BindView(R.id.unloading_pic)
    ImageView mUnloadingPic;
    @BindView(R.id.tv_unpay)
    TextView tvUnpay;
    @BindView(R.id.ll_payed)
    LinearLayout llPayed;

    @BindView(R.id.wait_pay)
    TextView waitPay;

    private String mType;
    private String waybill_id;
    private LogisticalApi logisticalApi;
    private TruckOwnerWaybillDetialEntity mTruckOwnerWaybillDetialEntity;
    private TruckOwnerWaybillDetialEntity truckOwnerWaybillDetialEntity;
    private CommonDialog commonDialog;
    private ViewVehicleLargeMapDialog viewVehicleLargeMapDialog;
    SupplyApi mSupplyApi;
    OrderEntity mOrderEntity;

    public static void openActivity(Context mContext, String type, String waybillId) {
        Intent intent = new Intent(mContext, WaybillListDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("waybill_id", waybillId);
        mContext.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_waybill_list_detail);
        setTitle("运单详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mType = getIntent().getStringExtra("type");
        waybill_id = getIntent().getStringExtra("waybill_id");
        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        mSupplyApi = RetrofitFactory.createRetrofit(SupplyApi.class);
        switch (mType) {
            case "4":
                mTvDriverLocation.setVisibility(View.GONE);
                mTrWaitPay.setVisibility(View.VISIBLE);
                mTvUploading.setVisibility(View.GONE);
                break;
            case "5":
                mTvDriverLocation.setText("评价");
                mTrWaitPay.setVisibility(View.VISIBLE);
                waitPay.setText("实收款：");
                mTvUploading.setVisibility(View.GONE);
                break;
        }
        commonDialog = new CommonDialog();
        commonDialog.setConfirmClick(this);
        commonDialog.setConfirmText("打电话");
        commonDialog.setTitle("拨打电话");

        viewVehicleLargeMapDialog = new ViewVehicleLargeMapDialog();
        isPayFee();
    }

    @Override
    public void initData() {

        Observable<TruckOwnerWaybillDetialEntity> observable = logisticalApi.getTruckOwnerWaybillDetails(waybill_id).
                map(new HttpResultFunc<TruckOwnerWaybillDetialEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<TruckOwnerWaybillDetialEntity>(this) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(TruckOwnerWaybillDetialEntity entity) {
                mTruckOwnerWaybillDetialEntity = entity;
                setUiFunction(entity);
            }


            @Override
            public void onApiError(ApiException e) {
                // super.onApiError(e);
            }
        });
    }

    private void isPayFee() {

        Observable<List<OrderEntity>> observable = mSupplyApi.postIsSettle(App.getUserId()).map(new HttpResultFunc<List<OrderEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<OrderEntity>>(mActivity,false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<OrderEntity> value) {
                if (!value.isEmpty()) {
                    mOrderEntity = value.get(0);
                   // mCommonDialog.showDialog(getActivity(), "supply");
                }
            }

            @Override
            public void onApiError(ApiException e) {
                //super.onApiError(e);
                mOrderEntity=null;
            }
        });
    }

    private void setUiFunction(TruckOwnerWaybillDetialEntity entity) {
        truckOwnerWaybillDetialEntity = entity;
        mOrderNumber.setText("单号：" + entity.getOrderNum());
        mDriverName.setText(entity.getName());
        mDriverPhone.setText(entity.getMobile());
        mLicensePlate.setText(entity.getLicensePlate());
        mCarryNumber.setText(entity.getCarrierNum()+ StrUtil.formatUnit(entity.getUnit()));
        mLoadingTime.setText(entity.getLoadingTime());
        if(entity.getLoadingWeight()==null){
            mLoadingWeight.setText("未填写");
        }else{
            mLoadingWeight.setText(entity.getLoadingWeight() +StrUtil.formatUnit(entity.getUnit()));
        }
        if (entity.getLoadingPhoto() !=null){
            GlideUtils.getInstance().loadRound(this,entity.getLoadingPhoto(),mLoadingPic);
        }
        mUnloadingTime.setText(entity.getUnloadingTime());
        if(entity.getUnloadingWeight()==null){
            mUnloadingWeight.setText("未填写");
        }else{
            mUnloadingWeight.setText(entity.getUnloadingWeight()+ StrUtil.formatUnit(entity.getUnit()));
        }

        if (entity.getUnloadingPhoto() !=null){
            GlideUtils.getInstance().loadRound(this,entity.getUnloadingPhoto(),mUnloadingPic);
        }
        if (mType.equals("4")){
            totalMoney.setText("￥"+mTruckOwnerWaybillDetialEntity.getTotalMoney());
        }
        if (mType.equals("5")){

            if (entity.getCar_evaluate_status().equals("1")){
                mTvDriverLocation.setText("已评价");
                mTvDriverLocation.setEnabled(false);
            }
            String orderMoney = entity.getOrderMoney();
            if (!TextUtils.isEmpty(orderMoney)){
                totalMoney.setText(orderMoney+"元");
            }else {
                mTrWaitPay.setVisibility(View.GONE);
            }

        }

        if (entity.getMessageStatus().equals("0")){
            llPayed.setVisibility(View.GONE);
            tvUnpay.setVisibility(View.VISIBLE);
        }else {
            llPayed.setVisibility(View.VISIBLE);
            tvUnpay.setVisibility(View.GONE);
        }
        String driverId = entity.getDriverId();

        //指派自己
        if(driverId.equals(App.getUserId())){
             if (mType.equals("1")||mType.equals("2")){
                 mTvUploading.setVisibility(View.VISIBLE);
                 mTvDriverLocation.setVisibility(View.GONE);
             }
        }
    }


    @OnClick({R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_logistics_info, R.id.tv_driver_location, R.id.tv_uploading,R.id.iv_phone
    ,R.id.loading_pic,R.id.unloading_pic,R.id.tv_unpay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_owner_info: //  货主信息  需要货主id 货源id
                OwnerInfoActivity.openActivity(mActivity, mTruckOwnerWaybillDetialEntity.getmOwnerCargoId()
                        , mTruckOwnerWaybillDetialEntity.getCargoId());
                break;
            case R.id.tv_cargo_info:  // 货物信息 //需要知道货源id
                CargoInfoActivity.openActivity(mActivity, mTruckOwnerWaybillDetialEntity.getCargoId());
                break;
            case R.id.tv_logistics_info: //  物流信息 需要物流id 运单id
                LogisticsInfoActivity.openActivity(mActivity, mTruckOwnerWaybillDetialEntity.getLogisticalId(),waybill_id);
                break;
            case R.id.tv_driver_location:
                if ("5".equals(mType)) {  // 评论
                    Bundle bundle = new Bundle();
                    bundle.putString("waybill_id",waybill_id);
                    showActivity(PublishEvaluateActivity.class,bundle);
                } else { // 司机位置
                    if (TextUtils.isEmpty(mTruckOwnerWaybillDetialEntity.getLat())){
                        Toast.makeText(mActivity,"司机未上传位置",Toast.LENGTH_SHORT).show();
                    }else {
                        DriverLocationActivity.openActivity(mActivity,mTruckOwnerWaybillDetialEntity.getLog(),
                                mTruckOwnerWaybillDetialEntity.getLat());
                    }

                }

                break;
            case R.id.tv_uploading:
                //showActivity(LoadingBillActivity.class);
                if("1".equals(mType)){
                    LoadingBillActivity.openActivity(mActivity,waybill_id,truckOwnerWaybillDetialEntity.getCarrierNum());
                }else{
                    UnloadBillActivity.openActivity(mActivity,waybill_id,truckOwnerWaybillDetialEntity.getCarrierNum()
                    );
                }
                break;
            case R.id.iv_phone:
                commonDialog.setContent(truckOwnerWaybillDetialEntity.getMobile());
                commonDialog.showDialog(WaybillListDetailActivity.this,"phone");
                break;
            case R.id.loading_pic:
                String loadingPhoto = truckOwnerWaybillDetialEntity.getLoadingPhoto();
                if (!TextUtils.isEmpty(loadingPhoto)){
                    viewVehicleLargeMapDialog.setImgUrl(loadingPhoto).showDialog(WaybillListDetailActivity.this,"photo");
                }
                break;
            case R.id.unloading_pic:
                String unloadingPhoto = truckOwnerWaybillDetialEntity.getUnloadingPhoto();
                if (!TextUtils.isEmpty(unloadingPhoto)){
                    viewVehicleLargeMapDialog.setImgUrl(unloadingPhoto).showDialog(WaybillListDetailActivity.this,"photo");
                }
                break;
            case R.id.tv_unpay:
                 if (mOrderEntity!=null){
                     PayMessageFeeActivity.openActivity(mActivity,mOrderEntity);
                 }
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void evaluateSuccess(EvaluateEvent event){
        mTvDriverLocation.setText("已评价");
        mTvDriverLocation.setEnabled(false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccess(RefreshSupplyListEntity  entity){
        initData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
