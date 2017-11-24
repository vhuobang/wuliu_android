package com.arkui.transportation.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
import com.arkui.transportation.entity.LogWayBIllListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 84658 on 2017/8/23.
 */

public class MyWaybillListAdapter extends BaseQuickAdapter<LogWayBIllListEntity,BaseViewHolder> {

    public MyWaybillListAdapter(@LayoutRes int layoutResId, @Nullable List<LogWayBIllListEntity> data) {
        super(layoutResId, data);
    }

    public MyWaybillListAdapter(@Nullable List<LogWayBIllListEntity> data) {
        super(data);
    }


    public MyWaybillListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogWayBIllListEntity item) {
        helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName() +"  "+
                item.getCarrierNum()+ StrUtil.formatUnit(item.getUnit()));
        String[] split = item.getLoadingAddress().split(" ");
        helper.setText(R.id.tv_address,split[0]);
        String[] split1 = item.getUnloadingAddress().split(" ");
        helper.setText(R.id.tv_unloading_address,split1[0]);
        String mType = item.getLogStatus();

        switch (mType) {
            case "1":
                helper.setVisible(R.id.ll_location, true);
                helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName()
                        + "  预装" + item.getCarrierNum() + StrUtil.formatUnit(item.getUnit()));
                break;
            case "2":
                helper.setVisible(R.id.ll_location, true);
                helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName() +"  "+
                        item.getLoadingWeight()+ StrUtil.formatUnit(item.getUnit()));
                break;
            case "3":
                helper.setVisible(R.id.tv_state, true);
                helper.setText(R.id.tv_state,"运费:" + item.getAllPrice());
                helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName() +"  "+
                        item.getUnloadingWeight()+ StrUtil.formatUnit(item.getUnit()));
                break;
            case "5":
                helper.setVisible(R.id.tv_state, false);
                helper.setText(R.id.tv_product_info, item.getLicensePlate() + "   " + item.getCargoName() +"  "+
                        item.getUnloadingWeight()+ StrUtil.formatUnit(item.getUnit()));
                break;
        }
        helper.addOnClickListener(R.id.ll_location);
    }
}
