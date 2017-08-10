package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.UserEntity;

/**
 * Created by 84658 on 2017/8/9.
 */

public interface UserEditInterface {
    void onSuccess(UserEntity userEntity);
     void onFail(String message);
}
