package com.arkui.transportation_shipper.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.DriverApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.receiver.TrackReceiver;
import com.squareup.haha.perflib.Main;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WakeLockService extends Service implements AMapLocationListener {
    private static final String ACCOUNT_AUTHORITY ="" ;
    //cpu保活
    private static boolean isRegister = false;
    protected static PowerManager pm = null;
    public static PowerManager.WakeLock wakeLock = null;
    public static TrackReceiver trackReceiver = new TrackReceiver();

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private DriverApi driverApi;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
        nb.setOngoing(true);
        nb.setContentTitle(getString(R.string.app_name));
        nb.setContentText(getString(R.string.app_name));
        nb.setSmallIcon(R.mipmap.ic_launcher);
        PendingIntent pendingintent =PendingIntent.getActivity(this, 0,  new Intent(this, Main.class), 0);
        nb.setContentIntent(pendingintent);
        startForeground(1423, nb.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (driverApi==null){
            driverApi = RetrofitFactory.createRetrofit(DriverApi.class);
        }
        //动态注册
        if (!isRegister) {
            if (null == pm) {
                pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            }
            if (null == wakeLock) {
                wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "track upload");
            }
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            registerReceiver(trackReceiver, filter);
            isRegister = true;
        }
        getLocation();

        return START_STICKY;
    }
    /**
     * 获取定位
     */
    private void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
       // mLocationOption.setOnceLocation(true);
        mLocationOption.setInterval(1000*60*5);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }


    //得到经纬度
    private double latitude;
    private double longitude;
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null
                && aMapLocation.getErrorCode() == 0) {
            latitude = aMapLocation.getLatitude();
            longitude = aMapLocation.getLongitude();
            Log.e("fz", "latitude:  " + latitude + "longitude:  " + longitude + aMapLocation.getAddress());
            upLocation(latitude,longitude);
        } else {
            String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
            Log.e("AmapErr", errText);
            //  Toast.makeText(RouteSentActivity.this, errText, Toast.LENGTH_LONG).show();
        }
    }
    Disposable mdisposable;
    private void upLocation(double latitude, double longitude) {

        Observable<BaseHttpResult> observable = driverApi.upDriverPosition(String.valueOf(longitude), String.valueOf(latitude), App.getUserId());
        HttpMethod.getInstance().getNetData(observable, new Observer<BaseHttpResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseHttpResult value) {

                Log.e("fz", "onNext: " + value.getMessage() );
            }

            @Override
            public void onError(Throwable e) {
                Log.e("fz","onError: "+ e.toString() );
            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.stopLocation();
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegister) {
            try {
                //销毁广播
                unregisterReceiver(trackReceiver);
                isRegister = false;
            } catch (Exception e) {
            }
        }
        destroyLocation();
        mdisposable.dispose();

    }


}


