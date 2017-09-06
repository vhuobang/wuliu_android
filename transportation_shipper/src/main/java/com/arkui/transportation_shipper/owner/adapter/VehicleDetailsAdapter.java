package com.arkui.transportation_shipper.owner.adapter;

import android.support.annotation.Nullable;

import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.entity.TruckStatusEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by nmliz on 2017/9/5.
 * TODO 2017年9月5日 缺少车牌号 让后台返回
 */

public class VehicleDetailsAdapter extends BaseQuickAdapter<TruckStatusEntity,BaseViewHolder> {

    public VehicleDetailsAdapter() {
        super(R.layout.item_vehicle_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, TruckStatusEntity item) {
        if(item==null)
            return;
        helper.setText(R.id.tv_name,String.format("%s %s","",item.getCargo_name()))
                .setText(R.id.tv_loading_address, StrUtil.splitAddress(item.getLoading_address()))
                .setText(R.id.tv_unloading_address,StrUtil.splitAddress(item.getUnloading_address()))
                .setText(R.id.tv_company,item.getName());
    }
}
