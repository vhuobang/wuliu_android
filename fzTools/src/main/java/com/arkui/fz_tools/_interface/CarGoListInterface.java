package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.CarGoListEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/17.
 */

public interface CarGoListInterface {
    void onCarGoListSuccess(List<CarGoListEntity> carGoListEntityList);
    void onCarGoListFail(String errorMessage);
}
