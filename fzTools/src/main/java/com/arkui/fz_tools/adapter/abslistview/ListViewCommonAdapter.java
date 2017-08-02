package com.arkui.fz_tools.adapter.abslistview;

import android.content.Context;

import com.arkui.fz_tools.adapter.abslistview.base.ItemViewDelegate;

import java.util.List;

public abstract class ListViewCommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public ListViewCommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);
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
            public void convert(ListViewHolder holder, T t, int position) {
                ListViewCommonAdapter.this.convert(holder, t, position);
            }
        });
    }


    protected abstract void convert(ListViewHolder listViewHolder, T item, int position);

    public void setData(List<T> list) {

       // mDatas.addAll(list);
        this.mDatas=list;
        notifyDataSetChanged();
    }

}
