package com.arkui.transportation.view;

import com.arkui.transportation.entity.LogWayBIllListEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/23.
 */

public interface LogWaybillListView {
    void onSuccess(List<LogWayBIllListEntity> logWayBIllListEntities);
    void  onLoadDataFail(String errorMessage);
}
