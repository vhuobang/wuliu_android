package com.arkui.fz_tools.model;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.UserEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nmliz on 2017/8/7.
 * 用户的Api
 */

public interface UserApi {
    //注册接口
    @FormUrlEncoded
    @POST(NetConstants.REGISTER)
    Observable<BaseHttpResult> getRegister(@FieldMap Map<String,Object> parameter);

    //登录接口
    @FormUrlEncoded
    @POST(NetConstants.LOGIN)
    Observable<BaseHttpResult<UserEntity>> getLogin(@FieldMap Map<String,Object> parameter);

}
