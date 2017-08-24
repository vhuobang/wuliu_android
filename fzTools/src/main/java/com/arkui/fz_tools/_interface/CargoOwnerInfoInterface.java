package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.CargoOwnerInfoEntity;

/**
 * Created by 84658 on 2017/8/24.
 */

public interface CargoOwnerInfoInterface {
    void  onSuccess(CargoOwnerInfoEntity entity);
    void  onFail(String errorMessage);
}
