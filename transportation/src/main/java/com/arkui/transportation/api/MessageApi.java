package com.arkui.transportation.api;

import com.arkui.fz_net.entity.BaseHttpResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/7.
 */

public interface MessageApi {
   // 消息已读
    @FormUrlEncoded
    @POST(UrlContents.READ_MESSAGE)
    Observable<BaseHttpResult> getReadMessage(@Field("id") String messageId);
    //

}
