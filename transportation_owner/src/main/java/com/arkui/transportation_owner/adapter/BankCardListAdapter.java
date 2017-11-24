package com.arkui.transportation_owner.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arkui.fz_tools.entity.BinkCardInfo;
import com.arkui.transportation_owner.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 84658 on 2017/10/23.
 */

public class BankCardListAdapter extends BaseQuickAdapter<BinkCardInfo.BindingCardsBean,BaseViewHolder> {

    public BankCardListAdapter(@LayoutRes int layoutResId, @Nullable List<BinkCardInfo.BindingCardsBean> data) {
        super(layoutResId, data);
    }

    public BankCardListAdapter(@Nullable List<BinkCardInfo.BindingCardsBean> data) {
        super(data);
    }

    public BankCardListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BinkCardInfo.BindingCardsBean item) {
      helper.setText(R.id.bank_card_info,item.getBankName()+ " " + item.getCardBegin()+"****" + item.getCardEnd());
    }
}
