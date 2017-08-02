package com.arkui.fz_tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.arkui.fz_tools.model.Constants;

/**
 * SP存储数据的工具类
 *
 * @author Administrator
 */
public final class SPUtil {
    private static SPUtil minstance;
    private static SharedPreferences msp;

    private SPUtil() {
    }

    public static SPUtil getInstance(Context context, String name) {
        if (minstance == null) {
            minstance = new SPUtil();
            msp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        return minstance;

    }
    public static SPUtil getInstance(Context context) {
        if (minstance == null) {
            minstance = new SPUtil();
            msp = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        }
        return minstance;

    }
    // 保存数据
    public void save(String name, Object value) {
        if (value instanceof String) {
            msp.edit().putString(name, (String) value).commit();
        } else if (value instanceof Integer) {
            msp.edit().putInt(name, (Integer) value).commit();
        } else if (value instanceof Boolean) {
            msp.edit().putBoolean(name, (Boolean) value).commit();
        }
    }

    // 获取数据ֵ
    public String read(String name, String defValue) {
        return msp.getString(name, defValue);

    }

    public int read(String name, int defValue) {
        return msp.getInt(name, defValue);
    }

    public boolean read(String name, boolean defValue) {
        return msp.getBoolean(name, defValue);
    }

    // 删除数据
    public void remove(String name) {
        msp.edit().remove(name).commit();
    }

}
