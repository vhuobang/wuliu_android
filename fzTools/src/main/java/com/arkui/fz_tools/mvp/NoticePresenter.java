package com.arkui.fz_tools.mvp;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.NoticeInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.NoticeEntity;
import com.arkui.fz_tools.model.PublicModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 84658 on 2017/8/8.
 * 消息列表
 */

public class NoticePresenter  extends BasePresenter{

    public NoticeInterface mNoticeInterface;
    public PublicApi mPublicApi;

    public void setNoticeInterface(NoticeInterface noticeInterface){
        mNoticeInterface = noticeInterface;
        //mModel = model;
       // mModel.initModel();
        mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }
    // 消息列表
    public  void  getNoticeList(String userId,String type,int page,int pageSize){
        /*mModel.getNoticeList(userId, type, page, pageSize, */
        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("user_id",userId);
        hashMap.put("type",type);
        hashMap.put("page",page);
        hashMap.put("pagesize",pageSize);
        Observable<List<NoticeEntity>> observable = mPublicApi.getNoticeList(hashMap).map(new HttpResultFunc<List<NoticeEntity>>());
        HttpMethod.getInstance().getNetData(observable,new ProgressSubscriber<List<NoticeEntity>>(mContext) {
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
