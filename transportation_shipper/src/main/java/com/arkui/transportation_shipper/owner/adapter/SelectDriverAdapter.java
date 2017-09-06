package com.arkui.transportation_shipper.owner.adapter;

import android.content.Context;

import com.arkui.fz_tools.adapter.AbstractWheelTextAdapter;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;

import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 */

public class SelectDriverAdapter extends AbstractWheelTextAdapter {

    private List<DriverListEntity> list;
    public SelectDriverAdapter(Context context, List<DriverListEntity> list) {
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
