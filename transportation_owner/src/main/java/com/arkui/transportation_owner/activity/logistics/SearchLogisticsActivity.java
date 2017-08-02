package com.arkui.transportation_owner.activity.logistics;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.HistorySearchDividerItem;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.publish.PublishDeclareActivity;
import com.arkui.transportation_owner.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchLogisticsActivity extends BaseActivity<String> implements EasyRefreshLayout.EasyEvent, OnBindViewHolderListener<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    @BindView(R.id.rl_search)
    PullRefreshRecyclerView mRlSearch;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    private CommonAdapter<String> mLogisticsAdapter;
    private boolean mIsSelect;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_search_logistics);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mIsSelect = getIntent().getBooleanExtra("isSelect", false);
        mRlList.setLinearLayoutManager();
        CommonAdapter<String> mHistorySearchAdapter = new CommonAdapter<String>(R.layout.item_history_search, this);
        mRlList.setAdapter(mHistorySearchAdapter);
        mHistorySearchAdapter.addData(ListData.getTestData(""));
        mRlList.setEnablePullToRefresh(false);
        mRlList.addItemDecoration(new HistorySearchDividerItem(mActivity, HistorySearchDividerItem.VERTICAL_LIST));
        mRlList.setRecyclerBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));

        View mViewSearchFooter = getLayoutInflater().inflate(R.layout.layout_search_footer, mRlList, false);
        mHistorySearchAdapter.setFooterView(mViewSearchFooter);

        mHistorySearchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mRlList.setVisibility(View.GONE);
                mRlSearch.setVisibility(View.VISIBLE);
                if (mIsSelect) {
                    mTvNext.setVisibility(View.VISIBLE);
                }
            }
        });

        mLogisticsAdapter = new CommonAdapter<>(R.layout.item_logistics, this);
        mRlSearch.setLinearLayoutManager();
        mRlSearch.setAdapter(mLogisticsAdapter);
        mRlSearch.addEasyEvent(this);
        mRlSearch.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

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


    @OnClick({R.id.tv_cancel,R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_next:
                showActivity(PublishDeclareActivity.class);
                break;
        }
    }

    @Override
    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mLogisticsAdapter.addData(ListData.getTestData(""));
            }
        }, 300);

        mRlSearch.refreshComplete();
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {

    }
}
