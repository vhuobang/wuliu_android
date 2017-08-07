package com.arkui.transportation.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation.entity.ShareCodeEntity;
import com.arkui.transportation.entity.UserInfoEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/7.
 */

public interface UserApi {
    //注册
    @FormUrlEncoded
    @POST(UrlContents.REGISTER)
    Observable<BaseHttpResult> getRegister(@Field("mobile") String mobile,@Field("password") String password,@Field("type") String type,@Field("invitatioe") String invitation_code);
   //登陆
    @FormUrlEncoded
    @POST(UrlContents.LOGIN)
    Observable<BaseHttpResult<UserInfoEntity>> getLogin(@Field("mobile") String mobile, @Field("password") String password);
    //生成邀请码
    @FormUrlEncoded
    @POST(UrlContents.SHARE_CODE)
    Observable<BaseHttpResult<ShareCodeEntity>> getShareCode(@Field("user_id") String userId);
    //

}
