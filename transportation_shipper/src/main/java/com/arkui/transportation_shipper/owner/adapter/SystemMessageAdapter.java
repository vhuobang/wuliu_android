package com.arkui.transportation_shipper.owner.adapter;

import com.arkui.transportation_shipper.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/6/19.
 */

public class SystemMessageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SystemMessageAdapter() {
        super(R.layout.item_system_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
    }
}
