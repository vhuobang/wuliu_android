package com.arkui.transportation_owner.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arkui.fz_tools.entity.LogWayBIllListEntity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_owner.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 84658 on 2017/8/28.
 */

public class WaybillListAdapter extends BaseQuickAdapter<LogWayBIllListEntity,BaseViewHolder> {
    public WaybillListAdapter(@LayoutRes int layoutResId, @Nullable List<LogWayBIllListEntity> data) {
        super(layoutResId, data);
    }

    public WaybillListAdapter(@Nullable List<LogWayBIllListEntity> data) {
        super(data);
    }

    public WaybillListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogWayBIllListEntity item) {
        helper.setText(R.id.tv_name, item.getLicensePlate() + "   " + item.getCargoName() +"  "+
                item.getCarrierNum()+ StrUtil.formatUnit(item.getUnit()));
        String[] loadingAddress = item.getLoadingAddress().split(" ");
        String[] unloadingAddress = item.getUnloadingAddress().split(" ");
        helper.setText(R.id.tv_address, loadingAddress[0]);
        helper.setText(R.id.tv_unloading_address, unloadingAddress[0]);

        String cargoStatus = item.getCargoStatus();
        switch (cargoStatus) {
            case "1":
                helper.setVisible(R.id.ll_location, true);
                helper.setVisible(R.id.tv_state, false);
                helper.setText(R.id.tv_company, item.getLogName());
                helper.setText(R.id.tv_name, item.getLicensePlate() + "   " + item.getCargoName()
                        + "  预装" + item.getCarrierNum() + StrUtil.formatUnit(item.getUnit()));
                break;
            case "2":
                helper.setVisible(R.id.ll_location, true);
                helper.setVisible(R.id.tv_state, false);
                helper.setText(R.id.tv_company, item.getLogName());
                break;
            case "3":
                helper.setText(R.id.tv_state, item.getFreightPrice()+"元");
                break;
            case "5":
                helper.setVisible(R.id.ll_location, false);
                helper.setVisible(R.id.tv_state, false);
                break;
        }
        helper.addOnClickListener(R.id.ll_location);
    }
}
