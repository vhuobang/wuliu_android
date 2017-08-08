package com.arkui.fz_tools.listener;

import com.arkui.fz_tools.entity.UserEntity;

/**
 * Created by nmliz on 2017/8/7.
 * 登录成功的回调
 */

public interface LoginSucceedListener {
    void loginSucceed(UserEntity userEntity);
}
