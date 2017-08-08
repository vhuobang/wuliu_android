package com.arkui.transportation.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation.entity.ShareCodeEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/7.
 */

public interface UserApi {

    @FormUrlEncoded
    @POST(UrlContents.SHARE_CODE)
    Observable<BaseHttpResult<ShareCodeEntity>> getShareCode(@Field("user_id") String userId);
    //

}
