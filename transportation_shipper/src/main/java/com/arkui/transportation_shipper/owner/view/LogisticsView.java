package com.arkui.transportation_shipper.owner.view;

import com.arkui.transportation_shipper.owner.entity.LogisticalListEntity;

/**
 * Created by 84658 on 2017/8/30.
 */

public interface LogisticsView {
    void  onSuccess(LogisticalListEntity logisticalListEntity);
    void  onError(String errorMessage);
}
