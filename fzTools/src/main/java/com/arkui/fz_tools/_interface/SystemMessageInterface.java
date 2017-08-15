package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.SystemDetialEntity;

/**
 * Created by 84658 on 2017/8/14.
 */

public interface SystemMessageInterface {
    void onSuccess(SystemDetialEntity systemDetialEntity);
    void  onFail(String  errorMessage);
}
