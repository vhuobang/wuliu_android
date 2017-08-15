package com.arkui.transportation_owner.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.transportation_owner.entity.LogisticalListEntity;

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

    @FormUrlEncoded
    @POST(NetConstants.LOGISTICAL_DETAILS)
    Observable<BaseHttpResult<LogisticalListEntity>> postLogisticalDetail(@Field("log_id") String log_id);

}
