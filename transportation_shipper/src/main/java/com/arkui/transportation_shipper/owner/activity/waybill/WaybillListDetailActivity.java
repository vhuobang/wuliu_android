package com.arkui.transportation_shipper.owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkui.fz_tools._interface.WayBillDetialsInterface;
import com.arkui.fz_tools.entity.WayBillDetailEntity;
import com.arkui.fz_tools.mvp.WayBillDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WaybillListDetailActivity extends BaseActivity implements WayBillDetialsInterface {

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
    private String mType;
    private String waybill_id;
    WayBillDetailPresenter wayBillDetailPresenter;


    public static void openActivity(Context mContext,String type,String waybillId){
        Intent intent=new Intent(mContext, WaybillListDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("waybill_id",waybillId);
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
        wayBillDetailPresenter = new WayBillDetailPresenter(this, this);
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
        wayBillDetailPresenter.getWayBillDetail(waybill_id, App.getUserId());
    }


    @OnClick({R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_logistics_info, R.id.tv_driver_location,R.id.tv_uploading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_owner_info: //  货主信息
                showActivity(OwnerInfoActivity.class);
                break;
            case R.id.tv_cargo_info:  // 货物信息
                showActivity(CargoInfoActivity.class);
                break;
            case R.id.tv_logistics_info: //  物流信息
                showActivity(LogisticsInfoActivity.class);
                break;
            case R.id.tv_driver_location:
                if ("5".equals(mType)) {  // 评论
                    showActivity(PublishEvaluateActivity.class);
                } else { // 司机位置
                    showActivity(DriverLocationActivity.class);
                }
                break;

        }
    }

    // 请求数据成功
    @Override
    public void onSuccess(WayBillDetailEntity wayBillDetailEntity) {

    }
   // 请求数据失败
    @Override
    public void onFail(String message) {

    }
}
