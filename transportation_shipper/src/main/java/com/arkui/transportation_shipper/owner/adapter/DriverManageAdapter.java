package com.arkui.transportation_shipper.owner.adapter;

import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/6/19.
 */

public class DriverManageAdapter extends BaseQuickAdapter<DriverListEntity, BaseViewHolder> {
    public DriverManageAdapter() {
        super(R.layout.item_asset_manage);
    }


    @Override
    protected void convert(BaseViewHolder helper, DriverListEntity item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_content,String.format("%s  出车数:%s",item.getMobile(),item.getOut_num()));

    }
}
