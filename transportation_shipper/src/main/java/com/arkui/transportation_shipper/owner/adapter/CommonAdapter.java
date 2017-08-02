package com.arkui.transportation_shipper.owner.adapter;

import android.support.v7.widget.RecyclerView;

import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by nmliz on 2017/6/21.
 */

public class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    OnBindViewHolderListener<T> mOnBindViewHolderListener;

    public static <T> CommonAdapter<T> getInstance(int layoutResId, OnBindViewHolderListener<T> onBindViewHolderListener) {
        return new CommonAdapter<T>(layoutResId, onBindViewHolderListener);
    }

    public CommonAdapter(int layoutResId, OnBindViewHolderListener<T> onBindViewHolderListener) {
        super(layoutResId);
        this.mOnBindViewHolderListener = onBindViewHolderListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (mOnBindViewHolderListener != null) {
            mOnBindViewHolderListener.convert(helper, item);
        }
    }
}
