package com.arkui.transportation.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arkui.transportation.entity.CargoSearchListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 84658 on 2017/8/25.
 */

public class SearchLogisticaAdapter extends BaseQuickAdapter<CargoSearchListEntity,BaseViewHolder> {
    public SearchLogisticaAdapter(@LayoutRes int layoutResId, @Nullable List<CargoSearchListEntity> data) {
        super(layoutResId, data);
    }

    public SearchLogisticaAdapter(@Nullable List<CargoSearchListEntity> data) {
        super(data);
    }

    public SearchLogisticaAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CargoSearchListEntity item) {

    }
}
