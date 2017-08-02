package com.arkui.fz_tools.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by hubo on 2017/1/9.
 */

public class DestroyActivityUtils {
    //用来保存没有销毁的Activity
    public static Stack<Activity> unDestroyActivityList = new Stack<Activity>();

    public static void quit() {
        for (Activity activity : unDestroyActivityList) {
            if (null != activity) {
                activity.finish();
                activity = null;
            }
        }
        unDestroyActivityList.clear();
    }
    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {

        if (activity != null) {
            unDestroyActivityList.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : unDestroyActivityList) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }


}
