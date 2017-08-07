package com.arkui.fz_tools.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.utils.NetUtil;
import com.arkui.fz_tools.utils.ToastUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：任少东
 * 时间：2016-06-24 13:52
 */
public class LoadDataUtil {
    private static LoadDataUtil ourInstance = new LoadDataUtil();
    private OkHttpClient httpClient;
    private Handler delivery;
    private ProgressDialog dialog;
    private Request request;

    private LoadDataUtil() {
        httpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        delivery = new Handler(Looper.getMainLooper());
    }

    public static LoadDataUtil getInstance() {
        return ourInstance;
    }

    public void getData(final Context context, String url, Map<String, Object> map, String string, final ResultCallBack callBack) {
        if (!NetUtil.isConnected(context)) {
            Toast.makeText(context, "连接失败，请检查您的网络链接情况", Toast.LENGTH_SHORT).show();
            return;
        }
        if (string != null) {
            showDialog(context, string);
        }
        url += getParams(map);
        LogUtil.i("url=" + url);
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                delivery.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFinish();
                        dismissDialog();
                        LogUtil.i("fail=" + e);
                        Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                        callBack.onFailed();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                delivery.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFinish();
                        dismissDialog();
                        try {
                            LogUtil.i("success=" + result);
                            callBack.onSuccess(result);
                            callBack.onSuccess(new JSONObject(result));

                            JsonData data = new JsonData(result);
                            if (data.getCode() == 1) {
                                callBack.onSuccess(data);
                            } else {
                                // Toast.makeText(mContext, data.getDataString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void loadData(final Context context, String url, Map<String, Object> map, String string, final ResultCallBack callBack) {
        if (!NetUtil.isConnected(context)) {
            Toast.makeText(context, "连接失败，请检查您的网络链接情况", Toast.LENGTH_SHORT).show();
            return;
        }
        if (string != null) {
            showDialog(context, string);
        }
        if (map == null) {
            map = new HashMap<>();
        }
        /*if (map.get("user_id") == null && LocalData.getBean() != null) {
            map.put("uid", LocalData.getUserID());
        }*/
        LogUtil.i("url=" + url);
        RequestBody body = addParams(map);
        request = new Request.Builder().url(url).post(body).build();
       // new Thread(new Runnable() {
         /*   @Override
            public void run() {*/
                httpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        delivery.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onFinish();
                                dismissDialog();
                                LogUtil.i("fail=" + e);
                                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                                callBack.onFailed();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        try {
                            final String result = response.body().string();
                            delivery.post(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onFinish();
                                    dismissDialog();
                                    try {
                                        LogUtil.i("success=" + result);
                                        callBack.onSuccess(new JSONObject(result));

                                        callBack.onPay(result);

                                        JsonData data = new JsonData(result);


                                        if (data.getCode() == 1) {
                                            callBack.onSuccess(data);
                                        } else {
                                            ToastUtil.showToast(context, data.getDataString());
                                            callBack.onError(data.getError());
                                        }


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        callBack.onFailed();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "请求错误！", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

           // }
       // }).start();
    }

    private String getParams(Map<String, Object> map) {
        StringBuffer buffer = new StringBuffer("?");
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            buffer.append(entry.getKey() + "=" + entry.getValue());
            if (iterator.hasNext()) {
                buffer.append("&");
            }
        }
        return buffer.toString();
    }

    private RequestBody addParams(Map<String, Object> map) {
        FormBody.Builder builder = new FormBody.Builder();
        StringBuffer buffer = new StringBuffer("?");
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            buffer.append(entry.getKey() + "=" + entry.getValue());
            if (iterator.hasNext()) {
                buffer.append("&");
            }
        }
        LogUtil.i("params=" + buffer.toString());
        return builder.build();
    }


    private void showDialog(Context context, String string) {
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    return;
                }
            }

            dialog = new ProgressDialog(context);
            dialog.setMessage(string);
            dialog.show();
        } catch (Exception e) {

        }
    }

    private void dismissDialog() {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onDestroy() {
        try {
            //取消请求
            Call call = httpClient.newCall(request);
            call.cancel();
        } catch (Exception e) {

        }
    }

}
