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

public class LogisticsAdapter extends BaseQuickAdapter<LogisticalListEntity,BaseViewHolder> {

    public LogisticsAdapter() {
        super(R.layout.item_logistics);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticalListEntity item) {
        GlideUtils.getInstance().loadRound(mContext,item.getLogo(), (ImageView) helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_info,"已注册"+item.getRegisterYear()+"年 "+"成交"+item.getVolume()+"条");
        helper.setRating(R.id.rating,Float.parseFloat(item.getStarRating()));
        helper.setText(R.id.tv_collect,"1".equals(item.getStatus())?"已收藏":"收藏");
        helper.setImageResource(R.id.iv_collect,"1".equals(item.getStatus())?R.mipmap.collect_2:R.mipmap.collect);
        helper.addOnClickListener(R.id.ll_collect);
    }
}
