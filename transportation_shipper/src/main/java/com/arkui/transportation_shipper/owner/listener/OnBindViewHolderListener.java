package com.arkui.transportation_shipper.owner.listener;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by nmliz on 2017/6/21.
 */

public interface OnBindViewHolderListener <T>{
    abstract void convert(BaseViewHolder helper, T item);
}
