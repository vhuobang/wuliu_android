package com.arkui.transportation.activity.my;

import android.os.Handler;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectBankCardActivity extends BaseActivity implements  OnBindViewHolderListener<String>,OnRefreshListener {

    @BindView(R.id.rl_bank)
    PullRefreshRecyclerView mRlBank;
    private CommonAdapter<String> mSelectBankCardAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_select_bank_card);
        setTitle("选择银行卡");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mRlBank.setLinearLayoutManager();
        mRlBank.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));

        mSelectBankCardAdapter = CommonAdapter.getInstance(R.layout.item_select_bank,this);
        mRlBank.setAdapter(mSelectBankCardAdapter);
        mRlBank.setOnRefreshListener(this);

        onRefreshing();
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSelectBankCardAdapter.addData("积分更新啦！");
                mSelectBankCardAdapter.addData("积分更新啦！");
                mSelectBankCardAdapter.addData("积分更新啦！");
                mSelectBankCardAdapter.addData("积分更新啦！");
                mRlBank.refreshComplete();
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
