package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.ShareCodeEntity;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.NetConstants;

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
    // 生成邀请码接口
    @FormUrlEncoded
    @POST(NetConstants.SHARE_CODE)
    Observable<BaseHttpResult<ShareCodeEntity>> getShareCode(@FieldMap Map<String,Object> parameter);
   // 修改密码接口 FORGET_PASSWORD
   @FormUrlEncoded
   @POST(NetConstants.FORGET_PASSWORD)
   Observable<BaseHttpResult> getForgetPassword(@FieldMap Map<String,Object> parameter);
    // 用户详情 index.php/App/Users/userInfo
    @FormUrlEncoded
    @POST(NetConstants.USER_INFO)
    Observable<BaseHttpResult<UserEntity>> getUserInfo(@FieldMap Map<String,Object> parameter);
    // 完善用户信息  USER_EDIT
    @FormUrlEncoded
    @POST(NetConstants.USER_EDIT)
    Observable<BaseHttpResult<UserEntity>> getUserEdit(@FieldMap Map<String,Object> parameter);

    //个人认证接口
    @FormUrlEncoded
    @POST(NetConstants.PERSONAL_AUTH)
    Observable<BaseHttpResult> postPersonalAuth(@FieldMap Map<String,Object> parameter);

}
