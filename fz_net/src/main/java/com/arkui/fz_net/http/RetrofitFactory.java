package com.arkui.fz_net.http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by nmliz on 2017/4/13.
 */

public class RetrofitFactory {

    private static final int DEFAULT_TIMEOUT = 5;
    private static Retrofit mRetrofit;

    private static final RetrofitFactory ourInstance = new RetrofitFactory();

    private RetrofitFactory() {

    }

    public static <T> T createRetrofit(Class<T> service) {
        synchronized (RetrofitFactory.class) {
            if (mRetrofit == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
                builder.addInterceptor(new LogInterceptor());
                String BASE_URL = "http://wuliu.181858.com/";
                mRetrofit = new Retrofit.Builder()
                        .client(builder.build())
                        //  .addConverterFactory(GsonConverterFactory.create())
                        //TODO  对http请求结果进行统一的预处理 GosnResponseBodyConvert 2017年3月29日14:58:55
                        .addConverterFactory(ResponseConvertFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
            }
        }
        return mRetrofit.create(service);
    }
}
