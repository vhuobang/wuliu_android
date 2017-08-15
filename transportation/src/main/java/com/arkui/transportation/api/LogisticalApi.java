package com.arkui.transportation.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation.entity.LogisticalListEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
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
   // 2.
}
