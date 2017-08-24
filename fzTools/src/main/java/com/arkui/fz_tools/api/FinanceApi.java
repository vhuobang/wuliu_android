package com.arkui.fz_tools.api;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_tools.entity.BankCarEntity;
import com.arkui.fz_tools.entity.BillingDetailsEntity;
import com.arkui.fz_tools.entity.InformationDetailEntity;
import com.arkui.fz_tools.entity.IntegralDetailsEntity;
import com.arkui.fz_tools.model.NetConstants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 84658 on 2017/8/22.
 */

public interface FinanceApi {
    // 1.添加银行卡
    @FormUrlEncoded
    @POST(NetConstants.ADD_BANK)
    Observable<BaseHttpResult> addBank(@FieldMap Map<String, Object> parameter);
    //2.删除银行卡
    @FormUrlEncoded
    @POST(NetConstants.DEL_BANK)
    Observable<BaseHttpResult> delBank(@Field("bank_id") String bankId);

    //3.银行卡列表
    @FormUrlEncoded
    @POST(NetConstants.BANK_LIST)
    Observable<BaseHttpResult<List<BankCarEntity>>> getBankList(@Field("user_id") String userId);
    //4.资金明细列表
    @FormUrlEncoded
    @POST(NetConstants.BILLING_DETAILS)
    Observable<BaseHttpResult<List<BillingDetailsEntity>>> getBillingDetails(@Field("user_id") String userId,@Field("page") int page,@Field("pagesize") int pagesize);
   //5.积分提现明细
   @FormUrlEncoded
   @POST(NetConstants.INTEGRAL_DETAILS)
   Observable<BaseHttpResult<List<IntegralDetailsEntity>>> getIntegralDetails(@Field("user_id") String userId, @Field("page") int page, @Field("pagesize") int pagesize);
   // 6.信息费明细
   @FormUrlEncoded
   @POST(NetConstants.INFOMATION_FEEDETAILS)
   Observable<BaseHttpResult<List<InformationDetailEntity>>> getInformationDetails(@Field("user_id") String userId, @Field("page") int page, @Field("pagesize") int pagesize);
  // 7.支付
  @FormUrlEncoded
  @POST(NetConstants.PAY)
  Observable<BaseHttpResult> pay(@FieldMap Map<String, Object> parameter);

}
