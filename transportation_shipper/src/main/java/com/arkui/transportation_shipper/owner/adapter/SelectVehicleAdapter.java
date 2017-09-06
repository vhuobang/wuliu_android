package com.arkui.transportation_shipper.owner.adapter;

import android.content.Context;

import com.arkui.fz_tools.adapter.AbstractWheelTextAdapter;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;

import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 */

public class SelectVehicleAdapter extends AbstractWheelTextAdapter {

    private List<TruckListEntity> list;
    public SelectVehicleAdapter(Context context, List<TruckListEntity> list) {
        super(context);
        this.list=list;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return list.get(index).getLicense_plate();
    }

    @Override
    public int getItemsCount() {
        return list==null?0:list.size();
    }
}
