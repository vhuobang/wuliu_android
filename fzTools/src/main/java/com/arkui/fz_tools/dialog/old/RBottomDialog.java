package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.view.Gravity;

/**
 * Created by 任少东 on 2016/07/29 15:40
 */
public abstract class RBottomDialog extends RBaseDialog {
    public RBottomDialog(Context context) {
        super(context);
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    protected float getWidthScale() {
        return 1;
    }

    @Override
    protected float getDimAmount() {
        return 0f;
    }
}
