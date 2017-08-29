package com.arkui.transportation_shipper.owner.adapter;

import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/6/19.
 */

public class VehicleManageAdapter extends BaseQuickAdapter<TruckListEntity, BaseViewHolder> {
    public VehicleManageAdapter() {
        super(R.layout.item_asset_manage);
    }

    @Override
    protected void convert(BaseViewHolder helper, TruckListEntity item) {
        helper.setText(R.id.tv_name,item.getLicense_plate())
                //TODO 这里车型还要改 先预留个 BUG
        .setText(R.id.tv_content,String.format("车型:%s  接单数:%s","null",item.getSingular_num()));

    }
}
