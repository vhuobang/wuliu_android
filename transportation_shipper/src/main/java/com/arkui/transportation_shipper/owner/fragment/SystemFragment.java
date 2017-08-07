package com.arkui.transportation_shipper.owner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.activity.MessageDetailsActivity;
import com.arkui.transportation_shipper.owner.adapter.SystemMessageAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系统消息
 */
public class SystemFragment extends BaseFragment implements OnRefreshListener {

    @BindView(R.id.rl_system)
    PullRefreshRecyclerView mRlSystem;
   /* @BindView(R.id.refresh)
    EasyRefreshLayout mRefresh;*/
    private SystemMessageAdapter mAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_system, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlSystem.setOnRefreshListener(this);
        mAdapter = new SystemMessageAdapter();
        mRlSystem.setLayoutManager(new LinearLayoutManager(mContext));
        mRlSystem.setAdapter(mAdapter);
        mRlSystem.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext, MessageDetailsActivity.class);
                intent.putExtra("title","系统消息");
                startActivity(intent);
            }
        });

        onRefreshing();
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mAdapter.addData("积分更新啦！");
                mAdapter.addData("积分更新啦！");
                mAdapter.addData("积分更新啦！");
                mAdapter.addData("积分更新啦！");
                mRlSystem.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
