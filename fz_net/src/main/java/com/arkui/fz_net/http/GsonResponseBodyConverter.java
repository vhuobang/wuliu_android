package com.arkui.fz_net.http;

import android.util.Log;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Charles on 2016/3/17.
 */
class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //httpResult 只解析result字段
        BaseHttpResult httpResult = gson.fromJson(response, BaseHttpResult.class);
        //
        if (httpResult.getCode() == 0) {
            throw new ApiException(httpResult);
        }
        try {
            return gson.fromJson(response, type);
        } catch (JsonSyntaxException e) {
            Log.d("fz", e.toString());
             throw new JsonSyntaxException(e);
        }

    }
}
