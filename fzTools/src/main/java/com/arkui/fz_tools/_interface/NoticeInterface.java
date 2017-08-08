package com.arkui.fz_tools._interface;

import com.arkui.fz_tools.entity.NoticeEntity;

import java.util.List;

/**
 * Created by 84658 on 2017/8/8.
 */

public interface NoticeInterface {

    void onSuccess(List<NoticeEntity> noticeEntityList);
    void onFail(String message);
}
