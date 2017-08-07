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
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.home.SupplyDetailActivity;
import com.arkui.transportation.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class HomePublishListFragment extends BaseListLazyFragment<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;
    private int mType;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_home_publish_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mType = getArguments().getInt("type");

        mCommonAdapter = initAdapter(mRlList, R.layout.item_logistics);
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));

        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,SupplyDetailActivity.class);
                intent.putExtra("type", mType);
                startActivity(intent);
            }
        });

       /* mCommonAdapter.setOnItemChildClickListener(ic_new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });*/
    }

    @Override
    protected void lazyLoadData() {
       onRefreshing();
    }

    public static HomePublishListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        HomePublishListFragment homePublishListFragment = new HomePublishListFragment();
        homePublishListFragment.setArguments(bundle);
        return homePublishListFragment;
    }


    @Override
    public void convert(BaseViewHolder helper, String item) {
        //helper.addOnClickListener(R.id.iv_head);
        if(mType==1){
            helper.setText(R.id.tv_info,"柴油/500吨");
        }

    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mCommonAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 300);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
