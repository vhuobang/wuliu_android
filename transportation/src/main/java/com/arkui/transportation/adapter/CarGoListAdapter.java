package com.arkui.transportation.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arkui.fz_tools.entity.CarGoListEntity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 84658 on 2017/8/17.
 */

public class CarGoListAdapter extends BaseQuickAdapter<CarGoListEntity, BaseViewHolder> {

    public CarGoListAdapter(@LayoutRes int layoutResId, @Nullable List<CarGoListEntity> data) {
        super(layoutResId, data);
    }

    public CarGoListAdapter(@Nullable List<CarGoListEntity> data) {
        super(data);
    }

    public CarGoListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarGoListEntity item) {
        //：1、吨；2、方；3、件；4、趟
        String cStatus = item.getCStatus();
        String freightPrice = item.getFreightPrice();
        String[] loadingAddress = item.getLoadingAddress().split(" ");
        String[] unloadingAddress = item.getUnloadingAddress().split(" ");

        helper.setText(R.id.tv_loading_address, loadingAddress.length>=0?loadingAddress[0]:"");
        helper.setText(R.id.tv_unloading_address,unloadingAddress.length>=0?unloadingAddress[0]:"");
        helper.setVisible(R.id.tv_company, true);
        String unit = StrUtil.formatUnit(item.getUnit());
        helper.setText(R.id.tv_company, freightPrice + StrUtil.formatMoneyUnit(item.getUnit()) + "/" + StrUtil.formatSettlementTime(item.getSettlementTime()));
        switch (cStatus) {
            case "0": //预发布
                helper.setVisible(R.id.tv_state, false);
                helper.setText(R.id.tv_good_info, item.getCargoName() + "/" + item.getCargoNum() + unit);
                break;
            case "1"://发布中
                helper.setVisible(R.id.tv_state, true);
                helper.setText(R.id.tv_state, "发布中");
                helper.setText(R.id.tv_good_info, item.getCargoName() + "/" + item.getCargoNum()
                        + unit + "/" + item.getSurplusNum() + unit);
                break;
            case "2": // 已抢完
                helper.setVisible(R.id.tv_state, true);
                helper.setText(R.id.tv_state, "已抢完");
                helper.setText(R.id.tv_good_info, item.getCargoName() + "/" + item.getCargoNum()
                        + unit + "/" + item.getSurplusNum() + unit);
                break;
            case "3": // 已停止
                helper.setVisible(R.id.tv_state, true);
                helper.setText(R.id.tv_state, "已停止");
                helper.setText(R.id.tv_good_info, item.getCargoName() + "/" + item.getCargoNum()
                        + unit + "/" + item.getSurplusNum() + unit);
                break;
        }

    }
}
