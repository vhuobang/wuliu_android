package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools._interface.WaybillListInterface;
import com.arkui.fz_tools.entity.LogWayBIllListEntity;
import com.arkui.fz_tools.mvp.WaybillListPresenter;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.waybill.DriverLocationActivity;
import com.arkui.transportation_owner.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation_owner.adapter.WaybillListAdapter;
import com.arkui.transportation_owner.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 * <p>
 * //运单列表
 * 1、待装货；2、运输中；3、待付款；5、已完成
 */
public class WaybillListFragment extends BaseLazyFragment implements OnRefreshListener, WaybillListInterface {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private WaybillListAdapter mListAdapter;
    private int mType;
    private WaybillListPresenter waybillListPresenter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlList.setLinearLayoutManager();
        mRlList.setOnRefreshListener(this);
        mType = getArguments().getInt("type");
        waybillListPresenter = new WaybillListPresenter(this, getActivity());
        mListAdapter = new WaybillListAdapter(R.layout.item_waybill);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
                WaybillDetailActivity.openActivity(mContext, mType,item.getId());
            }
        });

        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
               DriverLocationActivity.openActivity(getActivity(),item.getLog(),item.getLat());
            }
        });
        mRlList.setAdapter(mListAdapter);
    }

    @Override
    protected void lazyLoadData() {
        Log.e("lzb","lazyLoadData");
        loadWayBillListData();
    }

    // 请求货主
    private void loadWayBillListData() {
        waybillListPresenter.getWaybillList(App.getUserId(), String.valueOf(mType));
    }

    public static WaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        WaybillListFragment waybillListFragment = new WaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        loadWayBillListData();
    }

    @Override
    public void onSuccess(List<LogWayBIllListEntity> logWayBIllListEntityList) {
        mListAdapter.setNewData(logWayBIllListEntityList);
        mRlList.refreshComplete();
    }

    @Override
    public void onError(String errorMessage) {
        mRlList.refreshComplete();
        mRlList.loadFail();
    }
}
