package com.arkui.transportation_shipper.owner.adapter;

import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.entity.DriverDetailEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/9/5.
 */

public class DriverDetailAdapter extends BaseQuickAdapter<DriverDetailEntity,BaseViewHolder>{

    public DriverDetailAdapter() {
        super(R.layout.item_vehicle_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverDetailEntity item) {
        helper.setText(R.id.tv_name,String.format("%s %s",item.getLicense_plate(),item.getCargo_name()))
                .setText(R.id.tv_loading_address, StrUtil.splitAddress(item.getLoading_address()))
                .setText(R.id.tv_unloading_address,StrUtil.splitAddress(item.getUnloading_address()))
                .setText(R.id.tv_company,item.getLogistical_name());
        helper.setText(R.id.truck_status,item.getTruck_status().equals("1")?"待装货":"运输中");
    }
}
