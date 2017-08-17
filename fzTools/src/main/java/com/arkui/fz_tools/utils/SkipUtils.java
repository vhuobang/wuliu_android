package com.arkui.fz_tools.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by nmliz on 2017/8/15.
 */

public class SkipUtils {


    //跳转到 拨号
    public static void skipPhone(Context context, String phone) {
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }
}
