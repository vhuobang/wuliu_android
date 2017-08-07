package com.arkui.fz_tools.ui;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


public abstract class BaseListActivity<T> extends BaseActivity implements OnBindViewHolderListener<T>,OnRefreshListener {


    //初始化Adapter
    public CommonAdapter<T> initAdapter(PullRefreshRecyclerView mRlList, int layoutResId) {
        CommonAdapter<T> commonAdapter = new CommonAdapter<>(layoutResId, this);
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(commonAdapter);
        mRlList.setOnRefreshListener(this);
        //mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        return commonAdapter;
    }

    @Override
    public void convert(BaseViewHolder helper, T item) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}
