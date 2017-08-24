package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.BankCarEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/22.
 */

public interface BinkListInterface {
    void  onSuccess(List<BankCarEntity> BankCarList);
    void onFail(String errorMessage);
}
