package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.PublishBean;

/**
 * Created by 84658 on 2017/9/14.
 */

public interface PublishInterface {
    void  onSuccess(PublishBean publishBean);
    void onFail(String message);
}
