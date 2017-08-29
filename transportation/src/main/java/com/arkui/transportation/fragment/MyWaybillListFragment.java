package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.waybill.DriverLocationActivity;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation.adapter.MyWaybillListAdapter;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.LogWayBIllListEntity;
import com.arkui.transportation.presenter.LogWaybillListPresenter;
import com.arkui.transportation.view.LogWaybillListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class MyWaybillListFragment extends BaseLazyFragment implements OnRefreshListener, LogWaybillListView {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private MyWaybillListAdapter mListAdapter;
    private int mType;
    private LogWaybillListPresenter logWaybillListPresenter;
    String type ="2"; // 表示我的运单

    // 提供一个实例
    public static MyWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        MyWaybillListFragment waybillListFragment = new MyWaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        logWaybillListPresenter = new LogWaybillListPresenter(this, getActivity());
        mRlList.setLinearLayoutManager();
        mRlList.setOnRefreshListener(this);
        mType = getArguments().getInt("type");
        initReleaseAdapter();
    }

    private void initReleaseAdapter() {
        mListAdapter = new MyWaybillListAdapter(R.layout.item_owner_waybill_list);
        mRlList.setAdapter(mListAdapter);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlList.setOnRefreshListener(this);
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
                WaybillDetailActivity.openActivity(mContext, mType, true, item.getId());
            }
        });

        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(DriverLocationActivity.class);
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        loadWayBillListData();
    }

    /**
     * 运单列表
     */
    private void loadWayBillListData() {
        logWaybillListPresenter.getWaybillList(App.getUserId(),mType+"",type);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        loadWayBillListData();
    }


    @Override
    public void onSuccess(List<LogWayBIllListEntity> logWayBIllListEntities) {
          mListAdapter.setNewData(logWayBIllListEntities);
         mRlList.refreshComplete();
    }

    @Override
    public void onLoadDataFail(String errorMessage) {
       mRlList.refreshComplete();
        mRlList.loadFail();
    }
}
