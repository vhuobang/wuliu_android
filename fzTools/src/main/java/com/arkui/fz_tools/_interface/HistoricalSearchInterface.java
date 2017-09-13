package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.HistocialSearchEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/9/12.
 */

public interface HistoricalSearchInterface {
    void onSuccess(String successMessage);
    void onSearchFail(String errorMessage);
    void onSearchListSuccess(List<HistocialSearchEntity> histocialSearchEntity);
}
