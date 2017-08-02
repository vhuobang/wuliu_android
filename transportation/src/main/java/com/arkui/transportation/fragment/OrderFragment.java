package com.arkui.transportation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MessageDetailsActivity;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class OrderFragment extends BaseFragment implements  EasyRefreshLayout.EasyEvent, OnBindViewHolderListener<String> {

    @BindView(R.id.rl_order)
    PullRefreshRecyclerView mRlOrder;
   // @BindView(R.id.refresh)
   // EasyRefreshLayout mRefresh;
    private CommonAdapter<String> mOrderMessageAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlOrder.setLayoutManager(new LinearLayoutManager(mContext));
        mOrderMessageAdapter = new CommonAdapter(R.layout.item_system_message,this);
        mRlOrder.setAdapter(mOrderMessageAdapter);
        mRlOrder.addEasyEvent(this);

        //mRlOrder.autoRefresh();
        mRlOrder.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        mOrderMessageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               /* Intent intent=new Intent(mContext, MessageDetailsActivity.class);
                intent.putExtra("title","运单消息");
                startActivity(intent);*/
               showActivity(WaybillDetailActivity.class);
            }
        });

        onRefresh();
    }


    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mOrderMessageAdapter.addData("车辆已装货");
                mOrderMessageAdapter.addData("车辆已装货");
                mOrderMessageAdapter.addData("车辆已装货");
                mOrderMessageAdapter.addData("车辆已装货");
                mRlOrder.refreshComplete();
            }
        },1000);

    }

    @Override
    public void onRefreshing() {
        onRefresh();
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
    }
}
