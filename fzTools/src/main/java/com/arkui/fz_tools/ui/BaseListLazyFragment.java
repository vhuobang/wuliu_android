package com.arkui.fz_tools.ui;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/7/5.
 *
 */

public abstract class BaseListLazyFragment<T> extends BaseLazyFragment implements EasyRefreshLayout.EasyEvent, OnBindViewHolderListener<T> {

    //初始化Adapter
    public CommonAdapter<T> initAdapter(PullRefreshRecyclerView mRlList, int layoutResId) {
        CommonAdapter<T> commonAdapter = new CommonAdapter<>(layoutResId, this);
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(commonAdapter);
        mRlList.addEasyEvent(this);
        //mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        return commonAdapter;
    }



}
