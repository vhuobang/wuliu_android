package com.arkui.transportation_shipper.common.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.transportation_shipper.common.entity.SupplyListEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
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

}
