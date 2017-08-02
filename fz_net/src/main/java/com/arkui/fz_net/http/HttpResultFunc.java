package com.arkui.fz_net.http;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;

import io.reactivex.functions.Function;


/**
 * Created by nmliz on 2017/4/13.
 */

public class HttpResultFunc<T> implements Function<BaseHttpResult<T>, T> {
   /* @Override
    public T call(BaseHttpResult<T> httpResult) {
        if (httpResult.getCode() != 1) {
            throw new ApiException(httpResult);
        }
        return httpResult.getData();
    }*/

    @Override
    public T apply(BaseHttpResult<T> tBaseHttpResult) throws Exception {
        if (tBaseHttpResult.getCode() != 1) {
            throw new ApiException(tBaseHttpResult);
        }
        return tBaseHttpResult.getData();
    }
}
