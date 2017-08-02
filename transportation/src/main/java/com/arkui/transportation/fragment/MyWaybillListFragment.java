package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.waybill.CarriageDetailActivity;
import com.arkui.transportation.activity.waybill.DriverLocationActivity;
import com.arkui.transportation.activity.waybill.PlanPublishDetailActivity;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class MyWaybillListFragment extends BaseLazyFragment implements EasyRefreshLayout.EasyEvent, OnBindViewHolderListener<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mListAdapter;
    private int mType;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mRlList.setLinearLayoutManager();
        mRlList.addEasyEvent(this);

        mType = getArguments().getInt("type");

       /* switch (mType){

        }*/

        mListAdapter = new CommonAdapter<>(R.layout.item_my_waybill_list, this);
        mRlList.setAdapter(mListAdapter);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));



        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (mType) {
                    case 0:
                        showActivity(PlanPublishDetailActivity.class);
                        break;
                    case 1:
                        showActivity(CarriageDetailActivity.class);
                        break;
                    case 2:
                    case 3:
                        WaybillDetailActivity.openActivity(mContext, 3,true);
                        //showActivity(CarriageDetailActivity.class);
                        break;
                    case 4:
                        WaybillDetailActivity.openActivity(mContext, 2,true);
                        break;
                    case 5:
                        WaybillDetailActivity.openActivity(mContext, 4,true);
                        break;
                }
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
        onRefreshing();
    }

    public static MyWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        MyWaybillListFragment waybillListFragment = new MyWaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }

    @Override
    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 1000);
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        switch (mType) {
            case 0:
                helper.setVisible(R.id.tv_company, true);
                helper.setText(R.id.tv_company, "500元/吨 7日内结算");
                break;
            case 1:
                helper.setVisible(R.id.tv_company, true);
                helper.setVisible(R.id.tv_state, true);
                helper.setText(R.id.tv_company, "500元/吨 7日内结算");
                break;
            case 2:
            case 3:
                helper.setVisible(R.id.ll_location, true);
                helper.setVisible(R.id.tv_state, false);
                break;
            case 4:
                helper.setVisible(R.id.ll_location, false);
                helper.setVisible(R.id.tv_cost, true);
                helper.setText(R.id.tv_company, "北京美华国际物流有限公司");
                break;
            case 5:
                break;
          /*  case 6:
                helper.setVisible(R.id.ll_location, false);
                helper.setVisible(R.id.tv_state, false);
                break;*/
        }

        helper.addOnClickListener(R.id.ll_location);
    }
}
