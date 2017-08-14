package com.arkui.transportation_owner.view;

import com.arkui.fz_tools._interface.BaseView;
import com.arkui.transportation_owner.entity.LogisticalListEntity;

import java.util.List;

/**
 * Created by nmliz on 2017/8/11.
 */

public interface LogisticsView {

    //列表成功返回数据
    void onSucceed(List<LogisticalListEntity> logisticalList);

    //收藏成功 暂时不写

}
