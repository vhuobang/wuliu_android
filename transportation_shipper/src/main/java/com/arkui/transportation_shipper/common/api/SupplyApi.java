package com.arkui.transportation_shipper.common.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation_shipper.common.entity.CargoListDetailEntity;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.OrderEntity;
import com.arkui.transportation_shipper.common.entity.SupplyListEntity;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nmliz on 2017/8/29.
 * 货源模块相关的接口
 */

public interface SupplyApi {

    //货源列表
    @FormUrlEncoded
    @POST(UrlContents.OWNER_CARGO_LIST)
    Observable<BaseHttpResult<List<SupplyListEntity>>> postSupplyList(@FieldMap Map<String, Object> parameter);

    //  CARGO_LIST_DETAIL
    //货源详情
    @FormUrlEncoded
    @POST(UrlContents.CARGO_LIST_DETAIL)
    Observable<BaseHttpResult<CargoListDetailEntity>> getCargoListDetail(@Field("id") String id);

    //选择司机列表
    @FormUrlEncoded
    @POST(UrlContents.DRIVER_LIST_BY_TRUCK)
    Observable<BaseHttpResult<List<DriverListEntity>>> postDriverList(@Field("user_id") String user_id, @Field("id") String id);

    //选择车辆列表
    @FormUrlEncoded
    @POST(UrlContents.DRIVER_TRUCK_LIST)
    Observable<BaseHttpResult<List<TruckListEntity>>> postTruckList(@Field("user_id") String user_id,@Field("id") String id);

    //货源---货源详情抢单
    @FormUrlEncoded
    @POST(UrlContents.GET_WAYBILL)
    Observable<BaseHttpResult<OrderEntity>> postOrder(@FieldMap Map<String, Object> parameter);

    //获取未支付信息费的订单
    @FormUrlEncoded
    @POST(UrlContents.IS_SETTLE)
    Observable<BaseHttpResult<List<OrderEntity>>> postIsSettle(@Field("user_id") String user_id);

    //支付信息费
    @FormUrlEncoded
    @POST(UrlContents.PAY)
    Observable<BaseHttpResult> postPay(@FieldMap Map<String, Object> parameter);

}
