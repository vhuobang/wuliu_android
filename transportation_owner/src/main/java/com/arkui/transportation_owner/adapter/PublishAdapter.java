package com.arkui.transportation_owner.adapter;

import com.arkui.fz_tools.entity.CarGoListEntity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_owner.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/8/18.
 * 已发布和未发布的Adapter
 */

public class PublishAdapter extends BaseQuickAdapter<CarGoListEntity,BaseViewHolder> {

    public PublishAdapter() {
        super(R.layout.item_waybill_publish);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarGoListEntity item) {
        String[] loadingAddress = item.getLoadingAddress().split(" ");
        String[] unloadingAddress = item.getUnloadingAddress().split(" ");
        String unit = StrUtil.formatUnit(item.getUnit());
        helper.setText(R.id.tv_loading_address,loadingAddress.length>=0?loadingAddress[0]:"")
                .setText(R.id.tv_unloading_address,unloadingAddress.length>=0?unloadingAddress[0]:"")
                .setText(R.id.tv_info,item.getCargoName()+"/"+item.getCargoNum()+ unit+ "/" +item.getSurplusNum()+unit)
        .setText(R.id.tv_company,item.getFreightPrice()+ StrUtil.formatMoneyUnit(item.getUnit())+"   "+StrUtil.formatSettlementTime(item.getSettlementTime()));
       helper.setVisible(R.id.tv_state,true);
        helper.setText(R.id.tv_state,StrUtil.formatCStatus(item.getCStatus()));
    }
}
