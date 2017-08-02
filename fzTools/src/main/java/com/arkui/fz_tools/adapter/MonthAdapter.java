package com.arkui.fz_tools.adapter;

import android.content.Context;

import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.entity.TimeEntity;

import java.util.List;


/**
 * Created by 任少东 on 2016/07/13 16:51
 */
public class MonthAdapter extends AbstractWheelTextAdapter {
    private List<TimeEntity> list;
    public MonthAdapter(Context context, List<TimeEntity> list) {
        super(context);
        this.list=list;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return list.get(index).getMonth();
    }

    @Override
    public int getItemsCount() {
        return list==null?0:list.size();
    }
}
