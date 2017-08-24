package com.arkui.transportation_owner.view;

import com.arkui.transportation_owner.entity.PublishDetailEntity;

/**
 * Created by nmliz on 2017/8/22.
 */

public interface PublishDetailView {

    //列表成功返回数据
    void onSucceed(PublishDetailEntity publishDetailEntity);

    //失败没有数据
    void onError();
}
