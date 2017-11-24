package com.arkui.transportation_shipper.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

public class MonitorService extends Service {
    public static boolean isCheck = false;
    public static boolean isRunning = false;
    private static final String SERVICE_NAME = "com.arkui.transportation_shipper.service.WakeLockService";

    @Override
    public void onCreate() {
        Log.d("MonitorService2", "MonitorService onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MonitorService2", "MonitorService onStartCommand");
        new Thread() {
            @Override
            public void run() {
                while (isCheck) {
                    try {
                        Thread.sleep(5 * 60 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("MonitorService2", "thread sleep failed");
                    }
                    if (!isServiceWork(getApplicationContext(), SERVICE_NAME)) {
                        Log.d("MonitorService2", "WakeLockService轨迹服务已停止，正在重启轨迹服务");
                        startService(new Intent(MonitorService.this, WakeLockService.class));
                    } else {
                        Log.d("MonitorService2", "WakeLockService轨迹服务正在运行");
                    }
                }
            }
        }.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 判断某个服务是否正在运行的方法 * * @param mContext * @param serviceName 是包名+服务的类名（例如：com.baidu.trace.LBSTraceService） * @return true代表正在运行，false代表服务没有正在运行
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(80);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
