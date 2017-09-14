package com.arkui.transportation_shipper.owner.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.transportation_shipper.common.api.UrlContents;
import com.arkui.transportation_shipper.owner.entity.LogisticalListEntity;
import com.arkui.transportation_shipper.owner.entity.TruckOwnerWaybillDetialEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/30.
 */

public interface LogisticalApi {

    //物流详情
    @FormUrlEncoded
    @POST(NetConstants.LOGISTICAL_DETAILS)
    Observable<BaseHttpResult<LogisticalListEntity>> postLogisticalDetail(@Field("user_id") String user_id, @Field("log_id") String log_id);
   // 车主端运单详情
    @FormUrlEncoded
    @POST(UrlContents.TRUCK_OWNER_WAYBILL_DETAILS)
    Observable<BaseHttpResult<TruckOwnerWaybillDetialEntity>> getTruckOwnerWaybillDetails(@Field("id") String id);

}
