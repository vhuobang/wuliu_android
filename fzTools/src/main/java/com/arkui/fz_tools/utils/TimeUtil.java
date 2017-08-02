package com.arkui.fz_tools.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：任少东
 * 时间：2016-06-27 13:51
 */
public class TimeUtil {
    public static String getFormatTime(long millisecond, String template) {
        SimpleDateFormat format = new SimpleDateFormat(template);
        return format.format(new Date(millisecond));
    }

    public static String getCurTime(String template) {
        return getFormatTime(System.currentTimeMillis(), template);
    }

    /**
     * 将字符串时间转化为新的字符串时间
     *
     * @param oldStr
     * @param oldPattern 旧格式
     * @param newPattern 新格式
     * @return
     */
    public static String strToLongTime(String oldStr, String oldPattern, String newPattern) {

        if (TextUtils.isEmpty(oldStr)) {
            return "";
        }
        String formatTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat(oldPattern);
        try {
            Date parse = sdf.parse(oldStr);
            long time = parse.getTime();
            formatTime =  getFormatTime(time, newPattern);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTime;
    }


}
