package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.UserEntity;

/**
 * Created by nmliz on 2017/8/7.
 */

public interface UserInterface {
    //这里 你就做出对应处理吧 用户相关的 操作成功 暂时性无参数 后续可能有参数
     void onSucceed();
    //登陆成功
    void loginSucceed(UserEntity userEntity);
}
