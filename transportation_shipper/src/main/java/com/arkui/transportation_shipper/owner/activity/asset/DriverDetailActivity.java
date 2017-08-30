package com.arkui.transportation_shipper.owner.activity.asset;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AssetDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.dialog.ViewVehicleLargeMapDialog;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseViewHolder;


public class DriverDetailActivity extends BaseActivity implements OnBindViewHolderListener<String>,OnConfirmClick {

    @BindView(R.id.rl_driver)
    PullRefreshRecyclerView mRlDriver;
    private CommonAdapter<String> mRlVehicleDerailsAdapter;
    private ViewVehicleLargeMapDialog mViewVehicleLargeMapDialog;
    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_detail);
        setTitle("司机详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mRlDriver.setLinearLayoutManager();
        mRlVehicleDerailsAdapter = CommonAdapter.getInstance(R.layout.item_vehicle_details, this);
        mRlDriver.setAdapter(mRlVehicleDerailsAdapter);
        mRlDriver.addItemDecoration(new AssetDecoration(mActivity, AssetDecoration.VERTICAL_LIST));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mRlVehicleDerailsAdapter.addData("车辆已装货");
                mRlVehicleDerailsAdapter.addData("车辆已装货");
                mRlDriver.refreshComplete();
            }
        }, 2000);
        mRlDriver.setEnablePullToRefresh(false);

        View vehicle_details_head = getLayoutInflater().inflate(R.layout.layout_driver_details_head, mRlDriver, false);
        mRlVehicleDerailsAdapter.addHeaderView(vehicle_details_head);

        vehicle_details_head.findViewById(R.id.iv_front);
      //  ImageView IvFront = ButterKnife.findById(vehicle_details_head, R.id.iv_front);
        //IvFront.setOnClickListener(this);

        initDialog();
    }

    private void initDialog() {
        mViewVehicleLargeMapDialog = new ViewVehicleLargeMapDialog();
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("删除司机").setContent("删除司机后，对应的手机号将不能登录司机端，确定要删除吗？").setConfirmClick(this);
    }

    //适配器回调
    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public void onConfirmClick() {

    }

    @OnClick({R.id.tv_edit, R.id.tv_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                showActivity(DriverEditActivity.class);
                break;
            case R.id.tv_del:
                mCommonDialog.show(getSupportFragmentManager(), "del");
                break;
            case R.id.iv_front:
                //showActivity(ViewVehicleLargeMapActivity.class);
                mViewVehicleLargeMapDialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
    }


}
