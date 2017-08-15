package com.arkui.transportation_owner.adapter;

import com.arkui.fz_tools.adapter.BaseDataBindingAdapter;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.databinding.ItemLogisticsBinding;
import com.arkui.transportation_owner.entity.LogisticalListEntity;

/**
 * Created by nmliz on 2017/8/11.
 */

public class LogisticsAdapter extends BaseDataBindingAdapter<LogisticalListEntity,ItemLogisticsBinding> {

    public LogisticsAdapter() {
        super(R.layout.item_logistics);
    }

    @Override
    protected void convert(ItemLogisticsBinding binding, LogisticalListEntity item) {
        binding.setLogisticsEntity(item);
        GlideUtils.getInstance().loadRound(mContext,item.getLogo(),binding.ivHead);
    }
}
