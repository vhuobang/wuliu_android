package com.arkui.transportation_shipper.common.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;
import com.arkui.transportation_shipper.common.entity.VehicleDetailEntity;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nmliz on 2017/8/25.
 */

public interface AssetApi {

    //添加车辆
    @FormUrlEncoded
    @POST(UrlContents.TRUCK_ADD)
    Observable<BaseHttpResult> postTruckAdd(@FieldMap Map<String, Object> parameter);

    //车辆列表
    @FormUrlEncoded
    @POST(UrlContents.TRUCK_LIST)
    Observable<BaseHttpResult<List<TruckListEntity>>> postTruckList(@Field("user_id") String user_id);

    //获取车型
    @POST(UrlContents.CAR_TYPE)
    Observable<BaseHttpResult<List<VehicleModelEntity>>> postCarType();

    //司机列表
    @FormUrlEncoded
    @POST(UrlContents.DRIVER_LIST)
    Observable<BaseHttpResult<List<DriverListEntity>>> postDriverList(@Field("user_id") String user_id);

    //添加司机
    @FormUrlEncoded
    @POST(UrlContents.DRIVER_ADD)
    Observable<BaseHttpResult> postDriverAdd(@FieldMap Map<String, Object> parameter);

    //车辆详情
    @FormUrlEncoded
    @POST(UrlContents.TRUCK_DETAIL)
    Observable<BaseHttpResult<VehicleDetailEntity>> postVehicleDetail(@Field("truck_id") String truck_id);

}
