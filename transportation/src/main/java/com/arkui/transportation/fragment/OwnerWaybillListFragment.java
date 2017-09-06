package com.arkui.transportation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseListLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.waybill.DriverLocationActivity;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.LogWayBIllListEntity;
import com.arkui.transportation.presenter.LogWaybillListPresenter;
import com.arkui.transportation.view.LogWaybillListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 * <p>
 *
 *  物流端 货主运单列表
 * 1."待装货",2. "运输中",3. "待付款",4. "待收款",5. "已完成"
 */
public class OwnerWaybillListFragment extends BaseListLazyFragment<LogWayBIllListEntity> implements LogWaybillListView {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<LogWayBIllListEntity> mCommonAdapter;
    private int mType;
    private LogWaybillListPresenter mLogWaybillListPresenter;
    public static final String type = "1";

    // 传入type
    public static OwnerWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        OwnerWaybillListFragment ownerWaybillListFragment = new OwnerWaybillListFragment();
        ownerWaybillListFragment.setArguments(bundle);
        return ownerWaybillListFragment;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_owner_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mLogWaybillListPresenter = new LogWaybillListPresenter(this, getActivity());
        mCommonAdapter = initAdapter(mRlList, R.layout.item_owner_waybill_list);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mType = getArguments().getInt("type");

        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
                Intent intent = new Intent(mContext, WaybillDetailActivity.class);
                intent.putExtra("type", mType);
                intent.putExtra("waybillId",item.getId());
                startActivity(intent);
            }
        });

        mCommonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
                DriverLocationActivity.openActivity(getActivity(),item.getLog(),item.getLat());
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        onRefreshing();
    }

    public void onRefreshing() {
        mLogWaybillListPresenter.getWaybillList(App.getUserId(), mType + "", type);
    }

    @Override
    public void convert(BaseViewHolder helper, LogWayBIllListEntity item) {
        helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName());
        helper.setText(R.id.tv_address, item.getLoadingAddress());
        helper.setText(R.id.tv_unloading_address, item.getUnloadingAddress());
        switch (mType) {
            case 1:
                helper.setVisible(R.id.ll_location, true);
                helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName()
                        + "  预装" + item.getCarrierNum() + StrUtil.formatUnit(item.getUnit()));
                break;
            case 2:
                helper.setVisible(R.id.ll_location, true);
                break;
            case 3:
                helper.setVisible(R.id.tv_state, true);
                break;
        }
        helper.addOnClickListener(R.id.ll_location);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }

    // 数据请求成功
    @Override
    public void onSuccess(List<LogWayBIllListEntity> logWayBIllListEntities) {
        mCommonAdapter.setNewData(logWayBIllListEntities);
        mRlList.refreshComplete();

    }

    //数据请求失败
    @Override
    public void onLoadDataFail(String errorMessage) {
        mRlList.loadFail();
        mRlList.refreshComplete();
    }
}
