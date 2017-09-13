package com.arkui.transportation.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation.R;
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
        ImageView view = helper.getView(R.id.iv_head);
      GlideUtils.getInstance().loadRound(mContext,item.getAvatar(),view);
        helper.setText(R.id.tv_start_address,StrUtil.splitAddress(item.getLoadingAddress()));
        helper.setText(R.id.tv_destination,StrUtil.splitAddress(item.getUnloadingAddress()));
        helper.setText(R.id.tv_info,item.getCargoName()+"/"+ item.getCargoNum()+ StrUtil.formatUnit(item.getUnit()));
        helper.setText(R.id.tv_state,item.getLogisticalStatus().equals("1")? "待发布":"已发布");
    }
}
