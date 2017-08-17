package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.PublishEntity;
import com.arkui.fz_tools.model.NetConstants;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by nmliz on 2017/8/17.
 */

public interface PublishApi {

    //保存发布信息
    @FormUrlEncoded
    @POST(NetConstants.ADD_CARGO)
    Observable<BaseHttpResult> postSaveCargo(@Body()PublishEntity publishEntity);

}
