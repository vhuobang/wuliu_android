package com.arkui.transportation_shipper.owner.adapter;

import android.content.Context;

import com.arkui.fz_tools.adapter.AbstractWheelTextAdapter;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;

import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 */

public class VehicleModelAdapter extends AbstractWheelTextAdapter {

    private List<VehicleModelEntity> list;
    public VehicleModelAdapter(Context context, List<VehicleModelEntity> list) {
        super(context);
        this.list=list;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return list.get(index).getTruck_type();
    }

    @Override
    public int getItemsCount() {
        return list==null?0:list.size();
    }
}
