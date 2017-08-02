package com.arkui.transportation_owner.activity.publish;

import android.os.Handler;
import android.view.View;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectLogisticsActivity extends BaseListActivity<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_select_logistics);
        setTitle("选择物流公司");
        setRightIcon(R.mipmap.go_collect);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mCommonAdapter = initAdapter(mRlList, R.layout.item_select_logistics);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        mCommonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                view.setSelected(!view.isSelected());
            }
        });

        onRefreshing();
    }

    @Override
    public void onRefreshing() {
        super.onRefreshing();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mCommonAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 1000);
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        super.convert(helper, item);
        helper.addOnClickListener(R.id.iv_select);
    }

    @OnClick(R.id.tv_publish)
    public void onClick() {
        showActivity(PublishDeclareActivity.class);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(LogisticsListActivity.class);
    }
}
