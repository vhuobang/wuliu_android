package com.arkui.transportation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.ui.BaseListLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.waybill.DriverLocationActivity;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation.listener.TextAdapter;
import com.arkui.transportation.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class OwnerWaybillListFragment extends BaseListLazyFragment<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;
    private int mType;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_owner_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mCommonAdapter = initAdapter(mRlList, R.layout.item_owner_waybill_list);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mType = getArguments().getInt("type");

        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext, WaybillDetailActivity.class);
                intent.putExtra("type",mType);
                startActivity(intent);
            }
        });
        mCommonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(DriverLocationActivity.class);
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        onRefreshing();
    }

    public static OwnerWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        OwnerWaybillListFragment ownerWaybillListFragment = new OwnerWaybillListFragment();
        ownerWaybillListFragment.setArguments(bundle);
        return ownerWaybillListFragment;
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mCommonAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 1000);
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        switch (mType){
            case 0:
            case 1:
                helper.setVisible(R.id.ll_location,true);
                break;
            case 2:
                helper.setVisible(R.id.tv_state,true);
                break;
        }
        helper.addOnClickListener(R.id.ll_location);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
