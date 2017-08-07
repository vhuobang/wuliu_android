package com.arkui.transportation.activity.waybill;

import android.os.Handler;
import android.view.View;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.utils.ListData;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CarriageDetailActivity extends BaseActivity implements  OnBindViewHolderListener<String>,OnRefreshListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCarriageDetailAdapter;
    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_carriage_detail);
        setTitle("承运详情");
        setRight("再来一单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mRlList.setLinearLayoutManager();
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));

        mCarriageDetailAdapter = new CommonAdapter<>(R.layout.item_carriage_detail, this);

        mRlList.setOnRefreshListener(this);
        mRlList.setAdapter(mCarriageDetailAdapter);

        View mCarriageHeaderView = getLayoutInflater().inflate(R.layout.layout_carriage_header, mRlList, false);
        mCarriageDetailAdapter.setHeaderView(mCarriageHeaderView);

        onRefreshing();

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("停止抢单").setContent("该运单还剩20吨没有被抢，确定要停止发布吗？");
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(EditPlanPublishDetailActivity.class);
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCarriageDetailAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 1000);
    }

    @OnClick(R.id.tv_state)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(),"state");
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
