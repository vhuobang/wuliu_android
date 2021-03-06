package com.arkui.fz_tools.adapter;

import android.content.Context;

import com.arkui.fz_tools.entity.City;

import java.util.List;


/**
 * Created by 任少东 on 2016/07/13 16:51
 */
public class CityAdapter extends AbstractWheelTextAdapter {
    private List<City> list;
    public CityAdapter(Context context, List<City> list) {
        super(context);
        this.list=list;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return list.get(index).getName();
    }

    @Override
    public int getItemsCount() {
        return list==null?0:list.size();
    }
}
