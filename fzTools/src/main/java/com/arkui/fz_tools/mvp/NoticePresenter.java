package com.arkui.fz_tools.mvp;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.NoticeInterface;
import com.arkui.fz_tools.entity.NoticeEntity;
import com.arkui.fz_tools.model.PublicModel;

import java.util.List;

/**
 * Created by 84658 on 2017/8/8.
 * 消息列表
 */

public class NoticePresenter  extends BasePresenter<PublicModel>{

    public NoticeInterface mNoticeInterface;

    public void setNoticeInterface(NoticeInterface noticeInterface ,PublicModel model){
        mNoticeInterface = noticeInterface;
        mModel = model;
        mModel.initModel();
    }
    // 消息列表
    public  void  getNoticeList(String userId,String type,int page,int pageSize){
        mModel.getNoticeList(userId, type, page, pageSize, new ProgressSubscriber<List<NoticeEntity>>(mContext) {
            @Override
            public void onNext(List<NoticeEntity> noticeEntityList) {
                  mNoticeInterface.onSuccess(noticeEntityList);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mNoticeInterface.onFail(e.getMessage());
            }
        });

    }

}
