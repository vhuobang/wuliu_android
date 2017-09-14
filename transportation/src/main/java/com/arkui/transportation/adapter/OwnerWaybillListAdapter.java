package com.arkui.transportation.adapter;

import android.support.annotation.LayoutRes;

import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
import com.arkui.transportation.entity.LogWayBIllListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/9/13.
 */

public class OwnerWaybillListAdapter extends BaseQuickAdapter<LogWayBIllListEntity,BaseViewHolder> {

    private int mType;

    public OwnerWaybillListAdapter(int type) {
        super( R.layout.item_owner_waybill_list);
        this.mType=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, LogWayBIllListEntity item) {
        helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName());
        String[] loadingAddress = item.getLoadingAddress().split(" ");
        String[] unloadingAddress = item.getUnloadingAddress().split(" ");

        helper.setText(R.id.tv_address, loadingAddress[0]);
        helper.setText(R.id.tv_unloading_address, unloadingAddress[0]);
        switch (mType) {
            case 1:
                helper.setVisible(R.id.ll_location, true);
                helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName()
                        + "  预装" + item.getCarrierNum() + StrUtil.formatUnit(item.getUnit()));
                break;
            case 2:
                helper.setVisible(R.id.ll_location, true);
                break;
            case 3:
                helper.setVisible(R.id.tv_state, true);
                break;
        }
        helper.addOnClickListener(R.id.ll_location);
    }
}
