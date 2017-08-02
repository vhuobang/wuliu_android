package com.arkui.transportation_shipper.owner.adapter;

import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/6/19.
 */

public class DriverManageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public DriverManageAdapter() {
        super(R.layout.item_asset_manage);
    }

    OnBindViewHolderListener<String> mOnBindViewHolderListener;

    public DriverManageAdapter(OnBindViewHolderListener<String> mOnBindViewHolderListener) {
        super(R.layout.item_asset_manage);
        this.mOnBindViewHolderListener=mOnBindViewHolderListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
        if (mOnBindViewHolderListener != null) {
            mOnBindViewHolderListener.convert(helper, item);
        }

    }
}
