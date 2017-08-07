package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.activity.asset.VehicleDetailsActivity;
import com.arkui.transportation_shipper.owner.adapter.VehicleManageAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 * 车辆管理
 */
public class VehicleManageFragment extends BaseFragment implements  BaseQuickAdapter.OnItemClickListener, OnRefreshListener {

    @BindView(R.id.rl_vehicle)
    PullRefreshRecyclerView mRlVehicle;
    private VehicleManageAdapter mVehicleManageAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_vehicle_manage, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlVehicle.setLinearLayoutManager();
        mVehicleManageAdapter = new VehicleManageAdapter();
        mRlVehicle.setAdapter(mVehicleManageAdapter);
        mVehicleManageAdapter.setOnItemClickListener(this);

        mRlVehicle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlVehicle.setOnRefreshListener(this);
        onRefreshing();
    }


    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mVehicleManageAdapter.addData("京H786486D");
                mVehicleManageAdapter.addData("京H786486D");
                mVehicleManageAdapter.addData("京H786486D");
                mVehicleManageAdapter.addData("京H786486D");
                mRlVehicle.refreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showActivity(VehicleDetailsActivity.class);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
