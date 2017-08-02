package com.arkui.transportation_shipper.owner.activity.my;

import android.os.Handler;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WithdrawRecordActivity extends BaseActivity implements OnBindViewHolderListener<String>,EasyRefreshLayout.EasyEvent {

    @BindView(R.id.rl_record)
    PullRefreshRecyclerView mRlRecord;
    private CommonAdapter<String> mWithdrawRecord;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw_record);
        setTitle("提现记录");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mRlRecord.setLinearLayoutManager();
        mWithdrawRecord = CommonAdapter.getInstance(R.layout.item_withdraw_record,this);
        mRlRecord.setAdapter(mWithdrawRecord);
        mRlRecord.addItemDecoration(new DividerItemDecoration2(mActivity,DividerItemDecoration.VERTICAL_LIST));
        mRlRecord.addEasyEvent(this);
        mRlRecord.setEnablePullToRefresh(false);
        onRefreshing();
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mWithdrawRecord.addData("积分更新啦！");
                mWithdrawRecord.addData("积分更新啦！");
                mWithdrawRecord.addData("积分更新啦！");
                mWithdrawRecord.addData("积分更新啦！");
                mRlRecord.refreshComplete();
            }
        },500);
    }
}
