package com.arkui.fz_tools.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.R;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.utils.DestroyActivityUtils;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;


public abstract class BaseListActivity<T> extends BaseActivity implements OnBindViewHolderListener<T>, EasyRefreshLayout.EasyEvent {


    //初始化Adapter
    public CommonAdapter<T> initAdapter(PullRefreshRecyclerView mRlList, int layoutResId) {
        CommonAdapter<T> commonAdapter = new CommonAdapter<>(layoutResId, this);
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(commonAdapter);
        mRlList.addEasyEvent(this);
        //mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        return commonAdapter;
    }

    @Override
    public void convert(BaseViewHolder helper, T item) {

    }

    @Override
    public void onRefreshing() {

    }

}
