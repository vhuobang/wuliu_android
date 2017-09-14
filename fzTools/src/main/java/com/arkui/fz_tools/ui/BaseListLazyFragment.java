package com.arkui.fz_tools.ui;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by nmliz on 2017/7/5.
 *
 */
@Deprecated
public abstract class BaseListLazyFragment<T> extends BaseLazyFragment implements OnRefreshListener, OnBindViewHolderListener<T> {

    //初始化Adapter
    public CommonAdapter<T> initAdapter(PullRefreshRecyclerView mRlList, int layoutResId) {
        CommonAdapter<T> commonAdapter = new CommonAdapter<>(layoutResId, this);
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(commonAdapter);
        mRlList.setOnRefreshListener(this);
        //mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        return commonAdapter;
    }



}
