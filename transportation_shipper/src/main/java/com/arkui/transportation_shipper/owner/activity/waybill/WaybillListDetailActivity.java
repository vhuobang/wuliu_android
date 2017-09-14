package com.arkui.transportation_shipper.owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.driver.activity.waybill.LoadingBillActivity;
import com.arkui.transportation_shipper.driver.activity.waybill.UnloadBillActivity;
import com.arkui.transportation_shipper.owner.api.LogisticalApi;
import com.arkui.transportation_shipper.owner.entity.TruckOwnerWaybillDetialEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 车主端运单详情界面
 */

public class WaybillListDetailActivity extends BaseActivity {

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
    private String mType;
    private String waybill_id;
    private LogisticalApi logisticalApi;
    private TruckOwnerWaybillDetialEntity mTruckOwnerWaybillDetialEntity;


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
        mType = getIntent().getStringExtra("type");
        waybill_id = getIntent().getStringExtra("waybill_id");
        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        switch (mType) {
            case "4":
                mTvDriverLocation.setVisibility(View.GONE);
                mTrWaitPay.setVisibility(View.VISIBLE);
                break;
            case "5":
                mTvDriverLocation.setText("评价");
                break;
        }
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


    private void setUiFunction(TruckOwnerWaybillDetialEntity entity) {
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
        String driverId = entity.getDriverId();

        //指派自己
        if(driverId.equals(App.getUserId())){
            //TODO 2017年9月11日 字太多撑爆了 现在就四个字
            //mTvUploading.setText("上传装货磅单");
            mTvUploading.setVisibility(View.VISIBLE);
            mTvDriverLocation.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_logistics_info, R.id.tv_driver_location, R.id.tv_uploading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_owner_info: //  货主信息  需要货主id 货源id
                OwnerInfoActivity.openActivity(mActivity, mTruckOwnerWaybillDetialEntity.getUserId()
                        , mTruckOwnerWaybillDetialEntity.getCargoId());
                break;
            case R.id.tv_cargo_info:  // 货物信息 //需要知道货源id
                CargoInfoActivity.openActivity(mActivity, mTruckOwnerWaybillDetialEntity.getCargoId());
                break;
            case R.id.tv_logistics_info: //  物流信息 需要物流id
                LogisticsInfoActivity.openActivity(mActivity, mTruckOwnerWaybillDetialEntity.getLogisticalId());
                break;
            case R.id.tv_driver_location:
                if ("5".equals(mType)) {  // 评论
                    showActivity(PublishEvaluateActivity.class);
                } else { // 司机位置
                    showActivity(DriverLocationActivity.class);
                }

                break;
            case R.id.tv_uploading:
                //showActivity(LoadingBillActivity.class);
                if("1".equals(mType)){
                    LoadingBillActivity.openActivity(mActivity,waybill_id);
                }else{
                    UnloadBillActivity.openActivity(mActivity,waybill_id);
                }
                break;

        }
    }



}
