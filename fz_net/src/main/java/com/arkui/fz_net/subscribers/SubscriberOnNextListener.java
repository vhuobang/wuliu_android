package com.arkui.fz_net.subscribers;

import com.arkui.fz_net.http.ApiException;

/**
 * Created by wjn on 2016/7/4.
 * 获取model数据监听
 */
public interface SubscriberOnNextListener<T> {
    void onApiError(ApiException e);
}
