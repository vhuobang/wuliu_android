package com.arkui.transportation_shipper.common.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation_shipper.common.entity.DriverOrderDetailEntity;
import com.arkui.transportation_shipper.common.entity.DriverOrderListEntity;
import com.arkui.transportation_shipper.common.entity.PoundListDetail;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/9/3.
 */

public interface DriverApi {
    //司机运单列表
    @FormUrlEncoded
    @POST(UrlContents.DRIVER_ORDER_LIST)
    Observable<BaseHttpResult<List<DriverOrderListEntity>>> getCargoList(@Field("driver_id") String driverId,
                                                                         @Field("order_status") String orderStatus);
    //司机运单列表详情
    @FormUrlEncoded
    @POST(UrlContents.DRIVER_ORDER_DETAIL)
    Observable<BaseHttpResult<DriverOrderDetailEntity>> getCargoListDetail(@Field("order_id") String orderId);
   // 提交装货榜单 LOADING_SUBMIT
   @FormUrlEncoded
   @POST(UrlContents.LOADING_SUBMIT)
   Observable<BaseHttpResult> loadingSubmit(@FieldMap Map<String,Object> map);
    // 提交卸货磅单 App/Driver/unload
    @FormUrlEncoded
    @POST(UrlContents.UNLOAD_SUBMIT)
    Observable<BaseHttpResult> unloadingSubmit(@FieldMap Map<String,Object> map);
    // 查看磅单 LOADING_LIST_DETAIL
    @FormUrlEncoded
    @POST(UrlContents.LOADING_LIST_DETAIL)
    Observable<BaseHttpResult<PoundListDetail>> loadingListDetail(@Field("order_id") String orderId);
   // 上传位置
   @FormUrlEncoded
   @POST(UrlContents.DRIVER_POSITION)
   Observable<BaseHttpResult> upDriverPosition(@Field("log") String log,@Field("lat") String lat);
}

























