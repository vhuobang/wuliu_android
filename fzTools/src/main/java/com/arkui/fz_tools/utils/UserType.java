package com.arkui.fz_tools.utils;

/**
 * Created by nmliz on 2017/8/7.
 */

import android.support.annotation.IntDef;

import com.arkui.fz_tools.model.Constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        Constants.OWNER, Constants.LOGISTICS, Constants.CAR_OWNER
})
@Retention(RetentionPolicy.SOURCE)
public @interface UserType {
}
