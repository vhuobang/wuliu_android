package com.arkui.fz_tools.adapter.abslistview.base;


import com.arkui.fz_tools.adapter.abslistview.ListViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ListViewHolder holder, T t, int position);


}
