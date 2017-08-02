package com.arkui.transportation.activity.home;

import android.os.Handler;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.utils.ListData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CarriageListActivity extends BaseListActivity<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_carriage_list);
        setTitle("承运详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mCommonAdapter = initAdapter(mRlList, R.layout.item_carriage_list);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        onRefreshing();
    }

    @Override
    public void onRefreshing() {
        super.onRefreshing();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mCommonAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 300);
    }
}
