package com.arkui.fz_tools.adapter;

import android.content.Context;

import com.arkui.fz_tools.entity.City;

import java.util.List;


/**
 * Created by 任少东 on 2016/07/13 16:51
 */
public class StringAdapter extends AbstractWheelTextAdapter {
    private List<String> list;
    public StringAdapter(Context context, List<String> list) {
        super(context);
        this.list=list;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return list.get(index);
    }

    @Override
    public int getItemsCount() {
        return list==null?0:list.size();
    }
}
