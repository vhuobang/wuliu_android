package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.PrePayEntity;

/**
 * Created by 84658 on 2017/9/7.
 */

public interface PrePayInterface {
    void onSuccess(PrePayEntity prePayEntity);
    void onPayFail(String errMessage);
}
