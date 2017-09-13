package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.HistocialSearchEntity;
import com.arkui.fz_tools.model.NetConstants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/9/12.
 */

public interface SearchApi {
    //搜索历史
    @FormUrlEncoded
    @POST(NetConstants.SEARCH_LIST)
    Observable<BaseHttpResult<List<HistocialSearchEntity>>> postSearchList(@Field("user_id") String userId, @Field("type") String type);
    // 清空搜索历史
    @FormUrlEncoded
    @POST(NetConstants.DEL_SEARCH)
    Observable<BaseHttpResult> postDelSearch(@Field("user_id") String userId, @Field("type") String type);
}
