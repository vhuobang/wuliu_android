package com.arkui.fz_tools.adapter.recycler_base;

import android.content.Context;
import android.view.LayoutInflater;

import com.arkui.fz_tools.adapter.recycler_base.base.ItemViewDelegate;
import com.arkui.fz_tools.adapter.recycler_base.base.ViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
@Deprecated
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;

    public void setData(List<T> mDatas) {
        this.mDatas=mDatas;
        notifyDataSetChanged();
    }

    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        if (position != mDatas.size()) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, mDatas.size() - position);
        }

    }

}
