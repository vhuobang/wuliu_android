package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.ReleaseDetailsEntity;

/**
 * Created by 84658 on 2017/8/17.
 */

public interface ReleaseDetailInterface {

    void onSuccess(ReleaseDetailsEntity releaseDetailsEntity);
    void onFail(String errorMessage);

}
