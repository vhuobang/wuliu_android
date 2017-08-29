package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.LogWayBIllListEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/28.
 */

public interface WaybillListInterface {

    void  onSuccess(List<LogWayBIllListEntity> logWayBIllListEntityList);

    void  onError(String errorMessage);
}
