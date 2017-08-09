package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.ShareCodeEntity;

/**
 * Created by 84658 on 2017/8/9.
 */

public interface ShareCodeInterface {
    void onSuccess(ShareCodeEntity shareCodeEntity);
    void onFail(String message);
}
