package com.arkui.transportation_shipper.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.driver.activity.waybill.DriverWaybillDetailActivity;
import com.arkui.transportation_shipper.owner.activity.waybill.WaybillListDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */

public class DriverWaybillListFragment extends BaseLazyFragment implements OnBindViewHolderListener<String>, EasyRefreshLayout.EasyEvent {

    @BindView(R.id.rl_waybill)
    PullRefreshRecyclerView mRlWaybill;
    private CommonAdapter<String> mWaybillListAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mWaybillListAdapter = CommonAdapter.getInstance(R.layout.item_driver_waybill, this);
        mRlWaybill.addEasyEvent(this);
        mRlWaybill.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlWaybill.setLinearLayoutManager();
        mRlWaybill.setAdapter(mWaybillListAdapter);
       // lazy();

        final int type = getArguments().getInt("type");

        mWaybillListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, DriverWaybillDetailActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
    }

    @Override
    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mWaybillListAdapter.addData("京H786486D");
                mWaybillListAdapter.addData("京H786486D");
                mWaybillListAdapter.addData("京H786486D");
                mWaybillListAdapter.addData("京H786486D");
                mRlWaybill.refreshComplete();
            }
        }, 200);
    }

    @Override
    protected void lazyLoadData() {
        onRefreshing();
    }


    public static DriverWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        DriverWaybillListFragment waybillListFragment = new DriverWaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }

}
