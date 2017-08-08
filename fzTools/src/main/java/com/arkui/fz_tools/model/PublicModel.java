package com.arkui.fz_tools.model;

import android.support.annotation.NonNull;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.BaseModel;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.NoticeEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 84658 on 2017/8/8.
 */

public class PublicModel implements BaseModel {



    public void initModel() {

    }

    /**
     *意见反馈
     */
    public void getFaceBack(@NonNull String userId, @NonNull String content, @NonNull String tel,
                            ProgressSubscriber<BaseHttpResult> progressSubscriber){
      /*  HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("user_id",userId);
        hashMap.put("content",content);
        hashMap.put("tel",tel);
        Observable<BaseHttpResult> observable = mPublicApi.getFreedBack(hashMap);
        HttpMethod.getInstance().getNetData(observable,progressSubscriber);*/

    }
    //获得消息列表
    public void getNoticeList(@NonNull String userId, @NonNull String type, @NonNull int page, @NonNull int pageSize,
                           ProgressSubscriber<List<NoticeEntity>> progressSubscriber){

    }

    @Override
    public void onDestroy() {

    }
}
