package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_shipper.owner.activity.asset.DriverDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.DriverManageAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 * 司机管理
 */
public class DriverManageFragment extends BaseFragment implements  BaseQuickAdapter.OnItemClickListener, OnBindViewHolderListener<String>,OnRefreshListener {

    @BindView(R.id.rl_driver)
    PullRefreshRecyclerView mRlVehicle;
    private DriverManageAdapter mDriverManageAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_driver_manage, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mRlVehicle.setLinearLayoutManager();
        mDriverManageAdapter = new DriverManageAdapter(this);
        mRlVehicle.setAdapter(mDriverManageAdapter);

        mRlVehicle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlVehicle.setOnRefreshListener(this);

        mDriverManageAdapter.setOnItemClickListener(this);

        onRefreshing();
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mDriverManageAdapter.addData("张建军");
                mDriverManageAdapter.addData("田少坤");
                mDriverManageAdapter.addData("李国强");
                mRlVehicle.refreshComplete();
            }
        }, 1000);
    }

    /**
     * 单击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showActivity(DriverDetailActivity.class);
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content,"1532222214578 出车数：236");
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
