package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.IntegralDetailsEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/22.
 */

public interface IntegralDetailsInterface {
    void onSuccess(List<IntegralDetailsEntity> integralDetailsEntityList);
    void onFail( String errorMessage);
}
