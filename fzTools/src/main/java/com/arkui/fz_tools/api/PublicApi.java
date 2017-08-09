package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.NoticeEntity;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.model.NetConstants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/8.
 */

public interface PublicApi {

    //意见反馈接口
    @FormUrlEncoded
    @POST(NetConstants.FREED_BACK)
    Observable<BaseHttpResult> getFreedBack(@FieldMap Map<String, Object> parameter);

    // 消息列表
    @FormUrlEncoded
    @POST(NetConstants.NOTICE_LIST)
    Observable<BaseHttpResult<List<NoticeEntity>>> getNoticeList(@FieldMap Map<String, Object> parameter);

    //上传图片
    @FormUrlEncoded
    @POST(NetConstants.IMAGE_UPLOAD)
    Observable<BaseHttpResult<UpLoadEntity>> upload(@Field("type") String type, @Field("img") String img);

}
