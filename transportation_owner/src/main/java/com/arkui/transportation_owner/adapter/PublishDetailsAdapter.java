package com.arkui.transportation_owner.adapter;

import android.support.annotation.LayoutRes;

import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.entity.PublishDetailEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/8/22.
 */

public class PublishDetailsAdapter extends BaseQuickAdapter<PublishDetailEntity.CarrierInfoBean,BaseViewHolder>{

    public PublishDetailsAdapter() {
        super(R.layout.item_carriage_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublishDetailEntity.CarrierInfoBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_info,String.format("委派车辆数:%s  承运数量:%s吨",item.getAssignVehicles(),item.getQuantityShipment()));

    }
}
