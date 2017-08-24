package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.InformationDetailEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/22.
 */

public interface InformationFeeInterface {
    void onSuccess(List<InformationDetailEntity> informationDetailEntities);
    void onFail(String errorMessage);
}
