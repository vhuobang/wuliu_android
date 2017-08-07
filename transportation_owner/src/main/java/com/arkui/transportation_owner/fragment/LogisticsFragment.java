package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkui.transportation_owner.activity.logistics.CityPickerActivity;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.logistics.CompanyDetailActivity;
import com.arkui.transportation_owner.activity.logistics.PersonageDetailActivity;
import com.arkui.transportation_owner.activity.logistics.SearchLogisticsActivity;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.transportation_owner.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 物流
 */
public class LogisticsFragment extends BaseFragment implements OnBindViewHolderListener<String>,OnRefreshListener {

    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mLogisticsAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_logistics, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mLogisticsAdapter = new CommonAdapter<>(R.layout.item_logistics,this);
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mLogisticsAdapter);
        mRlList.setOnRefreshListener(this);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST));

        mLogisticsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(PersonageDetailActivity.class);
            }
        });

        mLogisticsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(CompanyDetailActivity.class);
            }
        });
        onRefreshing();

    }

    @OnClick({R.id.tv_city,R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_city:
                showActivity(CityPickerActivity.class);
                break;
            case R.id.tv_search:
                showActivity(SearchLogisticsActivity.class);
                break;
        }

    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_head);
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mLogisticsAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 1000);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
