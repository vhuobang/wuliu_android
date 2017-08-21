package com.arkui.transportation.adapter;

import com.amap.api.services.help.Tip;
import com.arkui.transportation.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/8/16.
 */

public class SearchAddressAdapter extends BaseQuickAdapter<Tip, BaseViewHolder> {
    public SearchAddressAdapter() {
        super(R.layout.item_history_search);
    }

    @Override
    protected void convert(BaseViewHolder helper, Tip item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_name2, item.getAddress());
        helper.setVisible(R.id.tv_name2,true);
    }
}
