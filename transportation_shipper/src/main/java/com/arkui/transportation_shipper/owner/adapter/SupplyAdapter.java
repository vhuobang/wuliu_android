package com.arkui.transportation_shipper.owner.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.entity.SupplyListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 */

public class SupplyAdapter extends BaseQuickAdapter<SupplyListEntity, BaseViewHolder> {

    public SupplyAdapter() {
        super(R.layout.item_supply);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplyListEntity item) {
        GlideUtils.getInstance().loadRound(mContext,item.getLogo(), (ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_loading_address, StrUtil.splitAddress(item.getLoading_address()))
                .setText(R.id.tv_unloading_address,StrUtil.splitAddress(item.getUnloading_address()))
                .setText(R.id.tv_info,String.format("%s/%s%s/剩余%s%s",item.getCargo_name(),item.getCargo_num(),StrUtil.formatUnit(item.getUnit()),item.getSurplus_num(),StrUtil.formatUnit(item.getUnit())))
        .setText(R.id.tv_time,String.format("%s%s   %s",item.getCargo_price(),StrUtil.formatMoneyUnit(item.getUnit()),StrUtil.formatSettlementTime(item.getLog_settlement_time())));
    }
}
