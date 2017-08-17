package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.CarGoListEntity;
import com.arkui.fz_tools.entity.NoticeEntity;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.entity.SystemDetialEntity;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.entity.WayBillDetailEntity;
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
    // 消息已读
    @FormUrlEncoded
    @POST(NetConstants.READ_MESSAGE)
    Observable<BaseHttpResult> getReadMessage(@Field("id") String messageId);
    // 系统消息内容
    @FormUrlEncoded
    @POST(NetConstants.NOTICE_DETAILS)
    Observable<BaseHttpResult<SystemDetialEntity>> getNoticeDetails(@Field("id") String messageId);
    // 运单详情
    @FormUrlEncoded
    @POST(NetConstants.WAYBILL_DETAILS)
    Observable<BaseHttpResult<WayBillDetailEntity>> getWayBillDetails(@Field("waybill_id") String wayBillId);
  // 已发布 预发布
    @FormUrlEncoded
    @POST(NetConstants.CARGO_LIST)
    Observable<BaseHttpResult<List<CarGoListEntity>>> getCargoList(@Field("user_id") String userId, @Field("op_status") String opStatus,
                                                                   @Field("page") int page, @Field("pagesize") int pageSize);
   // RELEASE_DETAILS 预发布详情
   @FormUrlEncoded
   @POST(NetConstants.RELEASE_DETAILS)
   Observable<BaseHttpResult<ReleaseDetailsEntity>> getReleaseDetails(@Field("cargo_id") String cargo_id);
}
