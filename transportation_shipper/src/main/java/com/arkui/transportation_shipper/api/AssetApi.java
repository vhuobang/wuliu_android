package com.arkui.transportation_shipper.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;

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
}
