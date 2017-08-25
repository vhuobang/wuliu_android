package com.arkui.transportation.view;

import com.arkui.transportation.entity.CargoSearchListEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/25.
 */

public interface CargoSearchView {
    void onSuccess(List<CargoSearchListEntity> cargoSearchListEntities);
    void onFail(String  errorMessage);
}
