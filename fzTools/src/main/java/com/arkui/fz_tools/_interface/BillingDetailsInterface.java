package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.BillingDetailsEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/22.
 */

public interface BillingDetailsInterface {
    void onSuccess(List<BillingDetailsEntity> billingDetailsEntityList);
    void onFail(String errorMessage);
}
