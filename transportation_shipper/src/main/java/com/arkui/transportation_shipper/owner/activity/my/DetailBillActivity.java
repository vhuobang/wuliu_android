package com.arkui.transportation_shipper.owner.activity.my;

import android.os.Handler;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailBillActivity extends BaseActivity implements OnBindViewHolderListener<String>,OnRefreshListener {

    @BindView(R.id.rl_bill)
    PullRefreshRecyclerView mRlBill;
    private CommonAdapter<String> mDetailBillAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_detail_bill);
        setTitle("明细账单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mRlBill.setLinearLayoutManager();
        mRlBill.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));
        mRlBill.setOnRefreshListener(this);

        mDetailBillAdapter = CommonAdapter.getInstance(R.layout.item_detail_bill,this);
        mRlBill.setAdapter(mDetailBillAdapter);

        onRefreshing();
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mDetailBillAdapter.addData("积分更新啦！");
                mDetailBillAdapter.addData("积分更新啦！");
                mDetailBillAdapter.addData("积分更新啦！");
                mDetailBillAdapter.addData("积分更新啦！");
                mRlBill.refreshComplete();
            }
        },500);
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
