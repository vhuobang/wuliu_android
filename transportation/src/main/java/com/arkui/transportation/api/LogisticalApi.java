package com.arkui.transportation.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation.entity.CargoCarrierListEntity;
import com.arkui.transportation.entity.CargoSearchListEntity;
import com.arkui.transportation.entity.LogWayBIllListEntity;
import com.arkui.transportation.entity.LogisticalDetailEntity;
import com.arkui.transportation.entity.LogisticalListEntity;
import com.arkui.transportation.entity.PublishDetialEntity;
import com.arkui.transportation.entity.SliderMessageEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/15.
 */

public interface LogisticalApi {
    // 1.物流收到的货源
    @FormUrlEncoded
    @POST(UrlContents.CAR_GOTO_LOGISTICAL)
    Observable<BaseHttpResult<List<LogisticalListEntity>>> getLogisticalList(@FieldMap Map<String, Object> parameter);

    // 2.货源详情
    @FormUrlEncoded
    @POST(UrlContents.LOGISTICAL_RECEIVE_DETAILS)
    Observable<BaseHttpResult<LogisticalDetailEntity>> getLogisticalDetails(@Field("cargo_id") String carGoId);

    // 3.物流转发货主
    @FormUrlEncoded
    @POST(UrlContents.LOGISTICAL_FORWARD_CARGO)
    Observable<BaseHttpResult> getLogisticalForward(@FieldMap Map<String, Object> parameter);

    //4.承运详情列表 cargo_carrier_list
    @FormUrlEncoded
    @POST(UrlContents.CARGO_CARRIER_LIST)
    Observable<BaseHttpResult<List<CargoCarrierListEntity>>> getCargoCarrierList(@Field("cargo_id") String carGoId);

    //5.已发布货源详情  PUBLISH_DETAILS
    @FormUrlEncoded
    @POST(UrlContents.PUBLISH_DETAILS)
    Observable<BaseHttpResult<PublishDetialEntity>> getPublishDetails(@Field("cargo_id") String carGoId, @Field("user_id") String UserID);

    // UP_CARGO_STATUS 停止发布
    @FormUrlEncoded
    @POST(UrlContents.UP_CARGO_STATUS)
    Observable<BaseHttpResult> upCargoStatus(@Field("cargo_id") String carGoId);

    // 物流端运单列表
    @FormUrlEncoded
    @POST(UrlContents.LOG_WAYBILL_LIST)
    Observable<BaseHttpResult<List<LogWayBIllListEntity>>> getLogWaybillList(@Field("user_id") String userId, @Field("order_status") String order_status, @Field("type") String type);
   // 首页轮播消息
//   @FormUrlEncoded
   @POST(UrlContents.SLIDER_MESSAGE)
   Observable<BaseHttpResult<List<SliderMessageEntity>>> getSliderMessage();
    //  物流货源搜索 CARGO_TOLOGISTICAL_SEARCH
     @FormUrlEncoded
    @POST(UrlContents.CARGO_TOLOGISTICAL_SEARCH)
    Observable<BaseHttpResult<List<CargoSearchListEntity>>> getCargoSearch(@Field("user_id") String userId, @Field("key_word") String keyWord);
    // 微信支付

}
