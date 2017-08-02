package com.arkui.transportation_shipper.owner.activity.waybill;

import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.driver.activity.waybill.LoadingBillActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    private int mType;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_waybill_list_detail);
        setTitle("运单详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mType = getIntent().getIntExtra("type", 0);

        switch (mType) {
            case 3:
                mTvDriverLocation.setVisibility(View.GONE);
                mTrWaitPay.setVisibility(View.VISIBLE);
                break;
            case 4:
                mTvDriverLocation.setText("评价");
                break;
        }

        int position = getIntent().getIntExtra("position", -1);

        if (position == 0) {
            mTvDriverLocation.setVisibility(View.GONE);
            mTvUploading.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.tv_owner_info, R.id.tv_cargo_info, R.id.tv_logistics_info, R.id.tv_driver_location,R.id.tv_uploading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_owner_info:
                showActivity(OwnerInfoActivity.class);
                break;
            case R.id.tv_cargo_info:
                showActivity(CargoInfoActivity.class);
                break;
            case R.id.tv_logistics_info:
                showActivity(LogisticsInfoActivity.class);
                break;
            case R.id.tv_driver_location:
                if (mType == 4) {
                    showActivity(PublishEvaluateActivity.class);
                } else {
                    showActivity(DriverLocationActivity.class);
                }

                break;
            case R.id.tv_uploading:
                showActivity(LoadingBillActivity.class);
                break;
        }
    }
}
