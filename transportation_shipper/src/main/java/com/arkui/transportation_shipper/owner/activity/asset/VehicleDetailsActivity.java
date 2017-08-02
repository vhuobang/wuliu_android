package com.arkui.transportation_shipper.owner.activity.asset;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VehicleDetailsActivity extends BaseActivity implements OnBindViewHolderListener<String>, View.OnClickListener, OnConfirmClick {

    @BindView(R.id.rl_vehicle_derails)
    PullRefreshRecyclerView mRlVehicleDerails;
    private CommonAdapter<String> mRlVehicleDerailsAdapter;
    private ViewVehicleLargeMapDialog mViewVehicleLargeMapDialog;
    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehicle_details);
        setTitle("车辆详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mRlVehicleDerails.setLinearLayoutManager();
        //CommonAdapter<String> mRlVehicleDerailsAdapter=new CommonAdapter<>(R.layout.item_vehicle_details,this);

        mRlVehicleDerailsAdapter = CommonAdapter.getInstance(R.layout.item_vehicle_details, this);
        mRlVehicleDerails.setAdapter(mRlVehicleDerailsAdapter);
        mRlVehicleDerails.addItemDecoration(new AssetDecoration(mActivity,AssetDecoration.VERTICAL_LIST));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mRlVehicleDerailsAdapter.addData("车辆已装货");
                mRlVehicleDerailsAdapter.addData("车辆已装货");
                mRlVehicleDerails.refreshComplete();
            }
        }, 2000);
        mRlVehicleDerails.setEnablePullToRefresh(false);

        View vehicle_details_head = getLayoutInflater().inflate(R.layout.layout_vehicle_details_head, mRlVehicleDerails, false);
        mRlVehicleDerailsAdapter.addHeaderView(vehicle_details_head);

        vehicle_details_head.findViewById(R.id.iv_front);
        ImageView IvFront = ButterKnife.findById(vehicle_details_head, R.id.iv_front);
        IvFront.setOnClickListener(this);

        initDialog();
    }

    private void initDialog() {
        mViewVehicleLargeMapDialog = new ViewVehicleLargeMapDialog();
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("删除车辆").setContent("确定删除该条车辆信息吗？").setConfirmClick(this);
    }

    //适配器回调
    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @OnClick({R.id.tv_edit, R.id.tv_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                showActivity(VehicleEditedActivity.class);
                break;
            case R.id.tv_del:
                mCommonDialog.show(getSupportFragmentManager(), "del");
                break;
            case R.id.iv_front:
                //showActivity(ViewVehicleLargeMapActivity.class);
                mViewVehicleLargeMapDialog.show(getFragmentManager(), "dialog");
                break;
        }
    }

    //删除回调
    @Override
    public void onConfirmClick() {

    }
}
