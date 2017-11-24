package com.arkui.transportation_shipper.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arkui.transportation_shipper.service.WakeLockService;

/**
 * Created by 84658 on 2017/10/11.
 */

public class TrackReceiver extends BroadcastReceiver {

    private static final String TAG = "TrackReceiver";

    @SuppressLint("Wakelock")
    @Override
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            Log.d(TAG,"screen off,acquire wake lock!");
            if (null != WakeLockService.wakeLock && !(WakeLockService.wakeLock.isHeld())) {
                WakeLockService.wakeLock.acquire();
            }
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            Log.d(TAG,"screen on,release wake lock!");
            if (null != WakeLockService.wakeLock && WakeLockService.wakeLock.isHeld()) {
                WakeLockService.wakeLock.release();
            }
        }
    }
}
