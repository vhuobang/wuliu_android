package com.arkui.transportation_owner.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.entity.PublishDetailEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nmliz on 2017/8/11.
 * 物流相关的API
 */

public interface LogisticalApi {

    /**
     * 物流列表
     * @param parameter
     * @return
     */
    @FormUrlEncoded
    @POST(NetConstants.LOGISTICAL_LIST)
    Observable<BaseHttpResult<List<LogisticalListEntity>>> postLogisticalList(@FieldMap Map<String, Object> parameter);

    //物流详情
    @FormUrlEncoded
    @POST(NetConstants.LOGISTICAL_DETAILS)
    Observable<BaseHttpResult<LogisticalListEntity>> postLogisticalDetail(@Field("user_id") String user_id,@Field("log_id") String log_id,@Field("id") String id);

    //物流收藏和取消收藏
    @FormUrlEncoded
    @POST(NetConstants.COLLECTION_LOGISTICAL)
    Observable<BaseHttpResult> postCollectionLogistical(@Field("user_id") String user_id,@Field("log_id") String log_id);

    //收藏物流列表
    @FormUrlEncoded
    @POST(NetConstants.MY_COLLECTION)
    Observable<BaseHttpResult<List<LogisticalListEntity>>> postCollectionLogisticalList(@Field("user_id") String user_id,@Field("page") int page,@Field("pagesize") int pagesize);

    //已发布详情
    @FormUrlEncoded
    @POST(NetConstants.PUBLISH_DETAILS)
    Observable<BaseHttpResult<PublishDetailEntity>> postPublishDetail(@Field("user_id") String user_id,@Field("cargo_id") String cargo_id);

    // UP_CARGO_STATUS 停止发布
    @FormUrlEncoded
    @POST(UrlContents.UP_CARGO_STATUS)
    Observable<BaseHttpResult> upCargoStatus(@Field("cargo_id") String carGoId);
}
