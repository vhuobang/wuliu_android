package com.arkui.transportation_owner.adapter;

import android.widget.ImageView;

import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/8/11.
 */

public class CollectLogisticsAdapter extends BaseQuickAdapter<LogisticalListEntity, BaseViewHolder> {

    public CollectLogisticsAdapter() {
        super(R.layout.item_select_logistics);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticalListEntity item) {
        GlideUtils.getInstance().loadRound(mContext, item.getLogo(), (ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_info, "已注册" + item.getRegisterYear() + "年 " + "成交" + item.getVolume() + "条");

        helper.setRating(R.id.rating, Float.parseFloat(item.getStarRating()!=null?item.getStarRating():"0"));
        helper.addOnClickListener(R.id.iv_select);
        ImageView iv_select = helper.getView(R.id.iv_select);
        iv_select.setSelected(item.isCheck());
    }
}
